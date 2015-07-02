#include <iostream>
#include <list>
#include <assert.h>
using namespace std;

int main(){
	int size;
	cin >> size; assert(size >= 0);
	int index; // index is k 
	cin >> index; assert(index >= 0 && index < size);
	list <int> l;	

	int tmp;
	for (int i = 0; i < size; i++){
		cin >> tmp;
		l.push_back(tmp);
	}		
	assert(index > 0); // negative numbers shall not pass

	if (index < size){ // equal to index < size 
		int id = 0;
		for (list<int>::iterator i = l.end(); i != l.begin(); i--, id++)
			if (!(id ^ index)){
				l.remove(*i);
				break;
			}			
	}
	else if (!(index ^ size)) // equal to index != size
		l.pop_front();	

	for (auto i : l)
		cout << i << " ";	

	cout << endl;
}
