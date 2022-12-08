SPAWN_RATE = 6  # includes 0
INITIAL_DELAY = 2


def part1(lines, days):
    fishList = [int(f) for f in lines[0].split(',')]

    for _ in range(days):
        newFish = []
        for f in range(len(fishList)):
            fish = fishList[f]
            if fish == 0:
                newFish.append(SPAWN_RATE + INITIAL_DELAY)
                fish = SPAWN_RATE
            else:
                fish -= 1
            fishList[f] = fish
        fishList.extend(newFish[:])

    print(f"Total fish {len(fishList)}")


def part2(lines, days):
    fishList = [int(f) for f in lines[0].split(',')]
    fishDict = {}

    for f in fishList:
        if f in fishDict:
            fishDict[f] += 1
        else:
            fishDict[f] = 1

    for _ in range(days):
        newFish = {}
        for f, num in fishDict.items():
            if f == 0:
                # Set new fish to spawn + initial delay
                if SPAWN_RATE + INITIAL_DELAY in newFish:
                    newFish[SPAWN_RATE + INITIAL_DELAY] += num
                else:
                    newFish[SPAWN_RATE + INITIAL_DELAY] = num

                # set exisiting fish to spawn valuer
                if SPAWN_RATE in newFish:
                    newFish[SPAWN_RATE] += num
                else:
                    newFish[SPAWN_RATE] = num
            else:
                if f-1 in newFish:
                    newFish[f-1] += num
                else:
                    newFish[f-1] = num
        fishDict = newFish

    print(f"Total fish {sum(fishDict.values())}")


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines, 80)
    print("\n----- Part 2 -----\n")
    part2(lines, 256)
