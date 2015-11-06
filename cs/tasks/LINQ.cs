using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;

namespace Application
{
    public class LogMessage
    {
        public string family { get; set; }
        public string name { get; set; }
        public string secondName { get; set; }
        public Int32 groupName { get; set; }
        public List<string> marks { get; set; }

        private string getMarks()
        {
            string answer = "";
            foreach (string item in marks)
                answer += item.ToString();
            return answer;
        }

        public string getInfo()
        {
            return family + " " + name + " " + secondName + " " + groupName.ToString() + " " + getMarks();
        }
    }

    class Program
    {
        static void Main(string[] args)
        {

            Regex info = new Regex(@"^[A-Z][a-z]+$");
            Regex groups = new Regex(@"^(0|[1-9]|[1-9][0-9])$");

            var parse =
                from line in File.ReadAllLines(@"..\..\input.txt")
                let parts = line.Split(' ')
                where info.IsMatch(parts[0])
                where info.IsMatch(parts[1])
                where info.IsMatch(parts[2])
                where groups.IsMatch(parts[3])
                // Add marks filter
                select new LogMessage()
                {
                    family = parts[0],
                    name = parts[1],
                    secondName = parts[2],
                    groupName = Int32.Parse(parts[3]),                    
                    marks = new List<string>(parts.ToArray<string>())
                };
                

            foreach (LogMessage item in parse)
            {                
                Console.Write(item.getInfo());
                
            }

        }
    }
}
/*

    Аванесян Армен Самвелович 241 1 2 3 55 32 94
Бескровнов Вадим Васильевич 241 22 51 23
Болдырев Дмитрий Александрович 241 1 2 3 55 32 94
Гараничева Анна Александровна 241 22 51 23
Добрынин Артём Михайлович 241 1 2 3 55 32 94
Дьячков Евгений Александрович 241 22 51 23
Елисеева Елизавета Дмитриевна 241 22 51 23
Ерёмушкин Даниил Константинович 241 82 26 14
Ермолаев Александр Михайлович 241 62 11 52 51 62 3
Жакин Антон Вячеславович 241 77 22 33 44 8 23
Зажарнов Кузьма Андреевич 241 62 11 52 51 62 3
Истратенков Михаил Андреевич 241 1 2 3 55 32 94
Каримова Рената Фаридовна 241 22 51 23
Ким Вячеслав Юрьевич 241 82 26 14
Киселёв Вадим Юрьевич 241 62 11 52 51 62 3
Коровёнкова Мария Васильевна 241 1 2 3 55 32 94
Кузнецов Павел Сергеевич 241 82 26 14
Ларионова Мария Денисовна 241 77 22 33 44 8 23
Лукашова Мария Александровна 241 62 11 52 51 62 3
Мазин Алексей Владиславович 241 1 2 3 55 32 94
Макаров Алексей Александрович 241 22 51 23
Петкер Роман Олегович 241 82 26 14
Смагина Мария Алексеевна 241 77 22 33 44 8 23
Смирнова Мария Валерьевна 241 1 2 3 55 32 94
Сухарева Мария Сергеевна 241 77 22 33 44 8 23
Черногоров Владислав Максимович 241 62 11 52 51 62 3
Черноусова Юлия Андреевна 241 22 51 23

*/
