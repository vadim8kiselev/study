#include <fstream>
#include <vector>

using namespace std; 

int solve (vector< vector < int> > &matrix)
{
        vector <vector <int> > minor(matrix); 
     if (minor.size() == 1) return minor[0][0]; //exit condition
else if (minor.size() == 2) return minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0];
 
        int solveSumm = 0;
        int index = 0; // row: zero
        for (int jndex = 0; jndex < (int)matrix.size(); jndex++)
       {
                minor = matrix;
                int element = minor[0][jndex];
                minor.erase(minor.begin(), minor.begin() + 1); //cut a row: zero                              
 
                for (int position = 0; position < (int)minor.size(); position++)
                {   // cut a column: jndex
                        minor[position].erase(minor[position].begin()+jndex);                  
                } 
                int flag = 0;
                ( (jndex + 2) % 2 == 1 ) ? flag = -1 : flag = 1;        
                         solveSumm += flag * element * solve(minor); //recursion
        }
        return solveSumm;
} 
int main(void)
{
        ifstream in("input.txt"); 
                int size = 0;
                        in >> size;    
                        
                vector < vector < int > > matrix (size, vector< int >(size) );       
                for (int index = 0; index < size; index++)
                     for (int jndex = 0; jndex < size; jndex++)
                         in >> matrix[index][jndex]; 
        in.close();
        
        ofstream out("output.txt"); 
                out << solve(matrix); 
        out.close(); 
        
        return 0;
}
