using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WhatAGame
{
    class ResultHolder
    {
        private static List<int> result = Enumerable.Repeat(0, 5).ToList();

        private ResultHolder()
        {

        }

        public static void SetResult(List<int> newResult)
        {
            result = new List<int>(newResult);
        }

        public static List<int> GetResult()
        {
            return new List<int>(result);
        }

        public static void RefreshResult()
        {
            result = Enumerable.Repeat(0, 5).ToList();
        }
    }
}
