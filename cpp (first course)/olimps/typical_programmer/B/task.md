#Задача B: Банковская карта

Лимит времени 1 секунда

Лимит памяти 64 мегабайт

#Легенда:
На магнитной полосе банковской карты система ASIV записано 80 двоичных цифр
(каждая – либо единица либо ноль). Каждые четыре двоичные цифры обозначают одно
шестнадцатиричное число. Таким образом, магнитная полоса банковской карты ASIV позволяет
закодировать 20 шестнадцатеричных цифр.
Поскольку банковскую карту можно проводить в обоих направлениях, необходим маркер
начала. Маркер начала – это четыре единицы (то есть кодируют десятичное число 15). Маркер
начала может занимать ЛИБО первые четыре позиции, либо последние, но не обе позиции
одновременно.
Вы пишете модуль, который определяет, были ли данные корректно считаны с банковской
карты ASIV и, при необходимости, разворачивает их. Данные могли быть считаны корректно
тогда и только тогда когда в них ровно 80 двоичных цифр, и либо в конце либо в начале стоит
маркер начала (четыре единицы).

#Формат входных данных:
Все данные подаются программе через стандартный поток ввода (stdin)
В единственной строке входных данных введены цифры, считанные с карты.
Гарантируется, что каждая цифра – либо единица, либо ноль (больше ничего магнитный
считыватель получать просто не умеет). Однако цифр может быть не 80 (например, если
пользователь не закончил проведение карты).

#Формат выходных данных:
В единственной строке выходного файла выведите восемьдесят нулей, если данные не
могли быть считаны с карты.
Если данные могли быть считаны, выведите данные с карты в правильном направлении
(то есть вам необходимо развернуть данные, если при считывании маркер начала оказался в
конце).
