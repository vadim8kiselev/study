#include <iostream>
#include <vector>
using namespace std;

int main(){

	int n;
	cin >> n;
	int k;
	cin >> k;
	vector <int> v(n);	

	for (int i = 0; i < n; i++){
		cin >> v[i];
	}	
	swap(v[0], v[k]);

	for (auto i : v)
		cout << i << " ";
	
	cout << endl;
}
