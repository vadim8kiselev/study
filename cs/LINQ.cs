using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            Regex info = new Regex(@"(\s|^)[А-Я][а-я]+(\s|)");
            Regex groups = new Regex(@"[1-9][1-9][1-9]");
            Regex marks = new Regex(@"(^|\s)([0-9]|[1-9][0-9]|100)(\s|$)");            


            IEnumerable<string> parse = 
                (from line in System.IO.File.ReadAllLines(@"C:\Users\KiselevVY\Documents\input.txt", Encoding.GetEncoding(1251))
                    let words = line.Split(' ').ToList()
                    where info.IsMatch(words[0])
                    where info.IsMatch(words[1])
                    where info.IsMatch(words[2])
                    where groups.IsMatch(words[3])
                    from mark in words.GetRange(4, words.Count - 4)
                        where marks.IsMatch(mark)
                    select line
                 ).Distinct();
                    
            foreach (String str in parse)
                Console.WriteLine(str);            
        }
    }
}
