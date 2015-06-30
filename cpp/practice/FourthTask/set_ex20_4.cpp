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
	
	while(in.peek() != EOF){
		getline(in, pars);
		stringstream ss(pars);
		int tmp;
		multiset<int> val;
		while (ss >> tmp){
			val.insert(tmp);
		}
		v.push_back(val);
	}
	in.close();



	ofstream out("output.txt");
	for (int i = 0; i <= v.size()/2; i++,out<<endl){

		for (multiset<int>::iterator it = v[i].begin(); it != v[i].end(); it++){

			if (v[i].count(*it) == 1 && v[v.size()-1-i].count(*it) ==1 ){
				out << *it << " ";
			}
		}

	}
    out << endl;

	out.close();
}
