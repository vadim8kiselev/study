#include <iostream>
#include <fstream>
#include <string> 

using namespace std; 

struct student
{       string surname, name, familyname, address, school;
        int year;
        void show();
        void print(ofstream & );
}; 
void student::show()
{        cout << surname << ' ' << name << ' ' << familyname<< ' ' << year << ' ' 
<<address << ' ' << school << endl;
}
 
void student::print(ofstream &out)
{        out << surname << ' ' << name << ' ' << familyname<< ' ' << year << ' ' 
<<address << ' ' << school << endl;
} 
int main()
{
        ifstream in("input.txt");       
                int size = 0;
                in >> size;
                student* students = new student[size];
                int index = 0;
                while (!in.eof())
                {
                        in >> students[index].surname;
                        in >> students[index].name;
                        in >> students[index].familyname;
                        in >> students[index].year;
                        in >> students[index].address;
                        in >> students[index].school;
                        students[index++].show();                      
                } 
        in.close(); 
        string curSchool = "";
        cout << "Enter the name of school without spaces" << '\n';
        cin >> curSchool; 
        ofstream out("output.txt");  
                for (int index = 0; index < size; index++)
                {       if (curSchool == students[index].school)
                        {       students[index].show();
                                students[index].print(out);
                        }
                } 
        out.close();       
        delete[] students;
  return 0;
}
