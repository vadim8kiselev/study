#include <fstream>
#include <string>
 
using namespace std;
 
int main()
{
        ifstream in("input.txt");
        string str, str1;
        getline(in, str); in>>str1;
 
        while (true)
        {
                if (!(str.find(str1) == string::npos))
                        str.erase(str.find(str1), str1.length());
                else break;            
}
        ofstream out("output.txt");
                out << str;
        out.close();
        return 0;
}
