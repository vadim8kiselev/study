using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.IO;

namespace Application
{
    class Program
    {

        static void generate()
        {
            StreamWriter output = new StreamWriter(@"..\..\output.txt");
            int count = 0;

            for (int year = 2000; year <= 2001; year++)
            {
                for (int month = 1; month <= 12; month++)
                {
                    for (int day = 1; day <= 31; day++)
                    {
                        count++;
                        output.WriteLine("{0}-{1}-{2}", day.ToString("D2"), month.ToString("D2"), year);
                    }
                }
            }
            Console.WriteLine(count);
        }

        static void test()
        {
            string data = System.IO.File.ReadAllText(@"..\..\output.txt");

            Regex newReg = new Regex(@"\b(3[0-1]|0[1-9]|[1-2][0-9])-(0[1-9]|1[0-2])-([0-9]{4})\b");
            MatchCollection matches = newReg.Matches(data);

            foreach (Match mat in matches)
            {
                Console.WriteLine("I found match: {0}", mat.Value);
            }
            Console.WriteLine("I found {0} matches", matches.Count);
        }


        static void Main(string[] args)
        {
            test();
        }
    }
}
