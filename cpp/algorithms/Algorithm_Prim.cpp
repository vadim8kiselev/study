#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{

	int vertex = 0;
	int edges = 0;
	cin >> vertex >> edges;

	vector <vector <int> > graph(vertex, vector<int>(vertex, (int)1e5));

	vector <int> mst(vertex, (int)1e5);

	vector <int> sel(vertex, -1);

	vector <bool> used(vertex);
	mst[0] = 0;

	int first = 0;
	int second = 0;
	int weight = 0;
	for (int index = 0; index < edges; index++)
	{
		cin >> first;  first--;
		cin >> second;  second--;
		cin >> weight;

		graph[first][second] = min(weight, graph[first][second]);
		graph[second][first] = graph[first][second];
	}

	vector <pair<int, int> > answer;

	int cost = 0;

	for (int index = 0; index < vertex; index++){
		int vert = -1;

		for (int jndex = 0; jndex < vertex; jndex++)
			if (!used[jndex] && (vert == -1 || mst[jndex] < mst[vert]))
				vert = jndex;

		used[vert] = true;

		if (sel[vert] != -1)
			answer.push_back(make_pair(sel[vert], vert));
		cost += mst[vert];

		for (int to = 0; to < vertex; to++)
			if (graph[vert][to] < mst[to]) {
				mst[to] = graph[vert][to];
				sel[to] = vert;
			}
	}

	cout << cost << endl;
	for (int index = 0; index < (int)answer.size(); index++)
		cout << answer[index].first + 1 << " " << answer[index].second + 1 << endl;


	return 0;
}
