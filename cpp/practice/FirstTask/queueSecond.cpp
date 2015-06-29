#include <fstream>
#include <queue>
#include <vector>
#include <string>
using namespace std;  
 
 ofstream out ("output.txt");
 
 vector <queue <int> > v;
 
 int def (string post){
	if (post[0]=='P'){
		if (post[1]=='U')
			return 0; //PUSH
		else
			return 1; //POP
	}
	else 
		return -1; // TOP 
 }
 
 pair<int,int> getIndex(string post){
	if (!def(post)){
		int i = 6;
		int answer = (post[5]-'0');
		while ( post[i] != ',' ){
			answer *= 10;
			answer += (post[i] - '0');
			i++;
		}
		i+=2;
		int postfix = (post[i-1] - '0');
		while ( post[i] != ')' ){
			postfix  *= 10;
			postfix  += (post[i] - '0');
			i++;
		}
		return make_pair(answer,postfix );
	}
	else {
		int i = 5;
		int answer = (post[4]-'0');
		while ( post[i] != ')' ){
			answer *= 10;
			answer += (post[i] - '0');
			i++;
		}
		return make_pair(answer,0);
	}	
 }
 
 inline void inter (string post){
	if (!def(post)){
		v[getIndex(post).first-1].push(getIndex(post).second);
	}
	else if(def(post) > 0){
		int index = getIndex(post).first-1;
		out << v[index].front() << " ";
		v[index].pop();
	}
	else {
		out << v[getIndex(post).first-1].front() << " ";
	}

 }
 
int main(){		
	ifstream in ("input.txt");
		int n, k;
		in >> n >> k;
		v = vector <queue <int> > (n);	
	
	string post; 
	getline(in,post);
	for (int i = 0 ; i < k; i++){
		getline(in,post);
		inter (post);	
	}	
	in.close();
}