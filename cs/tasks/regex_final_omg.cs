using System;
using System.IO;
using System.Text.RegularExpressions;
using System.Collections.Generic;

namespace Application
{
	class Program
	{
		static void Main (string[] args)
		{
			string data = System.IO.File.ReadAllText (@"../../input.txt");

			foreach (KeyValuePair<string, string> item in getPatterns ())
				data = Regex.Replace (data, item.Key, item.Value);

			Console.WriteLine (data.Trim ());
		}

		static List<KeyValuePair<string, string> > getPatterns ()
		{
			List<KeyValuePair<string, string> > patterns = new List<KeyValuePair<string, string> > ();
			patterns.Add (new KeyValuePair<string, string> (@"\b(the|The|an?|An?)\b", ""));
			patterns.Add (new KeyValuePair<string, string> (@"c(?=[ie])", "s"));
			patterns.Add (new KeyValuePair<string, string> (@"C(?=[ie])", "S"));
			patterns.Add (new KeyValuePair<string, string> (@"ck?", "k"));
			patterns.Add (new KeyValuePair<string, string> (@"Ck?", "K"));
			patterns.Add (new KeyValuePair<string, string> (@"ee", "i"));
			patterns.Add (new KeyValuePair<string, string> (@"oo", "u"));
			patterns.Add (new KeyValuePair<string, string> (@"Ee", "I"));
			patterns.Add (new KeyValuePair<string, string> (@"Oo", "U"));
			patterns.Add (new KeyValuePair<string, string> (@"([a-zA-Z])(?=\1)", ""));
			for (char index = 'A'; index <= 'Z'; index++) {
				patterns.Add (new KeyValuePair<string, string> ((index + "" + (char)(index + 32)), index.ToString ()));
			}
			patterns.Add (new KeyValuePair<string, string> (@"(?<![^a-zA-Z])e\s", " "));
			patterns.Add (new KeyValuePair<string, string> (@"\s{2,}", " "));
			return patterns;
		}
	}
}
