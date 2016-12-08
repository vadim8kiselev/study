#include <fstream>
#include <algorithm>
#include <vector>
using namespace std;

int main(){

	ifstream in("input.txt");
	int size; 
	in >> size;
	vector <pair<int, int>> v(size);
	
	int x, y;
	for (int i = 0; i < size; i++){
		in >> x >> y;
		v[i] = make_pair(x, y);
	}
	in.close();
	int count = 0;
	for (int i = 0; i < int(v.size()); i++){
		if (v[i].second < 0 ){
			count++;			
		}
	}

	ofstream out("output.txt");
		out << count << endl;
	out.close();

}
