#Задача F: Пароль – 2 

Лимит времени 1 секунда

Лимит памяти 64 мегабайт

#Легенда:
Для того, чтобы проверить введенный пользователь пароль, на сервере не хранится сам
пароль открытым текстом, но хранится его хеш-сумма. Хеш-сумма – это десятичное число,
такое, что одной хеш-сумме обычно соответствует только одна строка.
Группа хакеров хочет взломать пароль от сервера http://ejudge.tproger.ru и проставить себе
все задачки как решенные. Подсмотрев за администратором в кафе, они обнаружили, что в
пароле n символов, причем все – маленькие английский символы из верхней строки стандартной
qwerty-клавиатуры. Злоумышленникам так же известен алгоритм вычисления хеш-суммы:
-Предположим пароль – это строка
####s=s0s1s2...sn−2sn−1

-Хеш-суммой h этой строки является следующее число:
####h=((f(s0)⋅p^0)+(f(s1)⋅p^1)+f(s2)⋅p^2+f(s3)⋅p^3+...+f(sn−1)⋅p^n−1)mod p2

-Где функция a mod b – остаток от деления числа a на b (78 mod 77 = 1), f(c) – функция,
переводящая маленькую английскую букву в ее номер в английском алфавите, начиная с 0 (f('a')
= 0), а числа p1 и p2 хакерам известны (из источников, которые пожелали сохранить анонимность потому что всегда хотели побыть анонимными источниками).
Вам предстоит написать программу, которая выводит список возможных паролей администратора сервера.

#Формат входных данных:
В первой строке через пробел подаются числа n, p1, p2
Во второй строке подается единственное число h.
Гарантируется, что 17< p1<63 , p2<2
64 . Число n меньше 8.

#Формат выходных данных:
Программа должна вывести столько строк, сколько надо для того, чтобы вывести все
пароли, хеш-сумма которых равна h.
