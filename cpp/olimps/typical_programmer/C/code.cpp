#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

int main()
{
	int bound;
	cin >> bound; 
	string x; 
	getline(cin, x);
	getline(cin, x);

	if (x.length() < bound)
	{
		cout << "NO";
		return 0;
	}
	int up = 0;
	int down = 0;
	int dig = 0;

	for (int i = 0; i < (int)x.length(); i++)
	{
		if (x[i] >= 'A' && x[i] <= 'Z')
			up++;
		else
			if (x[i] >= 'a' && x[i] <= 'z')
				down++;
			else
				if (x[i] >= '0' && x[i] <= '9')
					dig++;
				else
				{
					cout << "NO";
					return 0;
				}
	}	

	if (!up || !down || !dig)
	{
		cout << "NO";
		return 0;
	}

	cout << "YES";
	return 0;
}
