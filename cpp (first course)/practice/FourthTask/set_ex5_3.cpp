#include <fstream>
#include <string>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

int main(){

	ifstream in("input.txt");
		
		set <string> answer;
		string tmp; 
		vector <string> ex;
		while (in.peek() != EOF){
			in >> tmp;		
			ex.push_back(tmp);
			if (tmp[tmp.length() - 1] == '?' || tmp[tmp.length() - 1] == '!'){			
				for (int i = 0; i < ex.size()-1; i++)
					answer.insert(ex[i]); // added words in dictionary
				answer.insert(ex[ex.size() - 1].substr(0, ex[ex.size() - 1].length() - 1));	// and delete dot
			}
			else if (tmp[tmp.length() - 1] == '.')		
				ex.clear(); // solve the conditions				
		}

	in.close();

	
	ofstream out("output.txt");

		out << answer.size() << endl;
			for (auto i : answer)
				out << i << " ";	

	out.close();
}
