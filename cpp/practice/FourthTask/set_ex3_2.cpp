#include <fstream>
#include <string>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

int main(){

	ifstream in("input.txt");

	int n;
	in >> n;
	vector< set<char> > v;

	string tmp; getline(in, tmp);
	while (in.peek() != EOF){
		getline(in, tmp);
		set<char> x;
		for (auto i : tmp)
			x.insert(i);
		v.push_back(x);
	}
	in.close();

	
	ofstream out("output.txt");

	for (int i = 0; i < v.size(); i++,out<<endl)
		for (set<char>::iterator it = v[i].begin(); it != v[i].end(); it++){

			if (islower(*it) && !v[i].count(toupper(*it)))
				out << *it << " ";
		}
	

	out.close();
}
