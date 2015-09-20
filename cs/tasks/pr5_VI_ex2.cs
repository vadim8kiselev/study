/*
Practicum 5 
Module VI
Task 2
Разработать рекурсивную функцию для вывода на экран всех возможных разложений
натурального числа n на слагаемые (без повторений)
*/

using System;
using System.IO;
using System.Collections.Generic;

namespace development
{
    class Program
    {
        static List<int> data = new List<int>();
        
        static int target;

        static void generate( int result, int jndex )
        {
            if (data.Count > target || result > target) // stackoverflow exception
                return;

            if (result == target)
            {
                string answer = "";
                for (int index = 0; index < data.Count - 1; index++)
                    answer += string.Format("{0} + ", data[index]);
                Console.WriteLine(answer + string.Format("{0} = {1}", data[data.Count - 1], target));
            }
            else
            {
                for (int index = jndex; index < target; index++)
                {
                    data.Add(index);
                    result += index;
                    generate(result, index);
                    data.RemoveAt(data.Count - 1);
                    result -= index;
                }
            }
        }

        static void Main(string[] args)
        {            
            target = int.Parse(Console.ReadLine());
            
            generate(0, 1);            
        }
    }
}
