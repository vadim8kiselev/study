using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WhatAGame
{
    class GameModel
    {
        public Dictionary<string, Dictionary<string, List<int>>> GAME = new Dictionary<string, Dictionary<string, List<int>>>
        {
            { "Что вы хотите разрабатывать?",                     new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Драйвера\nдля разнообразных устройств\nхочу работать с железом",         new List<int>{1}},
                                                                    { "Пока не определился",                                                    new List<int>{5}},
                                                                    { "Сайты и интернет-проекты",                                               new List<int>{2, 3}},
                                                                    { "Программы и игры\nдля телефонов и планшетов",                            new List<int>{4}}
                                                                  }},
            { "Чем вы занимаетесь?",                              new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Учусь в колледже или университете",                                      new List<int>{1, 2, 3, 4, 5}},
                                                                    { "Учусь в школе",                                                          new List<int>{1, 2, 3, 4, 5}},
                                                                    { "Работаю",                                                                new List<int>{1, 2, 3, 4, 5}},
                                                                    { "Учусь, но на пары не хожу",                                              new List<int>{1, 2, 3, 4, 5}}
                                                                  }},
            { "Где вы хотите работать?",                          new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "В банке или большой организации",                                        new List<int>{4}},
                                                                    { "В интернет-студии",                                                      new List<int>{3}},
                                                                    { "В стартапе",                                                             new List<int>{2, 3, 5}},
                                                                    { "Хочу стать фрилансером\nи работать сам на себя",                         new List<int>{2, 3}},
                                                                    { "На заводе или на предприятии,\nгде разрабатывают различные устройства",  new List<int>{1}}
                                                                  }},
            { "Что вам нравится больше?",                         new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Простая работа и сравнительно\nневысокая зарплата в начале",             new List<int>{3, 2, 5}},
                                                                    { "Сложная, но интересная\nи высокооплачиваемая работа",                    new List<int>{1, 4}}
                                                                  }},
            { "Сколько времени вы готовы потратить на обучение?", new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Готов годами курить мануалы,\nдостичь дзена и отрастить бороду",         new List<int>{1}},
                                                                    { "Не очень много,\nхочу поскорее начать\nработать и зарабатывать",         new List<int>{2, 5}},
                                                                    { "Долго, но не более пары лет",                                            new List<int>{3, 4}}
                                                                  }},
            { "Готовы ли вы постоянно изучать что-то новое?",     new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Да, готов всегда быть в тренде",                                         new List<int>{3}},
                                                                    { "Мне нужен язык,\nв котором всё будет стабильно",                         new List<int>{1, 2, 4, 5}}
                                                                  }},
            { "Изучали ли вы программирование ранее?",            new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Да, я уже владею одним или несколькими ЯП",                              new List<int>{1, 2, 3, 4, 5}},
                                                                    { "Да, но в совершенстве ничего не знаю",                                   new List<int>{1, 2, 3, 4, 5}},
                                                                    { "Совсем нет",                                                             new List<int>{1, 2, 3, 4, 5}}
                                                                  }},
            { "Что лучше? С чем бы вы хотели работать?",           new Dictionary<string, List<int>>
                                                                  { 
                                                                    { "Интерпретатор",                                                          new List<int>{2, 3, 5}},
                                                                    { "Компилятор",                                                             new List<int>{1, 4}},
                                                                    { "Чего? Я не знаю, что это.\nПокажите результаты",                         new List<int>{5}}
                                                                  }}
        };
    }
}
