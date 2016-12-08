#include <fstream>
#include <queue>
#include <stack>
 
using namespace std; 
 
int main(){	
	
	stack <int> st;
	queue <int> q;
	
	ifstream in("input.txt");
		int tmp;
		while (in >> tmp)
			if (tmp > 0) // positive condition
				q.push(tmp);		
		
	in.close();
	
	while (!q.empty()){
		if (q.front() & 1){ // odd condition 
			st.push(q.front()); // push it in stack
		}
		q.pop();
	}	
	ofstream out("output.txt");	
	
		while (!st.empty()){
			out << st.top() << " ";
			st.pop(); // and print answer
		}
	
	out.close();
}
