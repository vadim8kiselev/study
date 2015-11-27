using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace ConsoleApplication1
{

    class Student
    {
        public string surname;
        public string name;
        public string secondName;
        public string groups;
        public List<string> marks;

        public string ToString()
        {   
            StringBuilder answer = new StringBuilder("");
            foreach(string item in marks)
                answer.Append(item + " ");
            return surname + " " + name + " " + secondName+ " " + groups + " " + answer;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            IEnumerable<Student> parse =
                from line in System.IO.File.ReadAllLines(@"C:\Users\KiselevVY\Documents\input.txt", Encoding.GetEncoding(1251))
                let words = line.Split(' ').ToList()
                where (words[0].Length != 0) && (words[1].Length != 0) && (words[2].Length != 0)
                where ((words[3].Length != 0))
                select new Student
                {
                    surname = words[0],
                    name = words[1],
                    secondName = words[2],
                    groups = words[3],
                    marks = words.GetRange(4, words.Count - 4)
                };
                    
            foreach (Student str in parse)
                Console.WriteLine(str.ToString());
            
        }
    }
}
