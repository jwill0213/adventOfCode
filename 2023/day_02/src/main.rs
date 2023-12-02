pub fn part1(input: &str) -> i32 {
    let max_red = 12;
    let max_green = 13;
    let max_blue = 14;
    input
        .lines()
        .map(|line| {
            let (game, record) = line.split_once(": ").unwrap();
            (
                game.split_once(" ").unwrap().1,
                record.split("; ").into_iter(),
            )
        })
        .filter_map(|(game_num, cubes)| {
            for cube_set in cubes.into_iter() {
                for c in cube_set.split(", ") {
                    let (count, color) = c.split_once(" ").unwrap();
                    if color == "green" && count.parse::<i32>().unwrap() > max_green {
                        return None;
                    } else if color == "red" && count.parse::<i32>().unwrap() > max_red {
                        return None;
                    } else if color == "blue" && count.parse::<i32>().unwrap() > max_blue {
                        return None;
                    }
                }
            }
            Some(game_num.parse::<i32>().unwrap())
        })
        .sum()
}

pub fn part2(input: &str) -> i32 {
    input
        .lines()
        .map(|line| {
            let (_game, record) = line.split_once(": ").unwrap();
            record.split("; ").into_iter()
        })
        .map(|cubes| {
            let mut min_green = 0;
            let mut min_blue = 0;
            let mut min_red = 0;
            for cube_set in cubes.into_iter() {
                for c in cube_set.split(", ") {
                    let (count, color) = c.split_once(" ").unwrap();
                    let count_num = count.parse::<i32>().unwrap();
                    if color == "green" && count_num > min_green {
                        min_green = count_num;
                    } else if color == "red" && count_num > min_red {
                        min_red = count_num;
                    } else if color == "blue" && count_num > min_blue {
                        min_blue = count_num;
                    }
                }
            }
            min_blue * min_green * min_red
        })
        .sum()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
