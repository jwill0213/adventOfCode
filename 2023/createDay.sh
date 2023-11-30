#!/bin/zsh

PROVIDED_DAY=$1
TITLE=$2
YEAR=2023

if [ "$PROVIDED_DAY" -lt 10 ]; then
    DAY="0${PROVIDED_DAY}"
else
    DAY="$PROVIDED_DAY"
fi

mkdir -p ./day_${DAY}

cp -R ./template/* ./day_"$DAY"

sed -i "s/template/day_${DAY}/" "./day_${DAY}/cargo.toml"
sed -i "s/template/day_${DAY}/" "./day_${DAY}/cargo.lock"

sed -i "s/--title--/$TITLE/; s/--day--/$PROVIDED_DAY/; s/--year--/$YEAR/" "./day_$DAY/README.md"

printf "\n\n[**Day %s: %s**](day_%s/) - [AdventOfCode](https://adventofcode.com/%s/day/%s)" "$PROVIDED_DAY" "$TITLE" "$DAY" "$YEAR" "$PROVIDED_DAY" >> README.md

#curl --cookie "session=$AOC_COOKIE" https://adventofcode.com/2023/day/"$PROVIDED_DAY"/input > ./day_"${DAY}"/input.txt