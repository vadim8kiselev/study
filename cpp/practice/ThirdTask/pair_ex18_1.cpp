#include <fstream>
#include <algorithm>
#include <vector>
using namespace std;

int main(){
	ifstream in("input.txt");
	int n; 
	in >> n;
	vector <pair<int, int>> v(n);
	
	int x, y;
	for (int i = 0; i < n; i++){
		in >> x >> y;
		v[i] = make_pair(x, y);
	}
	in.close();

	for (int i = 0; i < int(v.size()); i++){
		if (abs(v[i].first) > 5 || abs(v[i].second) > 5){
			v.erase(v.begin() + i);
			i--;
		}
	}

	ofstream out("output.txt");
		for (auto i : v)
			out << i.first << " " << i.second << endl;
	out.close();
}
