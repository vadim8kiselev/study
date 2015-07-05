#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

ofstream out("output.txt");
class Sub{
	string name;
	string second_name;
	string third_name;
	string number;

public:
	// it was ending of day, copy&paste help me, hope it's not problem
	Sub(string name,
		string second_name,
		string third_name,
		string number){
		this->name = name;
		this->second_name = second_name;
		this->third_name = third_name;
		this->number = number;
	}

	string getNumber(){
		return number;
	}

	void show(){
		out << name << " " << second_name << " " << third_name << " " << number << endl;
	}
};

char x, y; // global again :(
inline bool compare(string a){
	return a[0] == x && a[1] == y;
}

bool print(Sub a){
	if (compare(a.getNumber()) ){
		a.show();
		return true;
	}
	return false;
}

int main(){

	ifstream in("input.txt");
	int size;
	in >> size;
	int n = size;
	vector <Sub> v;
	string name, second_name, third_name, number;

	while (size && in.peek() != EOF){
		in >> name >> second_name >> third_name >> number;
		Sub tmp(name, second_name, third_name, number); // against setters
		v.push_back(tmp);
		size--;
	}
	in >> x >> y;
	in.close();

	// i'm trying to use "new" standart of c++
	out << count_if(v.begin(), v.end(), [](Sub a){return compare(a.getNumber()); }) << endl;
	// with lambda, anyway

	// and once again
	//for_each(v.begin(), v.end(), print);
	
	for (int i = 0; i < n; i++){
		
		if (compare(v[i].getNumber()))
			out << i + 1 << "\n";
	}
}
