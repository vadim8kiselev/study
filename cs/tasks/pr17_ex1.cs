/*
Practicum 17 
Task 1
В текстовом файле записана без ошибок формула вида:
<формула>=<цифра>|M(<формула>, <формула>)|m(<формула>, <формула>)
где | – или
<цифра>=0|1|2|3|4|5|6|7|8|9
M обозначает вычисление максимума, m – минимума
Вычислить значение этой формулы
*/

using System;
using System.IO;

namespace App
{
    class MainClass
    {
        static int findMainComma(string command)
        {
            if (command.Length > 1)
            { 
                int pointer = 0;

                for (int index = 0; index < command.Length; index++)
                {
                    if (command[index] == '(')
                        pointer++;
                    else if (command[index] == ')')
                        pointer--;

                    if (command[index] == ',' && pointer == 1 )
                        return index;
                }
            }
            
            return -1;
        }

        static int interpretate(string command)
        {
            int position = findMainComma(command);

            if (command[0] == 'M')
                return Math.Max(interpretate(command.Substring(2, position - 2)), interpretate(command.Substring(position + 1, command.Length - 2 - position)));
           
            else if (command[0] == 'm')
                return Math.Min(interpretate(command.Substring(2, position - 2)), interpretate(command.Substring(position + 1, command.Length - 2 - position)));
            
            return int.Parse(command);
        }

        public static void Main(string[] args)
        {
            StreamReader input  = new StreamReader("path\\to\\project\\input.txt"); // must be changed
            StreamWriter output = new StreamWriter("path\\to\\project\\output.txt");

            output.WriteLine(interpretate(input.ReadLine().Trim()));
            
            input.Close();
            output.Close();
        }
    }
}
