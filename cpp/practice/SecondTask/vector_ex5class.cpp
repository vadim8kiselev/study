#include <fstream>
#include <vector>
#include <string>
using namespace std;

class Games{
	string type;
	string name;
public:
	
	Games(string a, string b) : type(a), name(b){}

	string getType(){
		return type;
	}
	string getName(){
		return name;
	}
	void setQuest(){
		name = "?" + name;
	}
	void print(ofstream &out){
		out << type << " " << name << endl;
	}
};

int main(){
	
	vector <Games> v;

	ifstream in("input.txt");
		string type, name;
		while (in.peek() != EOF){
			in >> type>> name;
			Games tmp(type, name);
			v.push_back(tmp);
		}
	in.close(); 
	
	for (auto &i : v)   
		if (!i.getType().compare("Quest")) // equal strings
			i.setQuest(); // solve condition

	swap(v[0], v[v.size() - 2]);
	
	ofstream out("output.txt");	
		for (auto i : v)
			i.print(out);
	out.close();
	
}
