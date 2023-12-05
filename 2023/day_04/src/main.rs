pub fn part1(input: &str) -> String {
    input
        .lines()
        .map(|line| line.split_once(":").unwrap().1)
        .map(|card| card.split_once("|").unwrap())
        .map(|(winning_nums, my_card)| {
            let win = winning_nums
                .trim()
                .split(" ")
                .filter_map(|n| n.parse::<u32>().ok())
                .collect::<Vec<_>>();
            let mine = my_card
                .trim()
                .split(" ")
                .filter_map(|n| n.parse::<u32>().ok())
                .collect::<Vec<_>>();
            (win, mine)
        })
        .map(|(winning_nums, my_nums)| my_nums.iter().filter(|n| winning_nums.contains(n)).count())
        .filter(|count| count > &0)
        .map(|val| i32::from(2).pow(val as u32 - 1))
        .sum::<i32>()
        .to_string()
}

pub fn part2(input: &str) -> String {
    // Create vector of count for each card
    let win_counts: Vec<_> = input
        .lines()
        .map(|line| line.split_once(":").unwrap().1)
        .map(|card| card.split_once("|").unwrap())
        .map(|(winning_nums, my_card)| {
            let win = winning_nums
                .trim()
                .split(" ")
                .filter_map(|n| n.parse::<u32>().ok())
                .collect::<Vec<_>>();
            let mine = my_card
                .trim()
                .split(" ")
                .filter_map(|n| n.parse::<u32>().ok())
                .collect::<Vec<_>>();
            mine.iter().filter(|n| win.contains(n)).count()
        })
        .collect();

    let mut card_count: Vec<usize> = (0..win_counts.len()).map(|_| 1).collect();

    win_counts.iter().enumerate().for_each(|(i, win_count)| {
        for w in 1..*win_count + 1 {
            card_count[i + w] += card_count[i];
        }
    });

    card_count.iter().sum::<usize>().to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
