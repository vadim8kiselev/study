/*
Practicum 4 
Module VII
Task 14
Вывести на экран все числа из отрезка [a; b]: 
Дано натуральное число N. Вывести на экран: 
ближайшее большее данного число, среди делителей которого есть число С; 
*/

using System;
using System.Collections.Generic;

namespace Application
{
    class Program
    {
        static void Main(string[] args)
        {

            Console.Write("Enter the two range limits: ");
            string[] ranges = Console.ReadLine().Split(' ');
            int start  = int.Parse(ranges[0]);
            int finish = int.Parse(ranges[1]);

            Console.Write("\nEnter the divider: ");
            int bound = int.Parse(Console.ReadLine());

            Console.Write("\nEnter the left bound: ");
            int value = int.Parse(Console.ReadLine());

            start = Math.Max(start, value + 1);
            for (int index = start; index <= finish; index++)
            {
                if (index % bound == 0)
                {
                    Console.WriteLine("{0} is answer", index);
                    return ;
                }
            }
            
        }
    }
}
