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

        static bool isEquals (List<int> clone){ // compare two lists
            for (int i = 0; i < data.Count; i++)
                if (data[i] != clone[i])
                    return false;
            return true;
        }

        static string answerItem() // create string answer on task
        {            
            string answer = "";

            for (int index = 0; index < data.Count - 1; index++)
                answer += string.Format("{0} + ", data[index]);
            answer += string.Format("{0} = {1}", data[data.Count - 1], target);
            return answer;
        }

        static void generate( int result )
        {
            if (data.Count > target) // stackoverflow exception
                return;

            if (result == target)
            {
                List<int> clone = new List<int>(data);
                clone.Sort();
                
                if (isEquals(clone)) // choose only sorted answers
                {
                    Console.WriteLine(answerItem());
                    return;
                }
            }
            else
            {
                for (int index = 1; index < target; index++)
                {
                    data.Add(index);
                    result += index;
                    generate(result);
                    data.RemoveAt(data.Count - 1);
                    result -= index;
                }
            }
        }

        static void Main(string[] args)
        {            
            target = int.Parse(Console.ReadLine());
            
            generate(0);            
        }
    }
}
