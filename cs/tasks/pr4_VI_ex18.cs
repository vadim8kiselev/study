/*
Practicum 4 
Module VI
Task 18
Определить, в каком числе содержится больше значащих нулей.
*/

using System;
using System.Collections.Generic;


namespace Application
{
    class Program
    {
        static int countOfZero( int value )
        {
            int count = 0;
            while (value > 0)
            {
                if (value % 10 == 0)
                    count++;
                value /= 10;
            }
            return count;
        }

        static void Main(string[] args)
        {
            List<int> array = new List<int>();
            Console.WriteLine("Enter the few random values for counting of zeros:\n");
            foreach (string item in Console.ReadLine().Split(' '))
                array.Add(int.Parse(item));

            int max = 0;
            int var = -1;
            foreach (int item in array)
            {
                int common = countOfZero(item);
                if (common > max)
                {
                    max = common;
                    var = item;
                }
            }

            if (var != -1)
                Console.WriteLine("{0} has the most zeros", var);
            else
                Console.WriteLine("All numbers have no any zeroes");
        }
    }
}
