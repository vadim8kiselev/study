#include <fstream>
#include <string>
#include <algorithm>
using namespace std;
 
int main()
{
        ifstream in("input.txt");
                int sizen, sizem;
                        in >> sizen >> sizem;
                string *array = new string[sizen]; 
                for (int index = 0; index < sizen; index++)
                        in >> array[index];
        in.close();
       
        int countA = 0, countB = 0;
        for (int index = 0; index < sizen; index++)
        {
                for (int jndex = 0; jndex < sizem; jndex++)
                {	   if ((index + jndex) % 2 == 0 && array[index][jndex] != '*')
                                countA++;
                        if ((index + jndex) % 2 == 1 && array[index][jndex] != '.')
                                countA++; 
                        if ((index + jndex) % 2 == 0 && array[index][jndex] != '.')
                                countB++;
                        if ((index + jndex) % 2 == 1 && array[index][jndex] != '*')
                                countB++; 
                }
        }
        ofstream out("output.txt");
                out << min(countA, countB);
        out.close(); delete[]array;
        return 0;
}
