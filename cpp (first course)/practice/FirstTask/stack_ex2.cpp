#include <fstream>
#include <stack>
#include <string>
 
using namespace std;
 
string base = "!;?.,-:;)(\"'";
 
int main(){	
	stack<char> st;	
	ifstream in("input.txt");
		string tmp;
		while (in.peek() != EOF){
			getline (in,tmp);
			for (int index = 0; index < (int)tmp.length(); index++)
				if (!(base.find(tmp[index]) == string::npos)) // if tmp's element in base
					st.push(tmp[index]);	// push it in stack
		}
	in.close();
	
	ofstream out("output.txt");
		while (!st.empty()){
			out << st.top(); // print stack
			st.pop();
		}
	out.close();
}
