pub fn part1(input: &str) -> String {
    let mut lines: std::str::Lines<'_> = input.lines().into_iter();
    // get vec of all seeds
    let mut seeds: Vec<u32> = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .split_ascii_whitespace()
        .map(|c| c.parse::<u32>().unwrap())
        .collect();
    // Skip blank line
    lines.next();
    let transform_map: Vec<Vec<Vec<u32>>> = parse_transform_map(&mut lines);
    // transform_map.iter().for_each(|t| println!("{:?}", t));
    transform_map.iter().for_each(|t| {
        // println!("{:?}", t);
        for seed in seeds.iter_mut() {
            let mut new_seed: u32 = seed.clone();
            for transform in t.iter() {
                if transform[1] <= new_seed && (new_seed - transform[1]) < transform[2] {
                    new_seed = (new_seed - transform[1]) + transform[0];
                    // Only transform one time
                    break;
                }
            }
            // println!("Seed {} transformed to {}", seed, new_seed);
            *seed = new_seed;
        }
    });
    seeds.iter().min().unwrap().to_string()
}

pub fn part2(input: &str) -> String {
    let mut lines: std::str::Lines<'_> = input.lines().into_iter();
    // get vec of all seeds
    let seed_ranges: Vec<_> = lines
        .next()
        .unwrap()
        .split_once(":")
        .unwrap()
        .1
        .split_ascii_whitespace()
        .map(|c| c.parse::<u32>().unwrap())
        .collect::<Vec<_>>()
        .chunks(2)
        .map(|s| (s[0]..s[0] + s[1]))
        .collect();
    // println!("{:?}", seeds);
    // Skip blank line
    lines.next();
    let transform_map: Vec<Vec<Vec<u32>>> = parse_transform_map(&mut lines);
    // transform_map.iter().for_each(|t| println!("{:?}", t));
    seed_ranges
        .iter()
        .map(|seeds| {
            println!("Starting range {:?}", seeds);
            seeds
                .clone()
                .into_iter()
                .map(|s| {
                    let mut new_seed: u32 = s.clone();
                    transform_map.iter().for_each(|t| {
                        // println!("{:?}", t);
                        for transform in t.iter() {
                            if transform[1] <= new_seed && (new_seed - transform[1]) < transform[2]
                            {
                                new_seed = (new_seed - transform[1]) + transform[0];
                                // Only transform one time
                                break;
                            }
                        }
                        // println!("Seed {} transformed to {}", seed, new_seed);
                    });
                    new_seed
                })
                .min()
                .unwrap()
        })
        .min()
        .unwrap()
        .to_string()
}

fn parse_transform_map(lines: &mut std::str::Lines<'_>) -> Vec<Vec<Vec<u32>>> {
    let mut all_transforms: Vec<Vec<Vec<u32>>> = Vec::new();
    let mut current_transform: Vec<Vec<u32>> = Vec::new();
    println!("Parsing Map");
    lines.next();
    loop {
        let line = lines.next();
        if line == None {
            break;
        }
        if line.unwrap().is_empty() {
            // Jump to heading so we skip it on next loop
            lines.next();
            all_transforms.push(current_transform.clone());
            current_transform.clear();
            continue;
        }
        let mut row: Vec<u32> = Vec::new();
        for c in line.unwrap().split_ascii_whitespace() {
            row.push(c.parse::<u32>().unwrap());
        }
        current_transform.push(row);
    }
    all_transforms.push(current_transform);
    all_transforms
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
