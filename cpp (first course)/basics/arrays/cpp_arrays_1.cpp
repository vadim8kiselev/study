#include <fstream>
using namespace std;
int* create(int size, ifstream &inp)
{       int *array = new int[size];
        for (int index = 0; index < size; index++)
                inp >> array[index];
        return array;
}
int main(void)
{       int x, y, size;
        ifstream inp("input.txt");
                inp >> x >> y >> size;
                int *array = create(size, inp); //create array
        inp.close();
 
        for (int index = 0; index < size; index++)
        {       if ((array[index] > x) && (array[index] < y))
                {       for (int jndex = index; jndex < size - 1; jndex++)
                             array[jndex] = array[jndex + 1];  //shift array
                        size--;  index--;   //delete                    
                }
        }
 
        ofstream out("output.txt");
                for (int index = 0; index < size; index++)
                        out << array[index] << " ";
        out.close();
        return 0;
}
