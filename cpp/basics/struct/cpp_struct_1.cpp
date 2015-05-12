#include <fstream>
#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

struct Inventory
{       string name;
        double cost;
        int age, ageLimit;
        void show();
        void print(ofstream &out);
};
void Inventory::show()
{cout<<setw(10)<< name<<'\t'<< setw(10)<< cost<< setw(8)<< age<<setw(8)<<ageLimit<< endl;
}
void Inventory::print(ofstream &out)
{out<< setw(10)<< name<<'\t'<< setw(10)<< cost<< setw(8)<< age<<setw(8)<<ageLimit<< endl;
}
int main()
{       
                ifstream in("input.txt");
                Inventory Toy[10];
                int value = 0,index  = 0;                
                while (in.peek() != EOF)
                {
                        in >> Toy[index].name;
                        in >> Toy[index].cost;
                        in >> Toy[index].age;
                        in >> Toy[index].ageLimit;
                        Toy[index++].show();
                }
        in.close();
                cout << "\nAge limit: ";
                cin >> value;
        ofstream out("output.txt");                                             
                                for (int idx = 0; idx < index; idx++)
                                        if (Toy[idx].ageLimit <= value)
                                        {
                                                Toy[idx].print(out);
                                        }              
         out.close();
return 0;
}
