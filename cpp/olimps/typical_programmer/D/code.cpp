#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int flag = 0;
int tmp = 0;
vector<int> memory(30000);

void interpretator(string x){
	
	int q;	
	for (int i = 0; i < (int)x.length(); i++)
	{
		if (x[i] == ',')
		{
			cin >> q; memory[flag] = q%256;
		}
		else if (x[i] == '.')
			cout << memory[flag] << endl;
		else if (x[i] == '=')
		{
			if (flag == 0)
			{
				memory[flag] = memory[29999];
			}
			else
				memory[flag] = memory[flag - 1];
		}
		else if (x[i] == '0')
			memory[flag] = 0;
		else if (x[i] == '!')
			tmp = memory[flag];
		else if (x[i] == '?')
			memory[flag] = tmp%256;
		else if (x[i] == '*')
		{
			if (flag == 0)
			{
				memory[flag] = (memory[flag] * memory[29999]) % 256;
			}
			else
				memory[flag] = (memory[flag] * memory[flag - 1]) % 256;
		}
		else if (x[i] == '/')
		{
			if (flag == 0)
			{
				memory[flag] = memory[flag] / memory[29999];
			}
			else
				memory[flag] = memory[flag] / memory[flag - 1];
		}
		else if (x[i] == '+')
		{
			if (memory[flag] == 255)
				memory[flag] = 0;
			else
				memory[flag]++;
		}
		else if (x[i] == '-')
		{
			if (memory[flag] == 0)
				memory[flag] = 0;
			else
				--memory[flag];
		}
		else if (x[i] == '>')///////////flag
		{
			if (flag == 29999)
				flag = 0;
			else
				flag++;
		}
		else if (x[i] == '<')
		{
			if (flag == 0)
				flag = 29999;
			else
				flag--;
		}
		else if (x[i] == '|')
			flag = 0;
		else if (x[i] == '^')
			cout << tmp << endl;///////////////////////
		else if (x[i] == '$')
			tmp = flag;
		else if (x[i] == '[')
		{
			int start = i + 1; int p = 0;
			int finish = x.find(']', start);
			for (int w = start; w < (int)x.length(); w++)
			{
				if (x[w] == '[') p++;
				if (x[w] == ']')
				{
					if (p == 0)
					{
						finish = w - 1; break;
					}
					else
						p--;
				}
			}
			while (memory[flag]){
				interpretator(x.substr(start, finish - start + 1));
			}
			i += finish - start + 2;
		}
		else if (x[i] == '(')
		{
			int start = i + 1; int p = 0;
			int finish = x.find(')', start);
			for (int w = start; w < (int)x.length(); w++)
			{
				if (x[w] == '(') p++;
				if (x[w] == ')')
				{
					if (p == 0)
					{
						finish = w - 1; break;
					}
					else
						p--;
				}
			}
			if (memory[flag]){
				interpretator(x.substr(start, finish - start + 1));
			}
			
			i += finish - start + 2;
		}
		
		
	}
	
}
int main()
{	
	
	string x;
	cin >> x;
	interpretator(x);
	
	return 0;
}
