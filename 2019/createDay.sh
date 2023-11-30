#!/bin/sh

PROVIDED_DAY=$1
TITLE=$2
AOC_COOKIE=$(cat ../.aoc_cookie)

if [ "$PROVIDED_DAY" -lt 10 ]; then
    DAY="0${PROVIDED_DAY}"
else
    DAY="$PROVIDED_DAY"
fi

cp -R ./template ./day_"$DAY"

curl --cookie "session=$AOC_COOKIE" https://adventofcode.com/2019/day/"$PROVIDED_DAY"/input > ./day_"$DAY"/input.txt

printf "# Advent Of Code 2019 Day %s: %s\n\n## Part 1\nTBD\n\n---\n## Part 2\nTBD" "$DAY" "$TITLE" > ./day_"$DAY"/README.md

printf "\n\n[**Day %s: %s**](./day_%s/) - [AdventOfCode](https://adventofcode.com/2019/day/%s)" "$PROVIDED_DAY" "$TITLE" "$DAY" "$PROVIDED_DAY" >> README.md
