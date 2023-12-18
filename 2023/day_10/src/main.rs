use std::collections::{HashSet, VecDeque};

#[derive(Debug, Copy, Clone, Eq, Hash, PartialEq)]
struct Point {
    x: usize,
    y: usize,
    tile: char,
}

pub fn part1(input: &str) -> String {
    let mut start_point: Point = Point {
        x: 0,
        y: 0,
        tile: 'S',
    };
    let grid = input
        .lines()
        .enumerate()
        .map(|(y, l)| {
            l.chars()
                .enumerate()
                .map(|(x, c)| {
                    if c == 'S' {
                        start_point = Point {
                            x: x,
                            y: y,
                            tile: 'S',
                        }
                    }
                    c
                })
                .collect::<Vec<_>>()
        })
        .collect::<Vec<Vec<_>>>();
    // Get adjoined chars
    let (mut point1, mut point2) = get_connected_path(&grid, start_point);
    let (mut prev_point1, mut prev_point2) = (start_point, start_point);
    let mut farthest_dist = 0;
    loop {
        farthest_dist += 1;
        if point1.x == point2.x && point1.y == point2.y {
            break;
        }
        let new_point1 = get_next_point(prev_point1, point1);
        let new_point2 = get_next_point(prev_point2, point2);
        prev_point1 = point1;
        prev_point2 = point2;
        point1 = get_char(&grid, new_point1.x, new_point1.y);
        point2 = get_char(&grid, new_point2.x, new_point2.y);
    }
    farthest_dist.to_string()
}

fn get_next_point(prev: Point, curr: Point) -> Point {
    if curr.tile == '|' {
        if curr.y > 0 && prev.y != (curr.y - 1) {
            // moving down to up
            return Point {
                x: curr.x,
                y: curr.y - 1,
                tile: '*',
            };
        } else {
            // moving up to down
            return Point {
                x: curr.x,
                y: curr.y + 1,
                tile: '*',
            };
        }
    }
    if curr.tile == '-' {
        if curr.x > 0 && prev.x != (curr.x - 1) {
            // moving right to left
            return Point {
                x: curr.x - 1,
                y: curr.y,
                tile: '*',
            };
        } else {
            // moving left to right
            return Point {
                x: curr.x + 1,
                y: curr.y,
                tile: '*',
            };
        }
    }
    if curr.tile == 'L' {
        if prev.x == (curr.x + 1) {
            // moving right to up
            return Point {
                x: curr.x,
                y: curr.y - 1,
                tile: '*',
            };
        } else {
            // moving up to right
            return Point {
                x: curr.x + 1,
                y: curr.y,
                tile: '*',
            };
        }
    }
    if curr.tile == 'J' {
        if curr.x > 0 && prev.x != (curr.x - 1) {
            // moving up to left
            return Point {
                x: curr.x - 1,
                y: curr.y,
                tile: '*',
            };
        } else {
            // moving left to up
            return Point {
                x: curr.x,
                y: curr.y - 1,
                tile: '*',
            };
        }
    }
    if curr.tile == '7' {
        if prev.y != (curr.y + 1) {
            // moving left to down
            return Point {
                x: curr.x,
                y: curr.y + 1,
                tile: '*',
            };
        } else {
            // moving down to left
            return Point {
                x: curr.x - 1,
                y: curr.y,
                tile: '*',
            };
        }
    }
    if curr.tile == 'F' {
        if prev.x == (curr.x + 1) {
            // moving right to down
            return Point {
                x: curr.x,
                y: curr.y + 1,
                tile: '*',
            };
        } else {
            // moving down to right
            return Point {
                x: curr.x + 1,
                y: curr.y,
                tile: '*',
            };
        }
    }
    panic!("Invalid tile {:?} {:?}", prev, curr);
}

fn get_connected_path(grid: &Vec<Vec<char>>, point: Point) -> (Point, Point) {
    let mut path: Vec<Point> = Vec::new();
    if point.x > 0 {
        let left_char = get_char(&grid, point.x - 1, point.y);
        if left_char.tile == '-' || left_char.tile == 'L' || left_char.tile == 'F' {
            path.push(left_char);
        }
    }
    if point.y > 0 {
        let up_char = get_char(&grid, point.x, point.y - 1);
        if up_char.tile == '|' || up_char.tile == 'F' || up_char.tile == '7' {
            path.push(up_char);
        }
    }
    let right_char = get_char(&grid, point.x + 1, point.y);
    let down_char = get_char(&grid, point.x, point.y + 1);
    if right_char.tile == '-' || right_char.tile == 'J' || right_char.tile == '7' {
        path.push(right_char);
    }
    if down_char.tile == '|' || down_char.tile == 'L' || down_char.tile == 'J' {
        path.push(down_char);
    }
    // Should only be 2.
    return (path[0], path[1]);
}

fn get_char(grid: &Vec<Vec<char>>, x: usize, y: usize) -> Point {
    Point {
        x: x,
        y: y,
        tile: *grid.get(y).unwrap().get(x).unwrap(),
    }
}

pub fn part2(input: &str) -> String {
    let mut start_point: Point = Point {
        x: 0,
        y: 0,
        tile: 'S',
    };
    let mut grid = input
        .lines()
        .enumerate()
        .map(|(y, l)| {
            l.chars()
                .enumerate()
                .map(|(x, c)| {
                    if c == 'S' {
                        start_point = Point {
                            x: x,
                            y: y,
                            tile: 'S',
                        }
                    }
                    c
                })
                .collect::<Vec<_>>()
        })
        .collect::<Vec<Vec<_>>>();
    // Get adjoined chars
    let (mut point1, mut point2) = get_connected_path(&grid, start_point);
    // Update start point tile to correct type that isn't 'S'
    if point1.y == point2.y {
        start_point.tile = '-';
    } else if point1.x == point2.x {
        start_point.tile = '|';
    } else if (point1.x < start_point.x && point2.y > start_point.y)
        || (point2.x < start_point.x && point1.y > start_point.y)
    {
        start_point.tile = '7';
    } else if (point1.x > start_point.x && point2.y > start_point.y)
        || (point2.x > start_point.x && point1.y > start_point.y)
    {
        start_point.tile = 'F';
    } else if (point1.x > start_point.x && point2.y < start_point.y)
        || (point2.x > start_point.x && point1.y < start_point.y)
    {
        start_point.tile = 'L';
    } else if (point1.x < start_point.x && point2.y < start_point.y)
        || (point2.x < start_point.x && point1.y < start_point.y)
    {
        start_point.tile = 'J';
    }
    *grid
        .get_mut(start_point.y)
        .unwrap()
        .get_mut(start_point.x)
        .unwrap() = start_point.tile;
    let (mut prev_point1, mut prev_point2) = (start_point, start_point);
    let mut loop_path: HashSet<Point> = HashSet::new();
    loop_path.insert(start_point);
    loop {
        loop_path.insert(point1);
        loop_path.insert(point2);
        if point1.x == point2.x && point1.y == point2.y {
            break;
        }
        let new_point1 = get_next_point(prev_point1, point1);
        let new_point2 = get_next_point(prev_point2, point2);
        prev_point1 = point1;
        prev_point2 = point2;
        point1 = get_char(&grid, new_point1.x, new_point1.y);
        point2 = get_char(&grid, new_point2.x, new_point2.y);
    }
    // Expand the grid to allow room for searching
    let expanded_grid: Vec<Vec<char>> = grid
        .iter()
        .enumerate()
        .map(|(y, l)| {
            l.iter()
                .enumerate()
                .map(|(x, c)| {
                    if loop_path.contains(&Point {
                        x: x,
                        y: y,
                        tile: *c,
                    }) {
                        return *c;
                    } else {
                        return '.';
                    }
                })
                .collect::<Vec<_>>()
        })
        .flat_map(|l| {
            [
                l.iter()
                    .flat_map(|c| expand_char(*c, false))
                    .collect::<Vec<_>>(),
                l.iter()
                    .flat_map(|c| expand_char(*c, true))
                    .collect::<Vec<_>>(),
            ]
        })
        .collect();

    let mut visited: HashSet<Point> = HashSet::new();
    // Start at the first '.' we find
    let mut to_visit = VecDeque::new();
    for y in 0..grid.len() {
        to_visit.push_back(get_char(&expanded_grid, 0, y * 2));
        to_visit.push_back(get_char(&expanded_grid, expanded_grid[0].len() - 1, y * 2));
    }
    for x in 0..grid[0].len() {
        to_visit.push_back(get_char(&expanded_grid, x * 2, 0));
        to_visit.push_back(get_char(&expanded_grid, x * 2, expanded_grid.len() - 1));
    }

    while let Some(p) = to_visit.pop_front() {
        if visited.contains(&p) || p.tile != '.' {
            // println!("skipping {:?}", p);
            continue;
        }

        visited.insert(p);

        if p.x > 0 {
            to_visit.push_back(get_char(&expanded_grid, p.x - 1, p.y));
        }
        if p.y > 0 {
            to_visit.push_back(get_char(&expanded_grid, p.x, p.y - 1));
        }
        if p.x < expanded_grid[0].len() - 1 {
            to_visit.push_back(get_char(&expanded_grid, p.x + 1, p.y));
        }
        if p.y < expanded_grid.len() - 1 {
            to_visit.push_back(get_char(&expanded_grid, p.x, p.y + 1));
        }
    }
    let mut num = 0;
    expanded_grid.iter().enumerate().for_each(|(y, l)| {
        l.iter().enumerate().for_each(|(x, c)| {
            if !visited.contains(&Point {
                x: x,
                y: y,
                tile: *c,
            }) && *c == '.'
                && !(x % 2 != 0 || y % 2 != 0)
            {
                num += 1;
            }
        })
    });

    num.to_string()
}

// Expand char into 2x2. Second row is the new row below the original
fn expand_char(c: char, second_row: bool) -> [char; 2] {
    if c == 'F' {
        if second_row {
            return ['|', '.'];
        } else {
            return ['F', '-'];
        }
    } else if c == 'J' {
        if second_row {
            return ['.', '.'];
        } else {
            return ['J', '.'];
        }
    } else if c == 'L' {
        if second_row {
            return ['.', '.'];
        } else {
            return ['L', '-'];
        }
    } else if c == '7' {
        if second_row {
            return ['|', '.'];
        } else {
            return ['7', '.'];
        }
    } else if c == '-' {
        if second_row {
            return ['.', '.'];
        } else {
            return ['-', '-'];
        }
    } else if c == '|' {
        if second_row {
            return ['|', '.'];
        } else {
            return ['|', '.'];
        }
    } else {
        if second_row {
            return ['.', '.'];
        } else {
            return ['.', '.'];
        }
    }
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
