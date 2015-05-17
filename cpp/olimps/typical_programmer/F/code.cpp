#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

#pragma warning(disable : 4996)
long long p;
long long p2;
long long h;

int n;

string s;

char litter[10] = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' };

vector <string> v;

void gen(int pos)
{
	if (pos == n)
	{
		long long ch = (s[0] - 'a');
		long long pr = 1;
		for (int i = 1; i < n; i++)
		{
			pr *= p;
			ch += (s[i] - 'a')*pr;			
		}
		if (ch %p2== h)
			{
				  for (int j = 0; j < (int)s.length(); j++) 
		      	printf("%c", s[i]);
	      	printf("\n");
			}
		return;
	}
	for (int i = 0; i < 10; i++)
	{
		s.push_back(litter[i]);
		gen(pos + 1);
		s.pop_back();
	}
}

int main()
{
	scanf("%d", &n);
	scanf("%lld", &p);
	scanf("%lld", &p2);
	scanf("%lld", &h);

	gen(0);

	return 0;
}
