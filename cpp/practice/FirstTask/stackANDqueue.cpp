#include <fstream>
#include <queue>
#include <stack>
 
using namespace std; 
 
int main(){	
	
	stack <int> st;
	queue <int> q;
	
	ifstream in("input.txt");
		int tmp;
		while (in >> tmp){
			if (tmp > 0){
			q.push(tmp);
			}
		}
	in.close();
	
	while (!q.empty()){
		if (q.front() & 1){
			st.push(q.front());
		}
		q.pop();
	}	
	ofstream out("output.txt");	
	
		while (!st.empty()){
			out << st.top() << " ";
			st.pop();
		}
	
	out.close();
}