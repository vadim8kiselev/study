#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main()
{
	int vertex = 0;
	int edges = 0;
	cin >> vertex;
	cin >> edges;

	vector <vector<int> > g(vertex);

	vector <bool> used(vertex);

	queue <int> q;

	vector <int> color(vertex);

	int tmp = 0;
	int tmp1 = 0;

	for (int index = 0; index < edges; index++)
	{		
		cin >> tmp;
		cin >> tmp1;		
		g[tmp - 1].push_back(tmp1 - 1);
		g[tmp1 - 1].push_back(tmp - 1);
	}

	int length = 0;
	for (int index = 0; index < vertex; index++)
	{
		if (!used[index])
		{
			length++;
			used[index] = true;
			q.push(index);

			while (!q.empty())
			{

				int val = q.front();
				color[val] = length;
				q.pop();


				for (int jndex = 0; jndex < (int)g[val].size(); jndex++)
				{
					if (!used[g[val][jndex]])
					{
						used[g[val][jndex]] = true;
						q.push(g[val][jndex]);
					}
				}
			}
		}
	}

	cout << length << endl;
	for (int index = 0; index < vertex; index++)
		cout << color[index] << " ";
	cout << endl;
}
