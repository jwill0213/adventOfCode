import math


def part1(lines):
    total_fuel = 0
    for object_mass in lines:
        fuel_needed = math.floor(int(object_mass) / 3.0) - 2
        total_fuel += fuel_needed

    print(total_fuel)


def part2(lines):
    total_fuel = 0
    for object_mass in lines:
        fuel_needed = recursiveCalcFuel(object_mass)
        total_fuel += fuel_needed

    print(total_fuel)


def recursiveCalcFuel(mass):
    fuel = math.floor(mass / 3.0) - 2
    if fuel > 0:
        return fuel + recursiveCalcFuel(fuel)
    else:
        return 0


if __name__ == "__main__":
    with open("input.txt") as f:
        lines = [line.rstrip() for line in f.readlines()]

    print("\n----- Part 1 -----\n")
    part1(lines)
    print("\n----- Part 2 -----\n")
    part2(lines)
