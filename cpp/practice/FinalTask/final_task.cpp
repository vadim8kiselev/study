#include <stack>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <iomanip>
#include <time.h>

using namespace std;

class Node{
	std::string name;
	std::string second_name;
	int age;
	bool gender; // AHAHAHAHA 

public:
	Node(){}

	void read(std::ifstream &in){
		in >> name >> second_name >> age >> gender;
	}

	bool getGender(){
		return gender;
	}

	int getAge(){
		return age;
	}

	~Node(){}
};

template <class Type> 
class stack_array{
};


template <class Type> 
class stack_list{
private:

	class Node{
	public:
		Type value;
		Node* next;

		Node(Type value, Node* next){
			this->value = value;
			this->next = next;
		}
	};
	Node* head;

public:

	stack_list() : head(nullptr){}

	bool empty(){
		return head == nullptr;
	}

	void push(Type value){

		head = new Node(value, head);
	}

	void pop(){
		if (empty()) return ;
		Node* tmp = head;		
		head = tmp->next;
		delete tmp;
	}

	Type top(){
		if (empty()) return 0;
		return head->value;
	}

	~stack_list(){}
};


template <class Container>
class test{
	Container box;

	double gen_int(const int &size, const int &start = 0, const int &finish = 9){
		srand((int)time(NULL));
		double time = clock();
		for (int index = 0; index < size; index++){
			box.push(rand() % finish + start);
		}
		return double(clock() - time) / CLOCKS_PER_SEC;
	}
	
	double gen_string(const int &size){		
		ifstream in("input.txt");
		vector <string> words;
		string tmp, trash;
		int count = size;
		while (count && in >> tmp){
			getline(in, trash); // only first word pushing
			words.push_back(tmp);
			count--;
		}
		random_shuffle(words.begin(), words.end());
		in.close();

		double time = clock();
		for (int index = 0; index < size; index++){			
             box.push(words[index]);
		}		
		return double(clock() - time) / CLOCKS_PER_SEC;
	}

	double gen_class(const int &size){
		ifstream in("input.txt");
		vector <Node> objects;
		Node tmp;
		int count = size;
		while (count && in.peek() != EOF){
			tmp.read(in);
			objects.push_back(tmp);
			count--;
		}
		random_shuffle(objects.begin(), objects.end());
		in.close();
		
		double time = clock();
		for (int index = 0; index < size; index++){
			box.push(objects[index]);
		}
		return double(clock() - time) / CLOCKS_PER_SEC;			
	}

	pair<double, double> test_int(const int &size, const int &start = 0, const int &finish = 9){
		//return the average time to test the $size numbers (in the range)
		double time_push = gen_int(size, start, finish);
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}

	pair<double, double> test_string(const int &size){
		//return the average time to test the $size strings (made by dictionary)
		double time_push = gen_string(size);
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}

	pair<double, double> test_class(const int &size){
		//return the average time to test the $size objects of class
		double time_push = gen_class(size);
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}

	double clear_box(){
		double time = clock();
		while (!box.empty()){
			box.pop();
		}
		return double(clock() - time)/CLOCKS_PER_SEC;
	}

	// for print
	string get_name_identify(){
		string info = typeid(Container).name();
		string answer = info.substr(info.find("sta"), info.find('<') - info.find("sta"));
		return (answer == "char")?"string":answer;
	}

	int    get_id_type(){
		string info = typeid(Container).name();
		string type = info.substr(info.rfind('<') + 1, info.find('>', info.rfind('<')) - (info.rfind('<') + 1));;
		char postfix = type[type.length() - 1];
		return (postfix == 't') ? 0 : ((postfix == 'r') ? 1 : 2);
	}

	pair<double, double> test_container(const int &size, const int &start = 0, const int &finish = 9){
		int id = get_id_type(); cout << id << endl;
		switch (id){
		case 0:   return test_int(size, start, finish); break;
		case 1:   return test_string(size);             break;
		case 2:   return test_class(size);              break;
		}
		return make_pair(-1.0, -1.0);

	}
	
public:
	

	void test_and_print(ofstream &out, const int &size, const int &start = 0, const int &finish = 9){
		pair <double, double> time = test_container(size,start,finish);
		out << setprecision(3) << fixed << "Time for " << get_name_identify() << " method PUSH is " << time.first << " and method POP is " << time.second << endl;
	}

};



int main(){

	// Initialization of containers
		
	/*stack       <int>    stack_int;
	stack       <string> stack_str;
	stack       <Node>   stack_node;

	stack_list  <int>    stack_list_int;
	stack_list  <string> stack_list_str;
	stack_list  <Node>   stack_list_node;

	stack_array <int>    stack_array_int;
	stack_array <string> stack_array_str;
	stack_array <Node>   stack_array_node;*/
	////////////////////////////

	
	// Initialization of test's shell
	
	test<stack       <int>   > stack_int_test;	
	test<stack       <string>> stack_str_test;
	test<stack       <Node>  > stack_node_test;

	test<stack_list  <int>   > stack_list_int_test;
	test<stack_list  <string>> stack_list_str_test;
	test<stack_list  <Node>  > stack_list_node_test;

	/*
	test<stack_array <int>   > stack_array_int_test;
	test<stack_array <string>> stack_array_str_test;
	test<stack_array <Node>  > stack_array_node_test;
	*/
	/////////////////////////////////
	
	
	// Initialization of constant
	int SIZE = 10000000;
	int START_RANGE  = 10000;
	int FINISH_RANGE = 90000;
	//////////////////////////////


	ofstream out("output.txt");
	stack_int_test.test_and_print(out, SIZE);
	stack_str_test.test_and_print(out, SIZE);
	stack_node_test.test_and_print(out, SIZE);

	stack_list_int_test.test_and_print(out, SIZE);
	stack_list_str_test.test_and_print(out, SIZE);
	stack_list_node_test.test_and_print(out, SIZE);

	/*stack_array_int_test.test_and_print(out, SIZE);
	stack_array_str_test.test_and_print(out, SIZE);
	stack_array_node_test.test_and_print(out, SIZE);*/
	
	out << endl;
	return 0;
}
