from collections import defaultdict


def part1(lines, numSteps):
    template = lines[0]

    pairs = {p.split(' -> ')[0]: p.split(' -> ')[1] for p in lines[2:]}

    for step in range(1, numSteps+1):
        newTemplate = []
        for i in range(len(template)-1):
            currPair = template[i] + template[i+1]
            if currPair in pairs:
                newTemplate.extend(
                    [template[i], pairs[currPair]])
            else:
                newTemplate.append(currPair)
        newTemplate.append(template[-1])
        # print(f"STEP {step}")
        # print("".join(newTemplate))
        template = newTemplate[:]

    letterCount = defaultdict(int)
    for l in template:
        letterCount[l] += 1

    sortedValues = sorted(letterCount.items(), key=lambda x: x[1])

    mostCommon = sortedValues[-1]
    leastCommon = sortedValues[0]

    print(f"At step {step} the polymer is {len(template)} long. Most common character is {mostCommon} least common is {leastCommon} ")
    print(
        f"Solution is {mostCommon[1]} - {leastCommon[1]} = {mostCommon[1] - leastCommon[1]}")


def part2(lines, numSteps):
    template = lines[0]
    pairMap = defaultdict(int)
    letterCount = defaultdict(int)
    for i in range(len(template)-1):
        currPair = template[i] + template[i+1]
        letterCount[template[i]] += 1
        pairMap[currPair] += 1

    letterCount[template[-1]] += 1

    pairs = {p.split(' -> ')[0]: p.split(' -> ')[1] for p in lines[2:]}

    for step in range(1, numSteps+1):
        newPairMap = defaultdict(int)
        for pair, count in pairMap.items():
            if pair in pairs:
                newLetter = pairs[pair]
                letterCount[newLetter] += count
                newPairMap[pair[0] + newLetter] += count
                newPairMap[newLetter + pair[1]] += count

        pairMap = newPairMap

    sortedValues = sorted(list(letterCount.items()), key=lambda x: x[1])

    mostCommon = sortedValues[-1]
    leastCommon = sortedValues[0]

    print(f"At step {step} the polymer is {len(template)} long. Most common character is {mostCommon} least common is {leastCommon} ")
    print(
        f"Solution is {mostCommon[1]} - {leastCommon[1]} = {mostCommon[1] - leastCommon[1]}")


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines, 10)
    print("\n----- Part 2 -----\n")
    part2(lines, 40)
