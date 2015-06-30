#include <fstream>
#include <queue>

using namespace std;

queue  <int> qu;
int size;

void rolling(int start, int finish, ofstream &out){
	for (int i = 0; i < size; i++){
		if (start <= qu.front() && qu.front() <= finish) // search in range
			out << qu.front() << " "; // and print it

		qu.push(qu.front()); // keep rolling
		qu.pop();            // on n-round
	}
}

int main(){

	ifstream in("input.txt");
	in >> size;
	int start, finish; in >> start >> finish;
	int tmp;
	for (int index = 0; index < size; index++){
		in >> tmp;
		qu.push(tmp);
	}
	in.close();

	ofstream out("output.txt");

	rolling(start, finish, out); // search needed elements
	rolling(1, start - 1, out); // for O(3*n)
	rolling(finish + 1, 1000, out); // not the best, but works

	out.close();
}