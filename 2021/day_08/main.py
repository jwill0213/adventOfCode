def part1(lines):
    numOfVals = 0

    for line in lines:
        # Ignore the unique pattern for part 1
        _, outputVal = line.split(' | ')

        for val in outputVal.split():
            if len(val) in [2, 3, 4, 7]:
                numOfVals += 1

    print(f"Numer of appearences: {numOfVals}")


def part2(lines):
    sumOfOutputs = 0

    for line in lines:
        segments = {'a': None, 'b': None, 'c': None,
                    'd': None, 'e': None, 'f': None, 'g': None}
        signalPattern, outputVal = line.split(' | ')

        # Sort by length
        signalPattern = signalPattern.split()
        signalPattern.sort(key=len)

        # First 2 indexes will always be 2 and 3 characters long representing 1 and 7
        # The value of segment a will always be the segment in 7 that is different from the 2 segments in 1
        segments['a'] = getSegmentA(signalPattern[0], signalPattern[1])

        # We can solve for f and then since f and c makeup 1 we can solve for c
        segments['f'], segments['c'] = getSegmentFandC(signalPattern)

        # Segment d is on for all but 3 numbers (0,1,7), since we know index 0 and 1 are 1 and 7 respectivley, we can ignore those
        segments['d'], segments['g'] = getSegmentDandG(
            signalPattern, getSolvedSegmentValues(segments))

        segments['b'], segments['e'] = getSegmentBandE(
            signalPattern, getSolvedSegmentValues(segments))

        numberMap = convertSegmentsToNumgers(segments)

        output = outputVal.split()
        outputNumberList = []
        for o in output:
            number = numberMap["".join(sorted(o))]
            outputNumberList.append(str(number))
        sumOfOutputs += int("".join(outputNumberList))

    print(f"Sum of outputs: {sumOfOutputs}")


def getSegmentA(onePattern, sevenPattern):
    return [x for x in sevenPattern if x not in onePattern][0]


def getSegmentFandC(signalPattern):
    # There is only one number, 2, that has segment F turned off. So if we find the one letter that is in 9 of 10 entries we know that is F
    # 8(last element in list) has all segments so we will loop through that until we find the 1 character that is in all but one pattern
    segmentF, segmentC = None, None
    for c in signalPattern[-1]:
        entries = [p for p in signalPattern if c not in p]
        if len(entries) == 1:
            segmentF = c

    # Loop through the 2 characters of the first element to find the one that is segmentC
    for c in signalPattern[0]:
        if c != segmentF:
            segmentC = c

    return segmentF, segmentC


def getSegmentDandG(signalPattern, solved):
    # Only 1 pattern will be missing the d segment, number 0, so we find the one character that is in 7 of 8 entries
    # 8(last element in list) has all segments so we will loop through that until we find the 2 characters that have 6 matches
    patternMatches = {}

    # We can remove the first 2 and the last element since that is 1, 7 and 8 which we alread know.
    shortPattern = signalPattern[2:-1]
    for c in signalPattern[-1]:
        if c in solved:
            continue
        entries = [p for p in shortPattern if c in p]
        if len(entries) == 6:
            patternMatches[c] = entries

    # There should be 2 pattern matches. If a list contains an entry with a length of 4 it is segment d since the number 4 is unique and had d on but not g
    # The other entry is g segment
    segmentD, segmentG = None, None
    for c in patternMatches.keys():
        containsFour = [p for p in patternMatches[c] if len(p) == 4]
        if len(containsFour) > 0:
            segmentD = c
        else:
            segmentG = c
    return segmentD, segmentG


def getSegmentBandE(signalPattern, solved):
    # Get the patterns that are 6 characters long, which represent 0, 6, and 9
    shortPattern = [p for p in signalPattern if len(p) == 6]

    # We assume we have solved for all but segments b and e at this point. All 3 numbers will have e solved so if the caracter is not solved and is in all 3 we know it is e
    segmentE, segmentB = None, None
    for c in signalPattern[-1]:
        if c in solved:
            continue
        entries = [p for p in shortPattern if c in p]
        if len(entries) == 3:
            segmentB = c

    for c in 'abcdefg':
        if c not in solved and c != segmentB:
            segmentE = c

    return segmentB, segmentE


def convertSegmentsToNumgers(segments):
    numberMap = {}
    # 0 is all segments except d
    numberMap[0] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'd']))
    # 1 is only segments c and f
    numberMap[1] = "".join(sorted(
        [v for k, v in segments.items() if k in 'cf']))
    # 2 is all segments except b and f
    numberMap[2] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'bf']))
    # 3 is all segments except b and e
    numberMap[3] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'be']))
    # 4 is all segments except a, e, and g
    numberMap[4] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'aeg']))
    # 5 is all segments except c and e
    numberMap[5] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'ce']))
    # 6 is all segments except c
    numberMap[6] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'c']))
    # 7 is segments a, c, and f
    numberMap[7] = "".join(sorted(
        [v for k, v in segments.items() if k in 'acf']))
    # 8 is all segments
    numberMap[8] = "".join(sorted(segments.values()))
    # 9 is all segments except e
    numberMap[9] = "".join(sorted(
        [v for k, v in segments.items() if k not in 'e']))

    # Make the string the key to make lookup easier when converting string to a specific number
    return {v: k for k, v in numberMap.items()}


def getSolvedSegmentValues(segments):
    return [v for k, v in segments.items() if v is not None]


'''
  aaaa   
 b    c  
 b    c  
  dddd  
 e    f  
 e    f  
  gggg 
'''


if __name__ == "__main__":
    with open('input.txt') as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
