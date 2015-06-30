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
		out << name<< " " << second_name<< " " << third_name<< " " << number<<endl;
	}
};
char x, y;
inline bool compare(string a){
	return a[0] == x && a[1] == y;
}

bool print(Sub a){
	if (compare(a.getNumber())){
		a.show();
		return true;
	}
	return false;
}

int main(){	

	ifstream in("input.txt");
	int n;
	in >> n;
	vector <Sub> v;
	string name, second_name, third_name, number;
	
	while (n && in.peek() != EOF){
		in >> name >> second_name >> third_name >> number;
		Sub tmp(name, second_name, third_name, number);
		v.push_back(tmp);
		n--;
	}
	in >> x >> y;
	in.close();	

	out << count_if(v.begin(), v.end(), [](Sub a){return compare(a.getNumber()); }) << endl;

	for_each(v.begin(), v.end(), print);
}
