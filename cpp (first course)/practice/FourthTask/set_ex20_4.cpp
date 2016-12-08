#include <fstream>
#include <string>
#include <sstream>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

int main(){

	ifstream in("input.txt");
		
		vector <multiset<int> > v;
		string pars;
	    //while stringstream not mainstream
		while(in.peek() != EOF){
			getline(in, pars); // pars strings for newbee
			stringstream ss(pars); // so brittle system
			int tmp;
			multiset<int> val; // push multisets
			while (ss >> tmp){
				val.insert(tmp);
			}
			v.push_back(val);
		}
	in.close();

	ofstream out("output.txt");

		for (int i = 0; i <= v.size()/2; i++,out<<endl)
			for (multiset<int>::iterator it = v[i].begin(); it != v[i].end(); it++)
				// if.. just.. it is what my task to want 
				if (v[i].count(*it) == 1 && v[v.size()-1-i].count(*it) ==1 )
					out << *it << " ";
		
		out << endl;

	out.close();
}
