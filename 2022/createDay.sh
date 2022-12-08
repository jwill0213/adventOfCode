#!/bin/sh

PROVIDED_DAY=$1
TITLE=$2

if [ "$PROVIDED_DAY" -lt 10 ]; then
    DAY="0${PROVIDED_DAY}"
else
    DAY="$PROVIDED_DAY"
fi

mkdir -p ./src/main/kotlin/

cp -R ./template ./src/main/kotlin/day_"$DAY"

sed -i "s/package template/package day_$DAY/; s/--day--/day_$DAY/" "./src/main/kotlin/day_$DAY/Main.kt"

curl --cookie "session=$AOC_COOKIE" https://adventofcode.com/2022/day/"$PROVIDED_DAY"/input > ./src/main/kotlin/day_"$DAY"/input.txt

printf "# Advent Of Code 2022 Day %s: %s\n\n## Part 1\nTBD\n\n---\n## Part 2\nTBD" "$DAY" "$TITLE" > ./src/main/kotlin/day_"$DAY"/README.md

printf "\n\n[**Day %s: %s**](/src/main/kotlin/day_%s/) - [AdventOfCode](https://adventofcode.com/2022/day/%s)" "$PROVIDED_DAY" "$TITLE" "$DAY" "$PROVIDED_DAY" >> README.md