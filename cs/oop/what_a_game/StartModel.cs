using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WhatAGame
{
    class StartModel
    {
        public static string HELLO = "Здравствуй";
        public static string INTRODUCTION = "Сегодня я хочу поговорить о сфере IT";
        public static string DESCRIPTION = "Это огромный и интересный мир,";
        public static string DESCRIPTION_PLUS = "в который ты очень опаздываешь";
        public static string IMMEDIATELY = "Мы должны начать немедленно";
        public static string START = "и я помогу сделать твой\n\t\tпервый шаг...";

        public static string WELCOME_BUTTON = "Начать!";

        public static List<string> DYNAMIC_STARTUP = new List<string>() {
                                                                HELLO,
                                                                INTRODUCTION,
                                                                DESCRIPTION,
                                                                DESCRIPTION_PLUS,
                                                                IMMEDIATELY,
                                                                START
                                                    };
    }
}
