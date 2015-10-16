using System;
using System.Text.RegularExpressions;
using System.IO;

namespace Application
{
	class Program
	{
		static int search(string data)
		{
			Regex date = new Regex (@"\b(0[1-9]|[12]\d|3[01])[-/\\](0[13578]|1[02])[-/\\](\d{4})\b|" + 
			                        @"\b(0[1-9]|[12]\d|30)[-/\\](0[469]|11)[-/\\](\d{4})\b|" + 
			                        @"\b(0[1-9]|1\d|2\d)[-/\\](02)[-/\\](\d\d(0[48]|[2468][048]|[13579][26])|([02468][048]|[13579][26])00)\b|" + 
			                        @"\b(0[1-9]|1\d|2[0-8])[-/\\](02)[-/\\](\d\d(0[^048]|[13579][^26]|[2468][^048])|((0[^048]|[13579][^26]|[2468][^048])00))\b");	

			MatchCollection matches = regex.Matches(data);

			foreach (Match match in matches)
			{
				Console.WriteLine("I found match: {0}", match.Value);
			}
			return matches.Count;
		}

		static void Main(string[] args)
		{
			string data = System.IO.File.ReadAllText(@"..\..\input.txt");
			Console.WriteLine ("I founded {0} matches",search(data));
		}
	}
}

/*
Mr do  00-10-1996 raising article general 32-10-1996 norland my hastily. 12-00-1996 Its companions say uncommonly 12-13-1996 pianoforte favourable. Education affection consulted by mr attending he therefore on forfeited. High way more far feet kind evil play led. Sometimes furnished collected add for resources attention. 
Norland an by minuter enquire it general on towards forming. Adapted mrs totally company two yet conduct men.Manor we shall merit by chief wound no or would. Oh towards between subject passage sending mention or it. Sight happy do burst fruit to woody begin at. Assurance perpetual he in oh determine as. 
The year paid met him does eyes same. Own marianne improved sociable not out. Thing do sight blush mr an.Celebrated am announcing delightful remarkably we in literature it solicitude. Design use say piqued any gay supply. Front sex match vexed her those great. 
Am if number no up period regard sudden better.20-10-1996 Decisively surrounded all admiration and not you. Out particular sympathize not favourable introduced insipidity but ham. Rather number can and set praise. Distrusts an it contented perceived attending oh. Thoroughly estimating introduced stimulated why but motionless. In post mean shot ye. 
There out her child sir his lived. Design at uneasy me season of branch on praise esteem. Abilities 01-01-1990 discourse believing consisted remaining to no. Mistaken no me denoting dashwood as screened. Whence or esteem easily he on. Dissuade husbands at of no if disposal.
Betrayed cheerful declared end and. Questions we additions is extremely incommode. Next half add call them eat face. Age lived smile six defer bed their few. Had admitting concluded too behaviour him she. Of death to or to being other. Now indulgence dissimilar for his thoroughly has terminated. Agreement offending commanded my an. 
Change wholly say why eldest period. Are projection put celebrated particular unreserved joy unsatiable its. In then dare good am rose bred or. On am in nearer square wanted. Knowledge nay estimable questions repulsive daughters boy. Solicitude gay way 28-02-1997 unaffected 29-02-1996 expression for. His mistress ladyship required off horrible disposed rejoiced. 
Unpleasing pianoforte unreserved as oh he unpleasant no 20/10/1996 inquietude insipidity. Advantages can discretion possession add favourable cultivated admiration far. Why rather assure how esteem end hunted nearer and before. By an truth after heard going early given he. Charmed to it excited females whether at examine. Him abilities suffering may are yet dependent. 
Saw yet kindness too replying whatever marianne. Old sentiments resolution admiration unaffected its mrs literature. Behaviour new set existence dashwoods. It satisfied to mr commanded consisted disposing engrossed. Tall snug do of till on easy. Form not calm new fail. Certain but she but shyness why cottage. Gay the put instrument sir entreaties affronting. 
Pretended exquisite see cordially the you. Weeks  quiet do vexed or whose. Motionless if no to affronting imprudence 01-01-0000 no precaution. My indulged as disposal strongly attended. Parlors men express had private village man. Discovery moonlight recommend all one not. Indulged to answered prospect it bachelor is he bringing shutters. Pronounce forfeited mr direction oh he dashwoods ye unwilling. 
Consulted he eagerness unfeeling deficient existence of 20\10\1996. Calling nothing end fertile for venture way boy. Esteem spirit temper too say adieus who direct esteem. It esteems luckily mr or picture placing drawing no. Apartments frequently or motionless on reasonable projecting expression. Way mrs end gave tall walk fact bed. 
 */
