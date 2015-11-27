using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Application
{
	class Program
	{
		static int OS = 1;
		static string path;
		static char slash;

		static void Main(string[] args)
		{
			if (OS == 1) {
				path = @"/tmp/";
				slash = '/';
			} else {
				path = @"C:\temp\";
				slash = '\\';
			}
			RunAbstractOS ();
		}

		static void RunAbstractOS(){	

			if (Directory.Exists(path + "K1" + slash))
				Directory.Delete (path + "K1" + slash, true);
			if (Directory.Exists(path + "K2" + slash))
				Directory.Delete (path + "K2" + slash, true);
			Directory.CreateDirectory (path + "K1" + slash);
			Directory.CreateDirectory (path + "K2" + slash);
			File.Create (path + "K1" + slash + "t1.txt").Dispose();
				string text = "Иванов Иван Иванович, 1965 года рождения, место жительства г. Саратов";
			WriteInFile (path + "K1" + slash + "t1.txt", text);

			File.Create (path + "K1" + slash + "t2.txt").Dispose();
				text = "Петров Сергей Федорович, 1966 года рождения, место жительства г.Энгельс";
			WriteInFile (path + "K1" + slash + "t2.txt", text);

			File.Create (path + "K2" + slash + "t3.txt").Dispose();
			WriteInFile (path + "K2" + slash + "t3.txt", System.IO.File.ReadAllText(path + "K1" + slash + "t1.txt") + 
			             System.IO.File.ReadAllText(path + "K1" + slash + "t2.txt"));

			ShowInformationAboutFile (path + "K1" + slash + "t1.txt");
			ShowInformationAboutFile (path + "K1" + slash + "t2.txt");
			ShowInformationAboutFile (path + "K2" + slash + "t3.txt");
			Console.WriteLine ();

			File.Move (path + "K1" + slash + "t2.txt", path + "K2" + slash + "t2.txt");
			File.Copy (path + "K1" + slash + "t1.txt", path + "K2" + slash + "t1.txt");

			if (Directory.Exists(path + "ALL" + slash))
				Directory.Delete (path +"ALL" +slash, true);
			Directory.Move (path +"K2" + slash, path +"ALL" + slash);
			Directory.Delete (path +"K1" + slash, true);

			foreach (string file in Directory.GetFiles(path +"ALL" + slash))
				ShowInformationAboutFile (file);
		}

		static void WriteInFile(string path, string data){
			StreamWriter fileStream = new StreamWriter(File.Open(path, FileMode.Append));
			fileStream.WriteLine (data);
			fileStream.Close ();
		}

		static void ShowInformationAboutFile(string path){
			FileInfo info = new FileInfo (path);
			Console.WriteLine (info.CreationTime + " " + info.FullName + "  " + info.Length + " Bytes.");
		}
	}
}
