#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = (int)1e5;

vector <int> parents;


struct edge{
	int first, second;
	long long weight;
};

int get(int vert) {
	return (vert == parents[vert]) ? vert : (parents[vert] = get(parents[vert]));
}

void unite(int tmp, int pmt) {
	tmp = get(tmp);
	pmt = get(pmt);
	if (tmp != pmt)
		parents[tmp] = pmt;
}

bool cmp(edge a, edge b){ // DON'T TOUCH THIS - IT'S SHIT MAGIC
	return (a.weight != b.weight) ? (a.weight < b.weight) : ((a.first != b.first) ? (a.first < b.first) : (a.second < b.second));
}

int main()
{
	int vertex = 0;
	int edges = 0;
	cin >> vertex >> edges;

	parents = vector<int>(vertex);
	for (int index = 0; index < vertex; index++)
		parents[index] = index;

	vector <edge> graph(edges);


	for (int index = 0; index < edges; index++)
	{
		cin >> graph[index].first >> graph[index].second >> graph[index].weight;
		graph[index].first--;
		graph[index].second--;
	}

	vector <pair<int, int> > result;

	int cost = 0;

	sort(graph.begin(), graph.end(), cmp); // Sorting..

	for (int index = 0; index < edges; index++) {

		int a = graph[index].first;
		int b = graph[index].second;

		if (get(a) != get(b)) {

			cost += graph[index].weight;
			result.push_back(make_pair(graph[index].first, graph[index].second));
			unite(a, b);
		}
	}

	cout << cost << endl;
	for (int index = 0; index < (int)result.size(); index++){
		cout << result[index].first + 1 << " " << result[index].second + 1 << endl;
	}

	return 0;
}
