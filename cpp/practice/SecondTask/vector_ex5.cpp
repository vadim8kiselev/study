#include <iostream>
#include <vector>
using namespace std;

int main(){

	int size;
	cin >> size;
	int index;
	cin >> index;
	vector <int> v(size);

	for (int i = 0; i < size; i++)
		cin >> v[i];
		
	swap(v[0], v[index]); // no commentary

	for (auto i : v)
		cout << i << " ";
	
	cout << endl;
}
