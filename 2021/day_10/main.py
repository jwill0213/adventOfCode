CHUNK_PAIRS = {
    '(': ')',
    '[': ']',
    '{': '}',
    '<': '>'
}


def part1(lines):
    score_map = {
        ')': 3,
        ']': 57,
        '}': 1197,
        '>': 25137
    }

    score = 0
    currentChunk = []
    for line in lines:
        for c in line:
            if c in CHUNK_PAIRS.keys():
                # open chunck so append to current chunk
                currentChunk.append(c)
            else:
                lastChunkOpen = currentChunk.pop()
                if CHUNK_PAIRS[lastChunkOpen] != c:
                    # invalid character
                    score += score_map[c]
                    break

    print(f"Syntax Error Score {score}")


def part2(lines):
    score_map = {
        ')': 1,
        ']': 2,
        '}': 3,
        '>': 4
    }

    lineScores = []
    for line in lines:
        currentChunk = []
        invalidLine = False
        for c in line:
            if c in CHUNK_PAIRS.keys():
                # open chunck so append to current chunk
                currentChunk.append(c)
            else:
                lastChunkOpen = currentChunk.pop()
                if CHUNK_PAIRS[lastChunkOpen] != c:
                    # invalid character ignore the line
                    invalidLine = True
                    break
        if not invalidLine:
            lineSolution = [CHUNK_PAIRS[c] for c in currentChunk[::-1]]
            lineScore = 0
            for s in lineSolution:
                lineScore *= 5
                lineScore += score_map[s]

            print("".join(lineSolution), lineScore)
            lineScores.append(lineScore)

    lineScores.sort()
    print(f"Syntax Error Score {lineScores[int((len(lineScores)-1)/2)]}")


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
