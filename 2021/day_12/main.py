def part1(lines):
    startingConnections = [l for l in lines if "start" in l]

    paths = []
    for point in startingConnections:
        p1, p2 = point.split('-')
        if p1 == 'start':
            paths.append([p1, p2])
        else:
            paths.append([p2, p1])

    allConnections = set(lines) - set(startingConnections)
    allConnections = [tuple(c.split('-')) for c in allConnections]

    newPaths = []
    finishedPaths = []
    morePaths = True
    while morePaths:
        morePaths = False
        newPaths = []
        for path in paths:
            currPoint = path[-1]
            possibleMoves = []
            for c in allConnections:
                if currPoint == c[0]:
                    possibleMoves.append(c[1])
                elif currPoint == c[1]:
                    possibleMoves.append(c[0])

            for move in possibleMoves:
                if move == 'end':
                    # print("Finished Path", [*path, move])
                    finishedPaths.append([*path, move])
                elif move.islower() and move in path:
                    # print("Dead End", move, [*path, move])
                    continue
                else:
                    # print("Continued Path", move, [*path, move])
                    newPaths.append([*path, move])

        if len(newPaths) > 0:
            morePaths = True
            paths = newPaths[:]

    print("Number of paths:", len(finishedPaths))


def part2(lines):
    startingConnections = [l for l in lines if "start" in l]

    paths = []
    for point in startingConnections:
        p1, p2 = point.split('-')
        if p1 == 'start':
            paths.append([p1, p2])
        else:
            paths.append([p2, p1])

    allConnections = set(lines) - set(startingConnections)
    allConnections = [tuple(c.split('-')) for c in allConnections]

    newPaths = []
    finishedPaths = []
    morePaths = True
    while morePaths:
        morePaths = False
        newPaths = []
        for path in paths:
            currPoint = path[-1]
            possibleMoves = []
            for c in allConnections:
                if currPoint == c[0]:
                    possibleMoves.append(c[1])
                elif currPoint == c[1]:
                    possibleMoves.append(c[0])

            for move in possibleMoves:
                if move == 'end':
                    # print("Finished Path", [*path, move])
                    finishedPaths.append([*path, move])
                elif move.islower():
                    # Get list of all lower caves already visited
                    smallCaves = [s for s in path if s.islower()]
                    # If the length of the set is smaller than the length of the list, that means a duplicate exists and we can't visit any more small caves
                    if len(smallCaves) > len(set(smallCaves)) and move in path:
                        continue
                    else:
                        newPaths.append([*path, move])
                else:
                    # print("Continued Path", move, [*path, move])
                    newPaths.append([*path, move])

        if len(newPaths) > 0:
            morePaths = True
            paths = newPaths[:]

    print("Number of paths:", len(finishedPaths))


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
