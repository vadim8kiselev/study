#include <fstream>

using namespace std;

int **creat(int &rows, int &columns)
{       
  ifstream in("input.txt");

  in >> rows >> columns;

  int ** array = new int *[rows];
  for (int index = 0; index < rows; index++)
  {
    array[index] = new int[2 * columns];
    for (int jndex = 0; jndex < columns; jndex++)
       in >> array[index][jndex];
  }
    in.close();
    return array;
}

void print(int rows, int columns, int **array)
{       
  ofstream out("output.txt");
  
  for (int index = 0; index < rows; index++)
  {
   for (int jndex = 0; jndex < columns; jndex++)
       out << array[index][jndex] << ' ';             
   out << endl;
  }
    out.close();
}

int main()
{
        int rows = 0;
        int columns = 0;
        int x = 0;
        
        cin >> x;
        
        int **array = creat(rows, columns);
        for (int index = 0; index < rows; index++)
                for (int jndex = 0; jndex < columns; jndex++)
                {
                if (array[index][jndex] == x)
                {
                        for (int iter = columns; iter > jndex - 1; iter--)
                        {
                                for (int jter = 0; jter < rows; jter++)
                                {
                                        array[jter][iter] = array[jter][iter - 1];
 
                                }
                        }
 
                        for (int past = 0; past < rows; past++)
                        {
                                array[past][jndex] = -1;
                        }
 
                        columns++;
                        jndex++;
                }
                }
        print(rows, columns, array);
  return 0;
}

