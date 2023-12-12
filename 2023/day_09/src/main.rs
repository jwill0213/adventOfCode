use itertools::Itertools;

pub fn part1(input: &str) -> String {
    input
        .lines()
        .map(|l| {
            l.split_ascii_whitespace()
                .map(|s| s.parse::<i32>().unwrap())
                .collect::<Vec<i32>>()
        })
        .map(|env| find_next_value(env))
        .sum::<i32>()
        .to_string()
}

fn find_next_value(env: Vec<i32>) -> i32 {
    let next_row = env
        .iter()
        .tuple_windows()
        .map(|(a, b)| b - a)
        .collect::<Vec<i32>>();
    if (*next_row).into_iter().all(|x| *x == 0) {
        return *env.last().unwrap();
    }
    find_next_value(next_row) + env.last().unwrap()
}

pub fn part2(input: &str) -> String {
    input
        .lines()
        .map(|l| {
            l.split_ascii_whitespace()
                .map(|s| s.parse::<i32>().unwrap())
                .collect::<Vec<i32>>()
        })
        .map(|env| find_prev_value(env))
        .sum::<i32>()
        .to_string()
}

fn find_prev_value(env: Vec<i32>) -> i32 {
    let next_row = env
        .iter()
        .tuple_windows()
        .map(|(a, b)| b - a)
        .collect::<Vec<i32>>();
    if (*next_row).into_iter().all(|x| *x == 0) {
        return *env.first().unwrap();
    }
    env.first().unwrap() - find_prev_value(next_row)
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
