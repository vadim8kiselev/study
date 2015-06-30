#include <fstream>
#include <iostream>
#include <algorithm>
#include <string>
#include <map>

using namespace std;

bool isDigit(string x){

	if (x[0] == '+' || x[0] == '-') // without mark
		x = x.substr(1, x.length() - 1);
	
	for (auto i : x)
		if (isalpha(i) && i != '.') // dot for double 
			return false;

	if (x[0] == '0'){
		for (int i = (int)'1'; i <= (int)'9'; i++)
			if (binary_search(x.begin(), x.end(), char(i)))
				return true;
		return false;
	}
	return true;
}
/*
	000 - isn't digit
	but 001 or 0000024152 is digit
*/
int main(){

	

	map <string, int> dictionary;

	ifstream in("input.txt");
		
		//assert (in.peek() != EOF);
		int count;
		in >> count;
		//assert (count>0);

		string val; // it might be few_spaces problem, than i can use sstream, but it only kill my time
		while (in.peek() != EOF){			
			in >> val;
			dictionary[val]++;
		}
	in.close();

	ofstream out("output.txt");
		for (map <string, int>::iterator it = dictionary.begin(); it != dictionary.end(); it++)
			if (it->second == count && isDigit(it->first))	{				
				//dictionary.erase(dictionary.find(it->first)); // not solved
				out << it->first << "\n"; // change direction
			}
	
	out.close();
}