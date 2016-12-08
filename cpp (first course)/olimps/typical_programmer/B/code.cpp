#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

void wrong(){
	for (int i = 0; i < 80; i++)
		cout << 0;
}
int main()
{		
	string x ;
	cin >> x;

	if (x.length() != 80)
	{
		wrong(); return 0;
	}
	string first = x.substr(0, 4);
	string second = x.substr(x.length() - 4, x.length());
	if (first != "1111" && second != "1111" || first == "1111" && second == "1111")
	{
		wrong(); return 0;
	}

	if (first == "1111" && second != "1111")
		cout << x;
	else if (first != "1111" && second == "1111")
	{
		reverse(x.begin(), x.end());
		cout << x;
	}

	return 0;
}
