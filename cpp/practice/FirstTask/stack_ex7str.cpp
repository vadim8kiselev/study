#include <fstream>
#include <map>
#include <vector>
#include <string>
using namespace std;

int main(){	
	ifstream in("input.txt");
	ofstream out("output.txt");
	
	int size, count;
	in >> size >> count;
	string command; getline(in, command); // just needed
	
	map <string,vector<string> > v; // i know, it might be scary :( scrolling down

	for (int i = 0; i < count; i++){
		getline(in, command, ')'); 
				string trash; getline(in, trash); // just needed
		string post = command.substr(0, 3); //mark of command		
		string tmp = command.substr(4); // key for pop and top
		if (post == "PUS"){			
			int posf = command.find(',');		
			v[command.substr(5, posf - 5)].push_back(command.substr(posf + 1) );//map insert		
		}
		else {		// branch for output pop and top
			out << v[tmp][v[tmp].size() - 1] << " ";			
		}
		 if (post == "POP"){ // and pop_back for pop
			 v[tmp].pop_back();
		}		
	}
	in.close();
	out.close();
}
