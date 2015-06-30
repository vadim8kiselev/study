#include <fstream>
#include <list>
#include <assert.h>
using namespace std;

int main(){

	list <int> l;

	ifstream in("input.txt");
		int tmp;
		while (in >> tmp){
			l.push_back(tmp);
		}
		in.close(); 
	
	int y = l.back();
	l.pop_back();
	int x = l.back();
	l.pop_back();
	
	
	for (list<int>::iterator it = l.begin(); it != l.end(); it++){

		if (*it == x){
			l.emplace(++it, y);
			--it;
		}
	}
	ofstream out("output.txt");
	out << x << " " << y<<endl;
		for (auto i : l)
			out << i << " ";
	out << endl;
}
