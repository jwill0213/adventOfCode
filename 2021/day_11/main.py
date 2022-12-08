def part1(lines):
    STEPS_TO_RUN = 100

    octopuses = [[int(num) for num in line] for line in lines]

    maxIndex = (len(octopuses[0]) - 1, len(octopuses) - 1)
    numFlashes = 0

    for step in range(STEPS_TO_RUN):
        # Incerement enegery of all indexes by 1
        for y in range(len(octopuses)):
            for x in range(len(octopuses[0])):
                octopuses[y][x] += 1

        # "Flash" all indexes that are at 9 or greater
        flashLoop = True
        while flashLoop:
            flashLoop = False
            for y in range(len(octopuses)):
                for x in range(len(octopuses[0])):
                    if octopuses[y][x] != '*' and octopuses[y][x] > 9:
                        octopuses[y][x] = '*'
                        numFlashes += 1
                        incrementList = findSurroundingPoints(
                            (x, y), maxIndex)
                        for point in incrementList:
                            x, y = point
                            if octopuses[y][x] != '*':
                                octopuses[y][x] += 1
                                flashLoop = True

        # Set all flashed octopuses, *,  to 0
        for y in range(len(octopuses)):
            for x in range(len(octopuses[0])):
                if octopuses[y][x] == '*':
                    octopuses[y][x] = 0

        if step+1 == 100:
            print("Flashes at step 100:", numFlashes)


def part2(lines):
    octopuses = [[int(num) for num in line] for line in lines]

    maxIndex = (len(octopuses[0]) - 1, len(octopuses) - 1)
    totalOctopuses = len(octopuses[0]) * len(octopuses)
    numFlashes = 0

    allFlashed = False
    step = 0
    while not allFlashed:
        flashesThisStep = 0
        step += 1
        # Incerement enegery of all indexes by 1
        for y in range(len(octopuses)):
            for x in range(len(octopuses[0])):
                octopuses[y][x] += 1

        # "Flash" all indexes that are at 9 or greater
        flashLoop = True
        while flashLoop:
            flashLoop = False
            for y in range(len(octopuses)):
                for x in range(len(octopuses[0])):
                    if octopuses[y][x] != '*' and octopuses[y][x] > 9:
                        octopuses[y][x] = '*'
                        flashesThisStep += 1
                        incrementList = findSurroundingPoints(
                            (x, y), maxIndex)
                        flashLoop = True
                        for point in incrementList:
                            x, y = point
                            if octopuses[y][x] != '*':
                                octopuses[y][x] += 1

        # Set all flashed octopuses, *,  to 0
        for y in range(len(octopuses)):
            for x in range(len(octopuses[0])):
                if octopuses[y][x] == '*':
                    octopuses[y][x] = 0

        if flashesThisStep == totalOctopuses:
            allFlashed = True
            print("All Octopuses flashed at step", step)
        else:
            numFlashes += flashesThisStep


def findSurroundingPoints(currPoint, maxIndex):
    x, y = currPoint
    newPoints = []

    # get left point
    if not x - 1 < 0:
        newPoints.append((x-1, y))
    # get right point
    if not x + 1 > maxIndex[0]:
        newPoints.append((x+1, y))
    # get up point
    if not y - 1 < 0:
        newPoints.append((x, y-1))
    # get down point
    if not y + 1 > maxIndex[1]:
        newPoints.append((x, y+1))

    # get top left point
    if not x - 1 < 0 and not y - 1 < 0:
        newPoints.append((x-1, y-1))
    # get top right point
    if not x + 1 > maxIndex[0] and not y - 1 < 0:
        newPoints.append((x+1, y-1))
    # get bottom left point
    if not x - 1 < 0 and not y + 1 > maxIndex[1]:
        newPoints.append((x-1, y+1))
    # get bottom right point
    if not x + 1 > maxIndex[0] and not y + 1 > maxIndex[1]:
        newPoints.append((x+1, y+1))

    return newPoints


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
