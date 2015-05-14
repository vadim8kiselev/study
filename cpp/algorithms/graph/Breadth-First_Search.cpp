#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

int main()
{
	int size = 0;
	int vertex = 0;
	cin >> size >> vertex;

	vector <vector<int> > graph(size);

	vector <bool> used(size);

	vector <int> parents(size);

	vector <int> dist(size);

	int tmp = 0;
	int tmp1 = 0;
	for (int index = 0; index < vertex; index++)
	{
		cin >> tmp >> tmp1;		
		graph[tmp-1].push_back(tmp1-1);
		graph[tmp1-1].push_back(tmp-1);
	}


	int start = 0;
	int finish = 0;
	cin >> start >> finish;
	start--; finish--;

	////INIT////////////
	parents[start] = -1;
	used[start] = true;
	queue <int> q;
	q.push(start);
	////////////////////

	while (!q.empty())
	{
		int val = q.front();
		q.pop();

		for (int index = 0; index < (int)graph[val].size(); index++)
		{
			int vert = graph[val][index];

			if (!used[vert])
			{
				used[vert] = true;
				q.push(vert);
				dist[vert] = dist[val] + 1;
				parents[vert] = val;
			}
		}
	}

	if (!used[finish])
	{
		cout << "No path solution!" << endl;
		return 0;
	}

	vector<int> path;
	cout << "Path is " << dist[finish] << endl;
	while (finish != -1)
	{
		path.push_back(finish);
		finish = parents[finish];
	}
	reverse(path.begin(),path.end());
	for (int index = 0; index < (int)path.size(); index++)
		cout << path[index] + 1 << " ";
}
