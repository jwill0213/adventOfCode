#!/bin/sh

PROVIDED_DAY=$1
TITLE=$2
YEAR=2015

if [ "$PROVIDED_DAY" -lt 10 ]; then
    DAY="0${PROVIDED_DAY}"
else
    DAY="$PROVIDED_DAY"
fi

mkdir -p ./src/main/kotlin/

cp -R ./template ./src/main/kotlin/day_"$DAY"

sed -i "s/package template/package day_$DAY/; s/--day--/day_$DAY/" "./src/main/kotlin/day_$DAY/Main.kt"

sed -i "s/--title--/$TITLE/; s/--day--/$PROVIDED_DAY/; s/--year--/$YEAR/" "./src/main/kotlin/day_$DAY/README.md"

curl --cookie "session=$AOC_COOKIE" https://adventofcode.com/"$YEAR"/day/"$PROVIDED_DAY"/input > ./src/main/kotlin/day_"$DAY"/input.txt

printf "\n\n[**Day %s: %s**](/src/main/kotlin/day_%s/) - [AdventOfCode](https://adventofcode.com/%s/day/%s)" "$PROVIDED_DAY" "$TITLE" "$DAY" "$YEAR" "$PROVIDED_DAY" >> README.md
