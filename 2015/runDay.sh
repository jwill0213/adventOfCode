#!/bin/sh

# Having issues with IntelliJ on my laptop so created a run script.

PROVIDED_DAY=$1

if [ "$PROVIDED_DAY" -lt 10 ]; then
    DAY="0${PROVIDED_DAY}"
else
    DAY="$PROVIDED_DAY"
fi

printf "\n\n########### COMPILING DAY %s ###########\n\n" "$DAY"

kotlinc src/main/kotlin/day_"$DAY"/Main.kt -include-runtime -d day.jar

printf "\n\n########### RUNNING DAY %s ###########\n" "$DAY"

java -jar day.jar

printf "\n\n########### CLEANING UP ###########\n\n"

rm -f day.jar

printf "\n\n########### DONE ###########\n\n"