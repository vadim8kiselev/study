#include <fstream>
#include <list>
using namespace std;

int main(){
	list <int> l;
	ifstream in("input.txt");
		int tmp;
		while (in >> tmp){
			l.push_back(tmp);
		}
	in.close(); 
	
	int y = l.back(); // worst solution ever
	l.pop_back();
	int x = l.back(); // in reverse order
	l.pop_back();	
	
	for (list<int>::iterator it = l.begin(); it != l.end(); it++)
		if (*it == x){
			l.emplace(++it, y); // crutch right-insertion
			--it;
		}
	
	ofstream out("output.txt");		
			for (auto i : l)
				out << i << " ";
	out << endl;
}
