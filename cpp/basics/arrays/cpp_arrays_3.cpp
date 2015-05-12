#include <fstream>
using namespace std;
int main()
{
        ifstream in("input.txt");
        int size;
                in >> size;
                
        int* mas   = new int[size + 1];
        int* index = new int[size + 1];
        
        for (int ind = 1; ind <= size; ind++)
        { 
                index[ind] = 0; mas[ind] = 0; 
        }
        
        ofstream out("output.txt");
        
        for (int ind = 1; ind <= size; ind++)
        {
                in >> mas[ind];
                if (mas[ind] > size)
                {
                        out << -1; return 0;
                }
                index[mas[ind]]++;
        }
        in.close();
        
        for (int ind = 2; ind <= size; ind++)
                if (index[ind] > index[ind - 1])
                {
                        out << -1; return 0;
                }
        out << index[1] << endl;
 
        int m = index[1] + 1;
        for (int ind = 1; ind <= size; ind++)
                out << m - index[mas[ind]]-- << " ";
                
        out.close(); 
        delete[]mas; delete[]index;
        return 0;
}
