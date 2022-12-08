def part1(lines):
    lowPoints = []
    maxIndexX = len(lines[0]) - 1
    maxIndexY = len(lines) - 1
    for y in range(len(lines)):
        for x in range(len(lines[0])):
            up = down = left = right = False
            currVal = int(lines[y][x])
            if y == 0 or int(lines[y-1][x]) > currVal:
                up = True
            if x == 0 or int(lines[y][x-1]) > currVal:
                left = True
            if y == maxIndexY or int(lines[y+1][x]) > currVal:
                down = True
            if x == maxIndexX or int(lines[y][x+1]) > currVal:
                right = True

            if up and down and left and right:
                lowPoints.append(currVal)
    print(f"Points: {lowPoints}\nRisk Level: {sum([1+h for h in lowPoints])}")


def part2():
    lowPoints = []
    maxIndex = (len(lines[0]) - 1, len(lines) - 1)
    for y in range(len(lines)):
        for x in range(len(lines[0])):
            up = down = left = right = False
            currVal = int(lines[y][x])
            if y == 0 or int(lines[y-1][x]) > currVal:
                up = True
            if x == 0 or int(lines[y][x-1]) > currVal:
                left = True
            if y == maxIndex[1] or int(lines[y+1][x]) > currVal:
                down = True
            if x == maxIndex[0] or int(lines[y][x+1]) > currVal:
                right = True

            if up and down and left and right:
                lowPoints.append((x, y))

    basins = []
    for point in lowPoints:
        basinPoints = {point}

        surroundingPoints = findSurroundingPoints(point, lines, maxIndex)

        while len(surroundingPoints) > 0:
            nextPoints = []
            for s in surroundingPoints:
                if s not in basinPoints:
                    nextPoints.extend(
                        findSurroundingPoints(s, lines, maxIndex))
                    basinPoints.add(s)
            surroundingPoints = nextPoints[:]
        print("Low", point, "total", len(basinPoints), "points", basinPoints)
        basins.append(len(basinPoints))

    # Sort to put biggest 3 at the end
    basins.sort()
    print(sorted(basins))

    answer = basins[-1] * basins[-2] * basins[-3]
    print(
        f"Three largest sizes {basins[-1]} {basins[-2]} {basins[-3]} = {answer}")


def findSurroundingPoints(currPoint, allPoints, maxIndex):
    x, y = currPoint
    newPoints = []

    # get left point
    if not x - 1 < 0 and int(allPoints[y][x-1]) < 9:
        newPoints.append((x-1, y))
    # get right point
    if not x + 1 > maxIndex[0] and int(allPoints[y][x+1]) < 9:
        newPoints.append((x+1, y))
    # get up point
    if not y - 1 < 0 and int(allPoints[y-1][x]) < 9:
        newPoints.append((x, y-1))
    # get down point
    if not y + 1 > maxIndex[1] and int(allPoints[y+1][x]) < 9:
        newPoints.append((x, y+1))

    return newPoints


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
