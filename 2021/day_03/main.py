def part1(lines):
    gamma = ''
    epsilon = ''

    for col in range(len(lines[0])):
        num_ones = 0
        num_zeros = 0
        colVals = [line[col] for line in lines]
        for val in colVals:
            if val == '1':
                num_ones += 1
            elif val == '0':
                num_zeros += 1
        if num_ones > num_zeros:
            gamma += '1'
            epsilon += '0'
        elif num_zeros > num_ones:
            gamma += '0'
            epsilon += '1'
    epsilonRate = int(epsilon, base=2)
    gammaRate = int(gamma, base=2)
    print(
        f'Epislon: {epsilonRate}\nGamma: {gammaRate}\nPower Consumption: {epsilonRate * gammaRate}')


def part2(lines):
    oxygenRatingList = []
    co2ScrubberList = []
    onesList = []
    zerosList = []

    for line in lines:
        if line[0] == '1':
            onesList.append(line)
        else:
            zerosList.append(line)

    if len(onesList) >= len(zerosList):
        oxygenRatingList = onesList[:]
        co2ScrubberList = zerosList[:]
    else:
        oxygenRatingList = zerosList[:]
        co2ScrubberList = onesList[:]

    for col in range(1, len(oxygenRatingList[0])):
        if len(oxygenRatingList) > 1:
            onesList = []
            zerosList = []
            for rating in oxygenRatingList:
                if rating[col] == '1':
                    onesList.append(rating)
                else:
                    zerosList.append(rating)

            if len(onesList) >= len(zerosList):
                oxygenRatingList = onesList[:]
            else:
                oxygenRatingList = zerosList[:]

        if len(co2ScrubberList) > 1:
            onesList = []
            zerosList = []
            for rating in co2ScrubberList:
                if rating[col] == '1':
                    onesList.append(rating)
                else:
                    zerosList.append(rating)

            if len(onesList) >= len(zerosList):
                co2ScrubberList = zerosList[:]
            else:
                co2ScrubberList = onesList[:]

        if len(oxygenRatingList) == 1 and len(co2ScrubberList) == 1:
            break

    oxygenRating = int(oxygenRatingList[0], base=2)
    co2ScrubberRating = int(co2ScrubberList[0], base=2)
    print(
        f'Oxygen Rating: {oxygenRating}\nCO2 Rating: {co2ScrubberRating}\nLife Support Rating: {oxygenRating * co2ScrubberRating}')


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
