using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;

namespace Application
{
	class Program
	{
		static void Main (string[] args)
		{
				StringBuilder data = new StringBuilder (System.IO.File.ReadAllText (@"../../input.txt"));

				int deleted = 0;
				foreach (Match match in new Regex (@"\b(the|an?)\b", RegexOptions.IgnoreCase).Matches (data.ToString ())) {
					data.Remove (match.Index - deleted, match.Length);
					deleted += match.Length;
				}
		
				foreach (Match match in new Regex (@"c[ie]", RegexOptions.IgnoreCase).Matches(data.ToString())) {
					data [match.Index] = (Char.IsUpper (data [match.Index])) ? 'S' : 's';
				}

				deleted = 0;
				foreach (Match match in new Regex (@"ck", RegexOptions.IgnoreCase).Matches(data.ToString())) {
					if (Char.IsUpper (data [match.Index - deleted])) {
						data [match.Index - deleted + 1] = Char.ToUpper (data [match.Index - deleted + 1]);
					}
					data.Remove (match.Index - deleted, 1);
					deleted++;
				}
				foreach (Match match in new Regex (@"c", RegexOptions.IgnoreCase).Matches(data.ToString())) {
					data [match.Index] = (Char.IsUpper (data [match.Index]) ? 'K' : 'k');
				}

				deleted = 0;
				foreach (Match match in new Regex (@"(ee|oo)", RegexOptions.IgnoreCase).Matches (data.ToString ())) {

					if (Char.IsUpper (data [match.Index - deleted])) {
						data [match.Index - deleted] = (data [match.Index - deleted] == 'E') ? 'I' : 'U';
					} else {
						data [match.Index - deleted] = (data [match.Index - deleted] == 'e') ? 'i' : 'u';
					}
					data.Remove (match.Index - deleted + 1, 1);
					deleted++;
				}

				deleted = 0;
				foreach (Match match in new Regex (@"([a-z])\1+", RegexOptions.IgnoreCase).Matches (data.ToString ())) {
					data.Remove (match.Index - deleted + 1, match.Length - 1);
					deleted += match.Length - 1;
				}

				deleted = 0;
				foreach (Match match in new Regex (@"\Se\b", RegexOptions.IgnoreCase).Matches (data.ToString ())) {
					data.Remove (match.Index - deleted + 1, 1);
					deleted++;
				}

				deleted = 0;
				foreach (Match match in new Regex (@"\s{2,}", RegexOptions.IgnoreCase).Matches (data.ToString ())) {
					data.Remove (match.Index - deleted + 1, match.Length - 1);
					deleted += match.Length - 1;
				}
				String answer = data.ToString ().Trim ();
				Console.WriteLine (answer);
		}
	}
}
