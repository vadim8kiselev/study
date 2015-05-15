#include <iostream>
#include <string>
 
using namespace std;
 
 
template <typename type, typename epyt> 
type* create(int n, int m){
 
        type* array = new type[n]; 
 
        for (int i = 0; i < n; i++)
        {
                array[i] = new epyt[m]; 
                for (int j = 0; j < m; j++)
                        cin >> array[i][j]; 
        }
 
        return array;
}
 
template <typename type> 
void change(type array, int n, int m, int val){
               
        for (int i = 0; i < n; i++) 
        {
                for (int j = 0; j < m; j++)
                        if (array[i][j] < val) 
                                array[i][j] = val;             
        }
}
 
template <typename type> 
void print(type array, int n, int m){
       
        cout << endl;
        for (int i = 0; i < n; i++) 
        {
                for (int j = 0; j < m; j++)
                        cout << array[i][j]<< " "; 
                cout << endl;
        }
}
 
int main()
{       
        int n, m, val;
        cin >> n >> m >> val;
 
        int** array = create<int*,int>(n, m);  
           
        //double** array = create<double*,double>(n, m);
        //float** array = create<float*,float>(n, m);
       
       
        change(array, n, m, val); 
 
        print(array, n, m); 
 
        return 0;
}
