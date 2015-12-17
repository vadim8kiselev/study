using System;
using System.IO;
using System.Text.RegularExpressions;

namespace Application
{
	class Program
	{
		static void Main (string[] args)
		{
			String data = System.IO.File.ReadAllText (@"../../input.txt");

			data = new Regex (@"\b(the|an?)\b", RegexOptions.IgnoreCase).Replace (data, "");

			data = Regex.Replace (data, @"c(?=[ie])", "s");
			data = Regex.Replace (data, @"C(?=[ie])", "S");

			data = Regex.Replace (data, @"ck?", "k");
			data = Regex.Replace (data, @"Ck?", "K");

			data = Regex.Replace (data, @"ee", "i");
			data = Regex.Replace (data, @"oo", "u");
			data = Regex.Replace (data, @"Ee", "I");
			data = Regex.Replace (data, @"Oo", "U");

			data = Regex.Replace (data, @"([a-zA-Z])(?=\1)", "");

			for (char index = 'A'; index <= 'Z'; index++) {
				data = Regex.Replace (data, (index + "" + (char)(index + 32)), index.ToString ());
			}

			data = Regex.Replace (data, @"(?<![^a-zA-Z])e\s", " ");
			data = Regex.Replace (data, @"\s{2,}", " ");

			Console.WriteLine (data);
		}
	}
}
