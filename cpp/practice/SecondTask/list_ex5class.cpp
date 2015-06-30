#include <fstream>
#include <list>
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
		name = "?" + name;  // concatenation of string
	}
	void print(ofstream &out){
		out << type << " " << name << endl;
	}
};

int main(){
	list <Games> l;

	ifstream in("input.txt");
		string type, name;
		while (in.peek() != EOF){
			in >> type >> name;
			Games tmp(type, name);
			l.push_back(tmp);
		}
	in.close(); 
		
	for (list<Games>::iterator it = l.begin(); it != l.end();){
		if (it->getType() == "Shooter")
			it = l.erase(it); // return iterator after this point
		else
			it++;		
	}
	l.push_back(l.front()); // push front element it back of list
	l.pop_front();	
	
	ofstream out("output.txt");
		for (auto i : l)
			i.print(out);
	out.close();	
}
