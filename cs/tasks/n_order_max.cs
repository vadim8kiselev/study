using System;
 
namespace Solution
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Enter size array: ");
            int size = int.Parse(Console.ReadLine());
            Console.Write("Enter order of maximum: ");
            int numberMax = int.Parse(Console.ReadLine());
            int[] array = new int[size];
            int pointer = 0;
 
            foreach (string item in Console.ReadLine().Trim().Split(' '))
            {
                array[pointer++] = int.Parse(item);
            }
 
            int max = int.MaxValue;
 
            for (int index = 0; index < numberMax; index++)
            {
                int currentMax = int.MinValue;
 
                for (int jndex = 0; jndex < size; jndex++)
                {
                    if (array[jndex] > currentMax && array[jndex] < max)
                        currentMax = array[jndex];
                }
 
                max = (currentMax != int.MinValue)?currentMax:max;
            }
 
            Console.WriteLine("Maximum of {0} order is {1}", numberMax, max);
        }
    }
}
