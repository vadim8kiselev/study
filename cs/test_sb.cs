using System;
using System.IO;
using System.Text;

namespace Application
{
	class Program
	{
		static void DeleteArticles(StringBuilder data, string pattern)
		{
			int index = 0;
			while ((index = data.ToString().ToLower().IndexOf(pattern, index)) != -1) 
			{
				if ((index == 0 || !char.IsLetter (data [index - 1])) &&
					(index == data.Length - pattern.Length || !char.IsLetter (data [index + pattern.Length]))) {
					int length = pattern.Length;
					if (index != data.Length - pattern.Length && data [index + pattern.Length] == ' ') {
						length++;
					}
					data.Remove (index, length);
				} else 
				{
					index++;
				}
			}		
		}

		static void Main (string[] args)
		{
			StringBuilder data = new StringBuilder (System.IO.File.ReadAllText (@"../../input.txt"));

			data.Replace ("ci", "si").Replace ("ce", "se")
				.Replace ("Ci", "Si").Replace ("Ce", "Se")
				.Replace ("ck", "k") .Replace ("c", "k");

			DeleteArticles(data, "the");
			DeleteArticles(data, "a");
			DeleteArticles(data, "an");

			for (int index = 1; index < data.Length; index++) 
			{
				if (char.ToLower(data [index]) == char.ToLower(data [index - 1]) && char.IsLetter(data [index])) 
				{
					if (data [index] == 'e' || data[index] == 'o')
					{
						data [index - 1] = (char.IsUpper(data [index - 1]) && data [index - 1] == 'E') ? 'I' :
							((char.IsUpper(data [index - 1]) ? 'U' : ((data [index - 1] == 'e') ? 'i' : 'u')));
					}
					data.Remove (index, 1);		
					index--;
				}
			}

			for (int index = 1; index < data.Length; index++) 
			{
				if (data [index] == 'e' && char.IsLetter (data [index - 1])
				    && (index == data.Length - 1 || !char.IsLetter (data [index + 1]))) 
				{
					data.Remove (index, 1);
				}
			}
			
			Console.WriteLine (data.ToString());
		}
	}
}
