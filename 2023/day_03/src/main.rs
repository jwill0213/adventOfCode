use std::collections::HashMap;

pub fn part1(input: &str) -> String {
    let mut sum: usize = 0;
    for (y, line) in input.lines().enumerate() {
        let mut is_number: bool = false;
        let mut num_buffer = String::new();
        let mut found_symbol = false;
        for (x, c) in line.char_indices() {
            if c.is_digit(10) {
                if !is_number {
                    // Start of number
                    is_number = true;
                    num_buffer = c.to_string();
                    // Check for symbols
                    // Check top left
                    if x > 0 {
                        // Check left
                        let prev_char = line.chars().nth(x - 1).unwrap();
                        if !(prev_char.is_digit(10) || prev_char == '.') {
                            found_symbol = true;
                        }
                        // Check top left
                        if y > 0 {
                            let prev_line = input.lines().nth(y - 1).unwrap();
                            let mut prev_char = prev_line.chars().nth(x - 1).unwrap();
                            if !(prev_char.is_digit(10) || prev_char == '.') {
                                found_symbol = true;
                            }
                            prev_char = prev_line.chars().nth(x).unwrap();
                            if !(prev_char.is_digit(10) || prev_char == '.') {
                                found_symbol = true;
                            }
                        }
                        // Check bottom left
                        if y < input.lines().count() - 1 {
                            let next_line = input.lines().nth(y + 1).unwrap();
                            let mut prev_char = next_line.chars().nth(x - 1).unwrap();
                            if !(prev_char.is_digit(10) || prev_char == '.') {
                                found_symbol = true;
                            }
                            prev_char = next_line.chars().nth(x).unwrap();
                            if !(prev_char.is_digit(10) || prev_char == '.') {
                                found_symbol = true;
                            }
                        }
                    }
                } else {
                    // In multi digit number
                    num_buffer.push(c);
                    // Check above
                    if y > 0 {
                        let prev_line = input.lines().nth(y - 1).unwrap();
                        let prev_char = prev_line.chars().nth(x).unwrap();
                        if !(prev_char.is_digit(10) || prev_char == '.') {
                            found_symbol = true;
                        }
                    }
                    // check below
                    if y < input.lines().count() - 1 {
                        let next_line = input.lines().nth(y + 1).unwrap();
                        let prev_char = next_line.chars().nth(x).unwrap();
                        if !(prev_char.is_digit(10) || prev_char == '.') {
                            found_symbol = true;
                        }
                    }
                }
            } else {
                // Not a digit so check if this is the end of a number
                if is_number {
                    // End of number, check for symbols
                    // right of number is current spot
                    if !(c.is_digit(10) || c == '.') {
                        found_symbol = true;
                    }
                    // Check top
                    if y > 0 {
                        let prev_line = input.lines().nth(y - 1).unwrap();
                        let prev_char = prev_line.chars().nth(x).unwrap();
                        if !(prev_char.is_digit(10) || prev_char == '.') {
                            found_symbol = true;
                        }
                    }
                    // check bottom
                    if y < input.lines().count() - 1 {
                        let next_line = input.lines().nth(y + 1).unwrap();
                        let prev_char = next_line.chars().nth(x).unwrap();
                        if !(prev_char.is_digit(10) || prev_char == '.') {
                            found_symbol = true;
                        }
                    }

                    // If we found a symbol, add the number to the sum
                    if found_symbol {
                        sum += num_buffer.parse::<usize>().unwrap();
                    }
                    is_number = false;
                    num_buffer.clear();
                    found_symbol = false;
                }
            }
        }
        // If we are at the end of the line and we are in a number add if found symbol
        if is_number && found_symbol {
            sum += num_buffer.parse::<usize>().unwrap();
        }
    }
    sum.to_string()
}

pub fn part2(input: &str) -> String {
    let mut gear_map: HashMap<(usize, usize), Vec<usize>> = HashMap::new();
    for (y, line) in input.lines().enumerate() {
        let mut is_number: bool = false;
        let mut num_buffer = String::new();
        let mut gear_coord = (0, 0);
        let mut found_gear = false;
        for (x, c) in line.char_indices() {
            if c.is_digit(10) {
                if !is_number {
                    // Start of number
                    is_number = true;
                    num_buffer = c.to_string();
                    // Check for symbols
                    // Check top left
                    if x > 0 {
                        // Check left
                        let prev_char = line.chars().nth(x - 1).unwrap();
                        if prev_char == '*' {
                            gear_coord = (x - 1, y);
                            found_gear = true;
                        }
                        // Check top left
                        if y > 0 {
                            let prev_line = input.lines().nth(y - 1).unwrap();
                            let mut prev_char = prev_line.chars().nth(x - 1).unwrap();
                            if prev_char == '*' {
                                gear_coord = (x - 1, y - 1);
                                found_gear = true;
                            }
                            prev_char = prev_line.chars().nth(x).unwrap();
                            if prev_char == '*' {
                                gear_coord = (x, y - 1);
                                found_gear = true;
                            }
                        }
                        // Check bottom left
                        if y < input.lines().count() - 1 {
                            let next_line = input.lines().nth(y + 1).unwrap();
                            let mut prev_char = next_line.chars().nth(x - 1).unwrap();
                            if prev_char == '*' {
                                gear_coord = (x - 1, y + 1);
                                found_gear = true;
                            }
                            prev_char = next_line.chars().nth(x).unwrap();
                            if prev_char == '*' {
                                gear_coord = (x, y + 1);
                                found_gear = true;
                            }
                        }
                    }
                } else {
                    // In multi digit number
                    num_buffer.push(c);
                    // Check above
                    if y > 0 {
                        let prev_line = input.lines().nth(y - 1).unwrap();
                        let prev_char = prev_line.chars().nth(x).unwrap();
                        if prev_char == '*' {
                            gear_coord = (x, y - 1);
                            found_gear = true;
                        }
                    }
                    // check below
                    if y < input.lines().count() - 1 {
                        let next_line = input.lines().nth(y + 1).unwrap();
                        let prev_char = next_line.chars().nth(x).unwrap();
                        if prev_char == '*' {
                            gear_coord = (x, y + 1);
                            found_gear = true;
                        }
                    }
                }
            } else {
                // Not a digit so check if this is the end of a number
                if is_number {
                    // End of number, check for symbols
                    // right of number is current spot
                    if c == '*' {
                        gear_coord = (x, y);
                        found_gear = true;
                    }
                    // Check top
                    if y > 0 {
                        let prev_line = input.lines().nth(y - 1).unwrap();
                        let prev_char = prev_line.chars().nth(x).unwrap();
                        if prev_char == '*' {
                            gear_coord = (x, y - 1);
                            found_gear = true;
                        }
                    }
                    // check bottom
                    if y < input.lines().count() - 1 {
                        let next_line = input.lines().nth(y + 1).unwrap();
                        let prev_char = next_line.chars().nth(x).unwrap();
                        if prev_char == '*' {
                            gear_coord = (x, y + 1);
                            found_gear = true;
                        }
                    }

                    // If we found a symbol, add the number to the sum
                    if found_gear {
                        if gear_map.contains_key(&gear_coord) {
                            gear_map
                                .get_mut(&gear_coord)
                                .unwrap()
                                .push(num_buffer.parse::<usize>().unwrap());
                        } else {
                            gear_map.insert(gear_coord, vec![num_buffer.parse::<usize>().unwrap()]);
                        }
                    }
                    is_number = false;
                    num_buffer.clear();
                    found_gear = false;
                }
            }
        }
        // If we are at the end of the line and we are in a number add if found symbol
        if is_number && found_gear {
            if gear_map.contains_key(&gear_coord) {
                gear_map
                    .get_mut(&gear_coord)
                    .unwrap()
                    .push(num_buffer.parse::<usize>().unwrap());
            } else {
                gear_map.insert(gear_coord, vec![num_buffer.parse::<usize>().unwrap()]);
            }
        }
    }

    gear_map
        .into_iter()
        .filter(|(_, v)| v.len() == 2)
        .map(|(_, v)| v.iter().product::<usize>())
        .sum::<usize>()
        .to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
