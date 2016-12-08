#include <stack>
#include <fstream>
#include <iostream>
#include <functional>
#include <algorithm>
#include <string>
#include <vector>
#include <iomanip>
#include <time.h>
#include "omp.h"

using namespace std;

// Initialization of constant
#define SIZE  1000  // assert(SIZE % 10 == 0)
int START_RANGE = 0;
int FINISH_RANGE = 9;
//////////////////////////////

vector <string> console;
static int a = 0;
static int b = 0;
void loading(string proc, int pointer){
	
	if ((pointer+1) % (SIZE / 10) == 0)
	{
		a++;
	}
	
	cout << "loading " << proc << ":";
	int procent = a / (b + 1);
	for (int i = 1; i <= procent; i++)
		cout << '#';
	for (int i = 1; i <= 10 - procent; i++)
		cout << '.';	
	system("cls");

	for (int i = 0; i < (int)console.size(); i++)
		cout << console[i];
	cout << endl;
}

void complete(string proc){
	b++;
	string ans = "\nloading " + proc + ":########## complete\n";
	console.push_back(ans);
}

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
class Stack_array{

	Type *s;
	int index;
	int size;
public:
	Stack_array() : index(0), size(SIZE), s(new Type[SIZE]){ }

	void  push(Type value)
	{
		if (index > size - 1) return;
		s[index++] = value;
	}
	void pop()
	{
		if (index < 0) return ;
		--index;
		return ;
	}
	Type top()
	{
		if (index < 0) return; 
		return  s[size - 1];
	}
	bool empty() { 
		return index < 0; 
	}
};

template <class Type> 
class Stack_list{
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

	Stack_list() : head(nullptr){}

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

	~Stack_list(){}
};

class Test{
	virtual double gen() = 0;
	virtual pair<double, double> test() = 0;
	virtual double clear_box() = 0;
};

template <class Container>
class IntegerTest : public Test{
	Container box;
	string name;
	

	double gen(){
		srand((int)time(NULL));

		string ind = name + " push"; 

		double time = clock();
		for (int index = 0; index < SIZE; index++){
			box.push(rand() % FINISH_RANGE + START_RANGE);
			loading(ind, index);
		}
		complete(ind);

		return double(clock() - time) / CLOCKS_PER_SEC;
	}
		
	pair<double, double> test(){
		//return the average time to test the $SIZE numbers (in the range)
		double time_push = gen();
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}
	
	double clear_box(){
		string ind = name + " pop ";
		double time = clock();
		int index = 0;
		while (!box.empty()){
			loading(ind, index);
			box.pop();
			index++;
		}
		complete(ind);
		return double(clock() - time)/CLOCKS_PER_SEC;
	}
		
public:	

	IntegerTest(string n) :name(n){}


	pair<string, pair<double, double>> operator ()(){
		pair <double, double> time = test();
		return make_pair(name, time);
		//out << setprecision(3) << fixed << "Time for " << name << " method PUSH is " << time.first << " and method POP is " << time.second << endl << endl;
	}

};

template <class Container>
class StringTest : public Test{
	Container box;
	string name;
	
	double gen(){
		ifstream in("input.txt");
		vector <string> words;
		string tmp, trash;
		int count = SIZE;
		while (count && in >> tmp){
			getline(in, trash); // only first word pushing
			words.push_back(tmp);
			count--;
		}
		random_shuffle(words.begin(), words.end());
		in.close();
		string ind = name + " push";
		double time = clock();
		for (int index = 0; index < SIZE; index++){
			box.push(words[index % (words.size() - 1)]);
			loading(ind, index);
		}
		complete(ind);
		return double(clock() - time) / CLOCKS_PER_SEC;
	}

	pair<double, double> test(){
		//return the average time to test the $SIZE strings (made by dictionary)
		double time_push = gen();
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}
		
	double clear_box(){
		double time = clock();
		string ind = name + " pop ";
		int index = 0;
		while (!box.empty()){
			loading(ind, index);
			box.pop();
			index++;
		}
		complete(ind);
		return double(clock() - time) / CLOCKS_PER_SEC;
	}		

public:
	
	StringTest(string n) : name(n){}
		
	pair<string, pair<double, double>> operator ()(){
		pair <double, double> time = test();
		return make_pair(name, time);
		//out << setprecision(3) << fixed << "Time for " << name << " method PUSH is " << time.first << " and method POP is " << time.second << endl << endl;
	}

};

template <class Container>
class ClassTest : public Test{
	Container box;
	string name;
	
	double gen(){
		ifstream in("input.txt");
		vector <Node> objects;
		Node tmp;
		int count = SIZE;
		while (count && in.peek() != EOF){
			tmp.read(in);
			objects.push_back(tmp);
			count--;
		}
		random_shuffle(objects.begin(), objects.end());
		in.close();
		string ind = name + " push";
		double time = clock();
		for (int index = 0; index < SIZE; index++){
			box.push(objects[index%(objects.size()-1)]);
			loading(ind, index);
		}
		complete(ind);
		return double(clock() - time) / CLOCKS_PER_SEC;
	}
		
	pair<double, double> test(){
		//return the average time to test the $SIZE objects of class
		double time_push = gen();
		double time_pop = clear_box();
		return make_pair(time_push, time_pop);
	}

	double clear_box(){
		double time = clock();
		string ind = name + " pop ";
		int index = 0;
		while (!box.empty()){
			box.pop();
			loading(ind, index);
			index++;
		}		
		complete(ind);
		return double(clock() - time) / CLOCKS_PER_SEC;
	}
		
public:
	ClassTest(string n) : name(n){}

	pair<string,pair<double,double>> operator ()(){
		pair <double, double> time = test();
		return make_pair(name, time);
		//out << setprecision(3) << fixed << "Time for " << name << " method PUSH is " << time.first << " and method POP is " << time.second << endl << endl << endl;
	}

};

int main(){
	
	// Initialization of test's shell
	
	IntegerTest<stack       <int>   >       stack_int_test("stack_int        "); //5
	StringTest <stack       <string>>       stack_str_test("stack_str        "); //6
	ClassTest  <stack       <Node>  >      stack_node_test("stack_class      "); //4

	IntegerTest<Stack_list  <int>   >  stack_list_int_test("stack_xlist_int  "); //8
	StringTest <Stack_list  <string>>  stack_list_str_test("stack_xlist_str  "); //9
	ClassTest  <Stack_list  <Node>  > stack_list_node_test("stack_xlist_class"); //7
	
	IntegerTest<Stack_array <int>   > stack_array_int_test("stack_array_int  "); //2
	StringTest <Stack_array <string>> stack_array_str_test("stack_array_str  "); //3
	ClassTest  <Stack_array <Node>  >stack_array_node_test("stack_array_class"); //1
	
	/////////////////////////////////
	
	function<pair<string, pair<double, double>>()> array[9];
		array[0] = stack_int_test;
		array[1] = stack_str_test;
		array[2] = stack_node_test;		
		array[3] = stack_list_int_test;
		array[4] = stack_list_str_test;
		array[5] = stack_list_node_test;	
		array[6] = stack_array_int_test;
		array[7] = stack_array_str_test;
		array[8] = stack_array_node_test;

		vector < pair<string, pair<double, double>> > v; // Inception

//#pragma omp parallel for
	for (int i = 0; i < 9; i++)
				v.push_back(array[i]());
		
	sort(v.begin(), v.end()); // Pure magic. It's sorting in lexicographic order


	ofstream out("output.txt");

	for (vector < pair<string, pair<double, double>> >::iterator it = v.begin(); it != v.end(); it++)
		out << setprecision(3) << fixed << "Time for " << it->first << " method PUSH is " << it->second.first << " \tand method POP is " << it->second.second << endl;
	
	out << endl;
	return 0;
}
