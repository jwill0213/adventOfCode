pub fn part1(input: &str) -> i32 {
    input
        .lines()
        .map(|line| {
            let digits = line
                .chars()
                .filter(|c| c.is_digit(10))
                .collect::<Vec<char>>();
            let num_string: String =
                format!("{}{}", digits.first().unwrap(), digits.last().unwrap());
            num_string.parse::<i32>().unwrap()
        })
        .sum()
}

pub fn part2(input: &str) -> i32 {
    input
        .lines()
        .map(|line| {
            let mut result_string: String = String::new();

            line.chars().enumerate().for_each(|(idx, c)| {
                if c.is_digit(10) {
                    result_string.push(c);
                } else if line[idx..].starts_with("one") {
                    result_string.push('1');
                } else if line[idx..].starts_with("two") {
                    result_string.push('2');
                } else if line[idx..].starts_with("three") {
                    result_string.push('3');
                } else if line[idx..].starts_with("four") {
                    result_string.push('4');
                } else if line[idx..].starts_with("five") {
                    result_string.push('5');
                } else if line[idx..].starts_with("six") {
                    result_string.push('6');
                } else if line[idx..].starts_with("seven") {
                    result_string.push('7');
                } else if line[idx..].starts_with("eight") {
                    result_string.push('8');
                } else if line[idx..].starts_with("nine") {
                    result_string.push('9');
                }
            });
            let digits = result_string
                .chars()
                .filter(|c| c.is_digit(10))
                .collect::<Vec<char>>();
            let num_string: String =
                format!("{}{}", digits.first().unwrap(), digits.last().unwrap());
            num_string.parse::<i32>().unwrap()
        })
        .sum()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
