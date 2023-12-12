use num::integer::lcm;
use std::{collections::HashMap, str::Lines};

fn parse_nodes(input: Lines<'_>) -> HashMap<&str, (&str, &str)> {
    let mut nodes = HashMap::new();
    for line in input {
        let (id, directions) = line.split_once(" = ").unwrap();
        let (left, right) = directions[1..directions.len() - 1]
            .split_once(", ")
            .unwrap();
        nodes.insert(id, (left, right));
    }
    nodes
}

pub fn part1(input: &str) -> String {
    let mut input_iter = input.lines();
    let mut directions = input_iter.next().unwrap().chars().cycle();
    // println!("{:?}", directions);
    // Skip blank line
    input_iter.next();
    let nodes = parse_nodes(input_iter);
    // println!("{:?}", nodes);
    let mut curr_node = "AAA";
    let mut num_steps = 0;
    loop {
        num_steps += 1;
        let d = directions.next().unwrap();
        let (left, right) = nodes.get(curr_node).unwrap();
        match d {
            'L' => curr_node = left,
            'R' => curr_node = right,
            _ => panic!("Invalid direction"),
        }
        if curr_node == "ZZZ" {
            break;
        }
    }
    num_steps.to_string()
}

pub fn part2(input: &str) -> String {
    let mut input_iter = input.lines();
    let mut directions = input_iter.next().unwrap().chars().cycle();
    // println!("{:?}", directions);
    // Skip blank line
    input_iter.next();
    let nodes = parse_nodes(input_iter);
    // println!("{:?}", nodes);
    let curr_nodes: Vec<&str> = nodes
        .keys()
        .filter(|n| n.ends_with("A"))
        .map(|n| *n)
        .collect();

    curr_nodes
        .into_iter()
        .filter_map(|n| {
            println!("{}", n);
            if (*n).ends_with("A") {
                let mut curr_node = n;
                let mut num_steps: usize = 0;
                loop {
                    num_steps += 1;
                    let d = directions.next().unwrap();
                    let (left, right) = nodes.get(curr_node).unwrap();
                    match d {
                        'L' => curr_node = left,
                        'R' => curr_node = right,
                        _ => panic!("Invalid direction"),
                    }
                    if curr_node.ends_with("Z") {
                        break;
                    }
                }
                Some(num_steps)
            } else {
                None
            }
        })
        .reduce(|a, b| lcm(a, b))
        .unwrap()
        .to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
