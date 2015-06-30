#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

ofstream out("output.txt");

string a, b;

struct Node{

	string name;
	string label;
	int num;

	void read(ifstream &in){
		in >> name >> label >> num;
	}

	void show(){
		if (name != a && name != b)
			out << name << " " << label << " " << num << endl;
	}
};

bool cmp(Node a, Node b){
	if (a.label.compare(b.label))
		return a.label < b.label;
	else
		if (a.num > b.num)
			return a.num > b.num;
		else
			return false;
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
	
	in >> a >> b;
	in.close();

	sort(v.begin(), v.end(), cmp);
	
	for (auto i : v)
		i.show();

	out.close();
}
