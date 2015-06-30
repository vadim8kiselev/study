#include <fstream>
#include <queue>
 
using namespace std; 
 
int main(){	
	queue  <int> first;	
	queue  <int> second;	
	queue  <int> third;	
	ifstream in("input.txt");
		int n; in >> n;
		int a, b; in >> a >> b;
		int tmp;
		for (int index = 0; index < n; index++){
			in >> tmp;
			(tmp < a)? first.push(tmp) : (tmp > b)? third.push(tmp): second.push(tmp);
		}
	in.close();
	
	ofstream out("output.txt");	
		while (!second.empty()){
			out << second.front() << " ";
			second.pop();
		}
		while (!first.empty()){
			out << first.front() << " ";
			first.pop();
		}
		while (!third.empty()){
			out << third.front() << " ";
			third.pop();
		}
	out.close();
}
