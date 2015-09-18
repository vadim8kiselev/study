/*
Practicum 17 
Task 3
Пусть символ # определен в текстовом редакторе как стирающий символ Backspace, т.е.
строка abc#d##c в действительности является строкой ac.
*/

using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;

namespace App
{
    class MainClass
    {                
        public static void Main(string[] args)
        {
            StreamReader input  = new StreamReader("D:\\path\\to\\project\\input.txt");  // Must change it
            StreamWriter output = new StreamWriter("D:\\path\\to\\project\\output.txt");

            char[] data = input.ReadLine().ToCharArray();

            Stack<char> contain = new Stack<char>();

            for (int index = 0; index < data.Length; index++)
                if (data[index] != '#')
                    contain.Push(data[index]);
                else
                    contain.Pop();            

            string answer = "";

            foreach (char item in contain)
                answer += item;            

            for (int index = answer.Length - 1; index >= 0; index--)
                output.Write(answer[index]);
            
            input.Close();
            output.Close();
        }
    }
}
