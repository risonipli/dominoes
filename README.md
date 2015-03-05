# dominoes

pinpong 0.0.1

Usage: pinpong [options]

# Опции

Костяшки домино. Программа проверит является ли данный набор костяшек базаром.

    -t (side1,side2) (side1,side2)... | --tiles (side1,side2) (side1,side2)...

Длина генерируемых базаров. Программа выведет список базаров с указанным числом костяшек.

    -l <value> | --bazaar-length <value>
    
Пример запуска

> sbt "run-main Main -t \"(1,0) (0,6) (0,0) (0,3) (0,5) (5,5)\" -l 5"
