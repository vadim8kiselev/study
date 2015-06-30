#include <iostream>
#include <list>
#include <assert.h>
using namespace std;

int main(){

	int n;
	cin >> n;
	int k;
	cin >> k;
	list <int> l;	

	int tmp;
	for (int i = 0; i < n; i++){
		cin >> tmp;
		l.push_back(tmp);
	}	
	
	assert(k > 0);

	if (!(k / n)){
		int id = 0;
		for (list<int>::iterator i = l.end(); i != l.begin(); i--, id++)
			if (id == k){
				l.remove(*i);
				break;
			}			
	}
	else if (!(k ^ n))
		l.pop_front();	

	for (auto i : l)
		cout << i << " ";	

	cout << endl;
}
