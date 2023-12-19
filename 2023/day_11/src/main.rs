pub fn part1(input: &str) -> String {
    let lines = input.lines().collect::<Vec<_>>();

    let mut expand_y = Vec::new();
    for y in 0..lines.len() {
        if lines[y].chars().all(|c| c == '.') {
            expand_y.push(y)
        }
    }

    let mut expand_x = Vec::new();
    for x in 0..lines[0].len() {
        if lines.iter().all(|l| (*l).chars().nth(x).unwrap() == '.') {
            expand_x.push(x)
        }
    }

    let galaxies: Vec<(i32, i32)> = lines
        .iter()
        .enumerate()
        .flat_map(|(y, l)| {
            l.chars()
                .enumerate()
                .filter(|(_, c)| *c == '#')
                .map(move |(x, _)| (x, y))
        })
        .map(|(x, y)| {
            let x_coord = x + expand_x.iter().filter(|&&e| e < x).count();
            let y_coord = y + expand_y.iter().filter(|&&e| e < y).count();
            (x_coord as i32, y_coord as i32)
        })
        .collect();

    let mut distances = 0;
    let mut num_pairs = 0;
    for i in 0..galaxies.len() - 1 {
        let g1 = galaxies[i];
        let other_galaxies = &galaxies[(i + 1)..];
        other_galaxies.iter().for_each(|g2| {
            num_pairs += 1;
            distances += (g1.0 - g2.0).abs() + (g1.1 - g2.1).abs();
        });
    }
    distances.to_string()
}

pub fn part2(input: &str) -> String {
    let lines = input.lines().collect::<Vec<_>>();

    let mut expand_y = Vec::new();
    for y in 0..lines.len() {
        if lines[y].chars().all(|c| c == '.') {
            expand_y.push(y as i128)
        }
    }

    let mut expand_x = Vec::new();
    for x in 0..lines[0].len() {
        if lines.iter().all(|l| (*l).chars().nth(x).unwrap() == '.') {
            expand_x.push(x as i128)
        }
    }

    let galaxies: Vec<(i128, i128)> = lines
        .iter()
        .enumerate()
        .flat_map(|(y, l)| {
            l.chars()
                .enumerate()
                .filter(|(_, c)| *c == '#')
                .map(move |(x, _)| (x as i128, y as i128))
        })
        .map(|(x, y)| {
            let x_expansions = expand_x.iter().filter(|&&e| e < x).count() as i128;
            let y_expansions = expand_y.iter().filter(|&&e| e < y).count() as i128;
            let x_coord = x - x_expansions + (x_expansions * 1000000);
            let y_coord = y - y_expansions + (y_expansions * 1000000);
            (x_coord as i128, y_coord as i128)
        })
        .collect();

    let mut distances = 0;
    for i in 0..galaxies.len() - 1 {
        let g1 = galaxies[i];
        let other_galaxies = &galaxies[(i + 1)..];
        other_galaxies.iter().for_each(|g2| {
            distances += (g1.0 - g2.0).abs() + (g1.1 - g2.1).abs();
        });
    }
    distances.to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
