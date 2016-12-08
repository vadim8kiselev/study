using System;
using System.Collections.Generic;

namespace Solution
{
	class MainClass
	{
		static List<int> data = new List<int>();

		static int value; 

		static void printArray(){
			if (data.Count > 0){
				string answer = "";
				for (int index = 0; index < data.Count - 1; index++)
					answer += string.Format("{0} * ", data[index]);
				Console.WriteLine(answer + string.Format("{0} = {1}", data[data.Count - 1], value));
			}
			else
				Console.WriteLine ("1 * {0} = {0}", value);
		}

		static void generate (int target, int jndex){

			if (target == 1) {
				if (data.Count == 1) {				
					Console.WriteLine ("1 * {0} = {0}", value);
				} else {				
					printArray ();
				}
				return;
			} 
			else {
			
				for (int index = jndex; index <= target; index++)
				{
					if (target % index == 0) {
						data.Add(index);
						target /= index;
						generate(target, index);
						data.RemoveAt(data.Count - 1);
						target *= index;					
					}
				}
			
			}
		}

		public static void Main (string[] args)
		{
			value = int.Parse(Console.ReadLine ());

			generate (value, 2);
		}
	}
}
