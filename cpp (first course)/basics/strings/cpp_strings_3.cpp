#include <set>
#include <string>
#include <fstream>
using namespace std;
void lower(char &c)
{        if (c >= 'A' && c <= 'Z')
                c = 'a' + c - 'A';
        if (c >= 'А' && c <= 'Я')
                c = 'а' + c - 'А';
}
set <string> pars(ifstream &in)
{       set <string> text;
        string symbols = "0123456789""!@#$%^&*(),./\!№;:?-_><+=«»—"; // some symbols     
        while (in.peek() != EOF)
        {       string currentLine = "";
                getline(in, currentLine);
                for (int index = 0; index < (int)currentLine.length(); index++)
                {
                        lower(currentLine[index]);
 
                if (symbols.find(currentLine[index]) != string::npos
                    || currentLine[index] == '"')
                                currentLine[index] = ' ';                      
                } 
                string word = "";
                for (int index = 0; index < (int)currentLine.length(); index++)
                {       if (currentLine[index] == ' ')
                        {
                                text.insert(word);
                                word = "";
                        }
                        else word += currentLine[index];
                }
        }
return text;}
int main()
{        ifstream in("input.txt"); 
                set <string> text = pars(in); 
        in.close(); 
        ofstream out("output.txt"); 
                for (auto index = text.begin(); index != text.end(); index++)
                        out << *index << '\n'; 
        out.close();
        return 0;
}
