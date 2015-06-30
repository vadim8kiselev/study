#include <fstream>
#include <algorithm>
#include <vector>
using namespace std;

double getLength(int x, int y){
	return sqrt(double(x*x + y*y));
}

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
	double max = 0;
	pair<int, int> answer;
	for (int i = 0; i < int(v.size()); i++)
		if (v[i].second < 0 && v[i].first > 0 ) // in fourth quarter
			if (getLength(v[i].first, v[i].second) > max) // max distance
			{
				max = getLength(v[i].first, v[i].second);
				answer = v[i];
			}

	ofstream out("output.txt");
		out << answer.first << " " << answer.second << endl;
	out.close();

}
