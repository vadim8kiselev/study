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
using System.Linq;
using System.IO;
using System.Collections.Generic;

namespace App
{
    class MainClass
    {
        static int findMainComma(string command){

            if (command.IndexOf(',') == command.LastIndexOf(','))
                return command.IndexOf(',');

            int count = 1;

            // M ( m ( 3 , 5 ) , M ( 1 , 2 ) )
            // 0 1 2 3 4...

            for (int i = 4; i < command.Length; i++)
            {
                if (command[i] == '(')
                    count++;
                else if (command[i] == ')')
                    count--;

                if (count == 0)
                    return i + 1;
            }

            return -1;
        }

        static int interpretate(string command, bool flag)
        {
            int position = findMainComma(command);

            if (command[0] == 'M')
            {
                if (command.IndexOf(',') == command.LastIndexOf(','))
                    return interpretate(command.Substring(2, command.Length - 3), true);                    
                
                return Math.Max(interpretate ( command.Substring(2, position - 2), true), interpretate ( command.Substring(position + 1, command.Length - 2 - position), true));
            }
            else if (command[0] == 'm')
            {
                if (command.IndexOf(',') == command.LastIndexOf(','))
                    return interpretate(command.Substring(2, command.Length - 3), false);                            

                return Math.Min(interpretate ( command.Substring(2, position - 2), false), interpretate(command.Substring(position + 1, command.Length - 2 - position), false));
            }
            else
            {
                if (flag)
                    return Math.Max(int.Parse(command.Substring(0, position)), int.Parse(command.Substring(position + 1, command.Length - 1 - position)));
                else
                    return Math.Min(int.Parse(command.Substring(0, position)), int.Parse(command.Substring(position + 1, command.Length - 1 - position)));                
            }
        }

        public static void Main(string[] args)
        {
            StreamReader input  = new StreamReader("D:\\path\\to\\project\\input.txt"); // must be changed
            StreamWriter output = new StreamWriter("D:\\path\\to\\project\\output.txt");

            for (int i = 0; i < 5; i++)
            {
                string command = input.ReadLine();

                bool flag = (command[0] == 'M') ? true : false;

                output.WriteLine(interpretate(command, flag));
            }

            input.Close();
            output.Close();
        }
    }
}
