#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	int vertex = 0;
	int edges = 0;
	cin >> vertex;
	cin >> edges;

	int start = 0;
	int finish = 0;
	cin >> start;
	cin >> finish;
	start--;
	finish--;

	vector<vector<pair<int, int>>> graph(vertex);

	int first = 0;
	int second = 0;
	int weight = 0;
	for (int index = 0; index < (int)edges; index++)
	{
		cin >> first;  first--;
		cin >> second;  second--;
		cin >> weight;
		graph[first].push_back(make_pair(second, weight));
		graph[second].push_back(make_pair(first, weight));
	}

	vector<int> dist(vertex, int(1e8));

	vector<bool> used(vertex);

	dist[start] = 0;

	for (int jndex = 0; jndex < (int)vertex; jndex++)
	{
		int vert = -1;

		for (int index = 0; index < (int)vertex; index++)
		{
			if (!used[index] && (vert == -1 || dist[index] < dist[vert]))
				vert = index;
		}

		if (!((vert == -1) || (dist[vert] == int(1e8))))
		{
			for (int index = 0; index < (int)graph[vert].size(); index++)
			{
				int to = graph[vert][index].first;

				if (dist[to] > dist[vert] + graph[vert][index].second)
				{
					dist[to] = dist[vert] + graph[vert][index].second;
				}
			}
			used[vert] = true;

		}
		else break;

	}

	if (!used[finish])
		cout << -1 << endl;
	else 
		cout << dist[finish] << endl;

	return 0;
}
