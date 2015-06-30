#include <fstream>
#include <algorithm>
#include <vector>
using namespace std;

double getLength(int x, int y){
	return sqrt(double(x*x + y*y));
}

bool cmp(pair<int, int> a, pair<int, int> b){

	if (getLength(a.first, a.second) <= getLength(b.first, b.second))
		return true;
		
		return false;
}

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
	
	sort(v.begin(), v.end(), [](const pair<int, int> &a, const pair<int, int> &b) {
		if (getLength(a.first, a.second) != getLength(b.first, b.second))
			return getLength(a.first, a.second) < getLength(b.first, b.second);
		else return getLength(a.first, a.second) > getLength(b.first, b.second);
	});


	ofstream out("output.txt");
	for (auto i : v)
		out << i.first << " " << i.second << endl;
	out.close();

}
