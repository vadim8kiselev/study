#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

int main(){	

	ifstream in("input.txt");

	int n;
	in >> n;
	vector< set<int> > v(n);

	int tmp;
	for (int i = 0; i < n; i++){
		in >> tmp;
		while (tmp){
			if (v[i].count(tmp % 10))
				v[i].erase(tmp % 10);			
			else
				v[i].insert(tmp % 10);			
			tmp /= 10;
		}
	}

	in.close();
	
	ofstream out("output.txt");

	for (int i = 0; i < n; i++, out << endl)
	{
		out << i << " -> ";
		for (set<int>::iterator it = v[i].begin(); it != v[i].end(); it++)
			out << *it << " ";
	}

	out.close();
}
