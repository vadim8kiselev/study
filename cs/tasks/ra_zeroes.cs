using System;
using System.Linq;

namespace Solution
{
    class Solution
    {
        static int[][] createArray(out int[][] array, int size)
        {
            array = new int[size][];

            for (int index = 0; index < size; index++)
            {
                string[] consoleData = Console.ReadLine().Trim().Split(' ');

                int jndex = 0;
                array[index] = new int[consoleData.Length];

                foreach (string item in consoleData)
                    array[index][jndex++] = int.Parse(item);
            }

            return array;
        }

        static void printArray(int[][] array) 
        {
            for (int index = 0; index < array.Length; index++, Console.WriteLine())
                for (int jndex = 0; jndex < array[index].Length; jndex++)
                    Console.Write("{0} ", array[index][jndex]);
        }

        public static void Main(string[] args)
        {
            int size = int.Parse(Console.ReadLine());
            int[][] array = createArray(out array, size);

            for (int index = 0; index < array.Length; index++)
                for (int jndex = 0; jndex < array[index].Length; jndex++)
                    if (array[index][jndex] == 0)
                    {
                        array[index] = array[index].Where((source, value) => value != jndex).ToArray();
                        jndex--;
                    } 

            printArray(array);
        }
    }

}
