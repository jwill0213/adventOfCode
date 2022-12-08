def part1(lines):
    ventMap = {}
    for line in lines:
        coords = line.split(' -> ')
        x1, y1 = [int(x) for x in coords[0].split(',')]
        x2, y2 = [int(y) for y in coords[1].split(',')]

        if x1 != x2 and y1 != y2:
            # For part 1, we skip diagonal lines
            continue

        xdiff = abs(x1 - x2)
        ydiff = abs(y1 - y2)
        for x in range(xdiff + 1):
            if x2 > x1:
                currX = x1 + x
            elif x1 > x2:
                currX = x1 - x
            else:
                currX = x1

            for y in range(ydiff + 1):
                if y2 > y1:
                    currY = y1 + y
                elif y1 > y2:
                    currY = y1 - y
                else:
                    currY = y1

                locKey = (currX, currY)
                if locKey in ventMap:
                    ventMap[locKey] += 1
                else:
                    ventMap[locKey] = 1

    print(f"Vent Overlaps: {len([k for k,v in ventMap.items() if v >= 2])}")


def part2(lines):
    ventMap = {}
    for line in lines:
        coords = line.split(' -> ')
        x1, y1 = [int(x) for x in coords[0].split(',')]
        x2, y2 = [int(y) for y in coords[1].split(',')]

        xdiff = abs(x1 - x2) + 1
        ydiff = abs(y1 - y2) + 1
        for val in range(max(xdiff, ydiff)):
            if x2 > x1:
                currX = x1 + val
            elif x1 > x2:
                currX = x1 - val
            else:
                currX = x1

            if y2 > y1:
                currY = y1 + val
            elif y1 > y2:
                currY = y1 - val
            else:
                currY = y1

            locKey = (currX, currY)
            if locKey in ventMap:
                ventMap[locKey] += 1
            else:
                ventMap[locKey] = 1

    print(f"Vent Overlaps: {len([k for k,v in ventMap.items() if v >= 2])}")


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
