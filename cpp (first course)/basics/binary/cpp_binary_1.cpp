#include <string>
#include <fstream>
#include <iostream>
 
using namespace std;
int main() {
        ofstream out("input.txt", ios::binary);
                int size = 0;  
                cout << "Enter count of double values: ";
                cin >> size;
 
                double startValue = 0.0;       
                double endValue = 0.0;
                cout << endl << "Enter start and end borders: ";
                cin >> startValue; startValue += 0.0;
                cin >> endValue; endValue += 0.0;
 
                double step = 0.0;
                cout << endl << "Enter step of iterations: ";
                cin >> step; step += 0.0;
 
                for (double index = 0; index < size; index += step)
                        out.write((char *)&index, sizeof(double));     
        out.close();
 
        ifstream in("input.txt", ios::binary);
                double currentValue = 0.0;
                while (in.peek() != EOF)
                {
                        in.read((char *)&currentValue, sizeof(double));
                        if (startValue <= currentValue && currentValue <= endValue)
                                cout << "Double value: " << currentValue + 0.0 << endl;                                      
                }
        in.close();
        return 0;
}
