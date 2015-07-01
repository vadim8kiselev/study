#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;


struct Node{
	string name;
	string label;
	int num;

	void read(ifstream &in){
		in >> name >> label >> num;
	}
	void show(ofstream &out){		
			out << name << " " << label << " " << num << endl;
	}
};

bool cmp(Node a, Node b){
	if (a.label.compare(b.label))
		return a.label < b.label;
	else
		return a.num > b.num;		
}
string x, y;
bool isright(Node a){	
		return a.name == x || a.name == y;
}

int main(){

	ifstream in("input.txt");
	int n;
	in >> n;
	vector<Node> v;
	while (n && in.peek() != EOF){
		Node tmp;
		tmp.read(in);
		v.push_back(tmp);
		n--;
	}

	in >> x >> y;
	in.close();

	v.erase(remove_if(v.begin(), v.end(), isright), v.end());

	stable_sort(v.begin(), v.end(), cmp);

	ofstream out("output.txt");

	for (int i = 0; i < min(10, (int)v.size()); i++)
		v[i].show(out);

	out.close();
}
