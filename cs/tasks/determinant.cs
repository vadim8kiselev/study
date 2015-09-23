using System;
 
namespace Determinat
{
    class Program
    {
        static int[,] GenerateMinor(int[,] input, int target)
        {
            int order = input.GetLength(0);
            int[,] output = new int[order - 1, order - 1];
 
            for (int index = 1, row = 0; index < order; index++, row++)
            {
                int column = 0;
                for (int jndex = 0; jndex < order; jndex++)
                {
                    if (jndex != target)
                    {
                        output[row, column++] = input[index, jndex];
                    }
                }
            }
            return output;
        }
        static int Determinant(int[,] input)
        {
            int order = input.GetLength(0);
            if (order == 2)
            {
                return ((input[0, 0] * input[1, 1]) - (input[1, 0] * input[0, 1]));
            }
            else if (order == 1)
            {
                return (input[0, 0]);
            }
 
            int value = 0;
 
            for (int index = 0; index < order; index++)
            {
                value += input[0, index] * ((index % 2 == 1) ? -1 : 1) * Determinant(GenerateMinor(input, index));
            }
            return value;
        }
        static void Main(string[] args)
        {
            Console.Write("Enter rank of matrix: ");
            int rank = int.Parse(Console.ReadLine());
 
            int[,] array = new int[rank, rank];
 
            for (int index = 0; index < rank; index++)
            {
                int jndex = 0;
                foreach (string item in Console.ReadLine().Trim().Split(' '))
                {
                    array[index, jndex++] = int.Parse(item);
                }
            }
            Console.WriteLine(Determinant(array));
        }
    }
}
