def part1(lines):
    dotCoords = [l for l in lines if 'fold' not in l and l != '']
    dotCoords = list(map(lambda x: (
        int(x.split(',')[0]), int(x.split(',')[1])), dotCoords))

    folds = [f.split()[2] for f in lines if 'fold' in f]

    maxX = max(dotCoords, key=lambda x: x[0])
    maxY = max(dotCoords, key=lambda x: x[1])

    maxIndex = (maxX[0], maxY[1])
    print(maxIndex)

    f = folds[0]
    direction, coord = f.split('=')
    coord = int(coord)

    bigNumList = []
    smallNumList = []
    if direction == 'y':
        bigNumList = [(b[0], maxIndex[1]-b[1])
                      for b in dotCoords if b[1] > coord]
        smallNumList = [b for b in dotCoords if b[1] < coord]
    else:
        bigNumList = [(maxIndex[0]-b[0], b[1])
                      for b in dotCoords if b[0] > coord]
        smallNumList = [b for b in dotCoords if b[0] < coord]

    combinedSet = set.union(set(bigNumList), set(smallNumList))
    print(f)
    print("big", bigNumList)
    print("small", smallNumList)
    print("Set", len(combinedSet))


def part2(lines):
    dotCoords = [l for l in lines if 'fold' not in l and l != '']
    dotCoords = list(map(lambda x: (
        int(x.split(',')[0]), int(x.split(',')[1])), dotCoords))

    folds = [f.split()[2] for f in lines if 'fold' in f]

    for f in folds:
        maxX = max(dotCoords, key=lambda x: x[0])[0]
        maxY = max(dotCoords, key=lambda x: x[1])[1]

        direction, coord = f.split('=')
        coord = int(coord)

        bigNumList = []
        smallNumList = []
        if direction == 'y':
            bigNumList = [(b[0], maxY-b[1])
                          for b in dotCoords if b[1] > coord]
            smallNumList = [b for b in dotCoords if b[1] < coord]
        else:
            bigNumList = [(maxX-b[0], b[1])
                          for b in dotCoords if b[0] > coord]
            smallNumList = [b for b in dotCoords if b[0] < coord]

        combinedSet = set.union(set(bigNumList), set(smallNumList))
        dotCoords = list(combinedSet)

    solutionMap = []
    for y in range(maxY + 1):
        solutionMap.append([])
        for x in range(maxX + 1):
            solutionMap[y].append('.')

    print(len(dotCoords))

    for c in dotCoords:
        x, y = c
        solutionMap[y][x] = '#'

    for y in solutionMap:
        print("".join(y))


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
