def part1(lines):
    computerInstructions = [int(x) for x in lines[0].split(",")]

    operandIndex = 0
    while computerInstructions[operandIndex] != 99:
        command = computerInstructions[operandIndex]
        if command == 1:
            computerInstructions[computerInstructions[operandIndex + 3]] = (
                computerInstructions[computerInstructions[operandIndex + 1]]
                + computerInstructions[computerInstructions[operandIndex + 2]]
            )
        elif command == 2:
            computerInstructions[computerInstructions[operandIndex + 3]] = (
                computerInstructions[computerInstructions[operandIndex + 1]]
                * computerInstructions[computerInstructions[operandIndex + 2]]
            )
        else:
            raise Exception(
                "Invalid operand {}. Must be 1, 2 or 99".format(int(command))
            )
        operandIndex += 4

    print(computerInstructions[0])


def part2(lines):
    defaultComputerInstructions = [int(x) for x in lines[0].split(",")]

    ANSWER_VALUE = 19690720

    for noun in range(100):
        for verb in range(100):
            computerInstructions = defaultComputerInstructions.copy()
            operandIndex = 0
            computerInstructions[1] = noun
            computerInstructions[2] = verb
            while computerInstructions[operandIndex] != 99:
                command = computerInstructions[operandIndex]
                val1Loc = computerInstructions[operandIndex + 1]
                val2Loc = computerInstructions[operandIndex + 2]
                ansLoc = computerInstructions[operandIndex + 3]
                if command == 1:
                    computerInstructions[ansLoc] = (
                        computerInstructions[val1Loc] + computerInstructions[val2Loc]
                    )
                elif command == 2:
                    computerInstructions[ansLoc] = (
                        computerInstructions[val1Loc] * computerInstructions[val2Loc]
                    )
                else:
                    raise Exception(
                        "Invalid operand {}. Must be 1, 2 or 99".format(int(command))
                    )
                operandIndex += 4
            print("{}, {} = {}".format(noun, verb, computerInstructions[0]))
            if computerInstructions[0] == ANSWER_VALUE:
                print("ANSWER FOUND. Noun {}, Verb: {}".format(noun, verb))
                ans = 100 * noun + verb
                print("Final answer 100 * {} + {} = {}".format(noun, verb, ans))
                exit(0)


if __name__ == "__main__":
    with open("input.txt") as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
