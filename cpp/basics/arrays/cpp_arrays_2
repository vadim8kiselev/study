#include <fstream>
using namespace std;
int main()
{       ifstream in("input.txt");
                int size;
                int tmp;
                        in >> size;
                        in >> tmp;
                int *array = new int[size + size - 1];
                for (int index = 0; index < size + size - 1; index++)
                        array[index] = INT_MAX;
 
                for (int index = 0; index < size; index++)
                        in >> array[index];
        in.close();
 
 
        for (int index = 0; index < size - 1; index++)
        {       if (array[index] * array[index + 1] < 0)
                {       for (int jndex = size + 1; jndex > index + 1; jndex--)
                        array[jndex] = array[jndex - 1];
 
                        array[index + 1] = tmp; 
                        size++; index++;
                }
        }
 
        ofstream out("output.txt");
                for (int index = 0; array[index] != INT_MAX; index++)
                        out << array[index] << " ";
        out.close();
        return 0;
  }
