pub fn part1(input: &str) -> String {
    let mut lines = input.lines();
    let times = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .split_ascii_whitespace()
        .map(|x| x.parse::<i32>().unwrap())
        .collect::<Vec<_>>();
    let records = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .split_ascii_whitespace()
        .map(|x| x.parse::<i32>().unwrap())
        .collect::<Vec<_>>();
    times
        .iter()
        .zip(records.iter())
        .map(|(time, record)| {
            (0..=*time)
                .filter_map(|t| {
                    if (t * (*time - t)) > *record {
                        Some(t)
                    } else {
                        None
                    }
                })
                .count()
        })
        .product::<usize>()
        .to_string()
}

pub fn part2(input: &str) -> String {
    let mut lines = input.lines();
    let time: usize = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .chars()
        .filter(|c| !c.is_ascii_whitespace())
        .collect::<String>()
        .parse()
        .unwrap();
    let record: usize = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .chars()
        .filter(|c| !c.is_ascii_whitespace())
        .collect::<String>()
        .parse()
        .unwrap();
    (0..=time)
        .filter_map(|t| {
            if (t * (time - t)) > record {
                Some(t)
            } else {
                None
            }
        })
        .count()
        .to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
