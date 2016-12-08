#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

int main(){	

	ifstream in("input.txt");

		int size;
		in >> size;
		vector< set<int> > v(size);

		int tmp;
		for (int i = 0; i < size; i++){
			in >> tmp;
			while (tmp){
				if (v[i].count(tmp % 10)) // if tmp%10 in v[i]
					v[i].erase(tmp % 10); // "even-condition" -> erase			
				else
					v[i].insert(tmp % 10); // "odd-condition" -> insert			
				tmp /= 10;
			}
		}

	in.close();
	
	ofstream out("output.txt");

		for (int i = 0; i < size; i++, out << endl << i << " -> ")	
			for (set<int>::iterator it = v[i].begin(); it != v[i].end(); it++)
				out << *it << " ";	

	out.close();
}
