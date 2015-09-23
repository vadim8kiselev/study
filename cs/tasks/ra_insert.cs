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
            Console.Write("Enter count of rows in ragged array: ");
            int size = int.Parse(Console.ReadLine());
            Console.Write("Enter target number: ");
            int target = int.Parse(Console.ReadLine());
            int[][] array = createArray(out array, size);

            for (int index = 0; index < array.Length; index++)
                for (int jndex = 0; jndex < array[index].Length; jndex++)
                    if (array[index][jndex] == 0)
                    {                         
                        int[] row = new int[array[index].Length + 1];

                        array[index].CopyTo(row, 0);                        
                        Array.Copy(row, jndex, row, jndex + 1, row.Length - 1 - jndex);
                        
                        row[jndex+++1] = target;

                        array[index] = row;

                        GC.Collect();
                    }

            printArray(array);
        }
    }

}
