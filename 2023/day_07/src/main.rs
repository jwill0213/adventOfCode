use std::{cmp::Ordering, collections::HashMap};

#[derive(Debug, PartialEq, Eq, Hash, Clone)]
enum HandType {
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard,
}

#[derive(Debug, Clone)]
struct Hand {
    cards: String,
    bid: usize,
    hand_type: HandType,
}

fn parse_hand(hand_entry: &str) -> Hand {
    let (cards, bid) = hand_entry.split_once(" ").unwrap();
    let mut card_map = HashMap::new();
    cards.chars().for_each(|c| {
        *card_map.entry(c).or_insert(0) += 1;
    });

    let parsed_hand: Hand;

    let max_same = *card_map.values().max().unwrap();
    if max_same == 5 {
        parsed_hand = Hand {
            cards: cards.to_string(),
            bid: bid.parse::<usize>().unwrap(),
            hand_type: HandType::FiveOfAKind,
        };
    } else if max_same == 4 {
        parsed_hand = Hand {
            cards: cards.to_string(),
            bid: bid.parse::<usize>().unwrap(),
            hand_type: HandType::FourOfAKind,
        };
    } else if max_same == 3 {
        // If there is 3 of a kind and a pair its a full house
        match card_map.values().into_iter().find(|&x| *x == 2) {
            Some(_) => {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::FullHouse,
                };
            }
            None => {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::ThreeOfAKind,
                };
            }
        }
    } else if max_same == 2 {
        let num_pairs = card_map.into_iter().filter(|(_, v)| *v == 2).count();
        if num_pairs == 2 {
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::TwoPair,
            };
        } else {
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::OnePair,
            };
        }
    } else {
        parsed_hand = Hand {
            cards: cards.to_string(),
            bid: bid.parse::<usize>().unwrap(),
            hand_type: HandType::HighCard,
        };
    }

    parsed_hand
}

fn get_card_value(card: char) -> usize {
    let val;
    if card == 'A' {
        val = 14;
    } else if card == 'K' {
        val = 13;
    } else if card == 'Q' {
        val = 12;
    } else if card == 'J' {
        val = 11;
    } else if card == 'T' {
        val = 10;
    } else {
        val = card.to_digit(10).unwrap();
    }
    val as usize
}

fn sort_hand(a: &Hand, b: &Hand) -> Ordering {
    for i in 0..=4 {
        let a_card: usize = get_card_value(a.cards.chars().nth(i).unwrap());
        let b_card: usize = get_card_value(b.cards.chars().nth(i).unwrap());
        if a_card.cmp(&b_card) == Ordering::Greater {
            return Ordering::Less;
        }
        if a_card.cmp(&b_card) == Ordering::Less {
            return Ordering::Greater;
        }
    }
    Ordering::Equal
}

fn parse_hand_p2(hand_entry: &str) -> Hand {
    let (cards, bid) = hand_entry.split_once(" ").unwrap();
    let mut card_map = HashMap::new();
    cards.chars().for_each(|c| {
        *card_map.entry(c).or_insert(0) += 1;
    });

    let parsed_hand: Hand;

    let max_same = *card_map.values().max().unwrap();
    let num_jokers = card_map.get(&'J').unwrap_or(&0).clone();
    if max_same == 5 {
        parsed_hand = Hand {
            cards: cards.to_string(),
            bid: bid.parse::<usize>().unwrap(),
            hand_type: HandType::FiveOfAKind,
        };
    } else if max_same == 4 {
        if num_jokers == 1 || num_jokers == 4 {
            // If there is 4 of a kind and a joker its a five of a kind
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::FiveOfAKind,
            };
        } else {
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::FourOfAKind,
            };
        }
    } else if max_same == 3 {
        // If there is 3 of a kind and a pair its a full house
        match card_map.values().into_iter().find(|&x| *x == 2) {
            Some(_) => {
                if num_jokers == 3 || num_jokers == 2 {
                    parsed_hand = Hand {
                        cards: cards.to_string(),
                        bid: bid.parse::<usize>().unwrap(),
                        hand_type: HandType::FiveOfAKind,
                    };
                } else {
                    parsed_hand = Hand {
                        cards: cards.to_string(),
                        bid: bid.parse::<usize>().unwrap(),
                        hand_type: HandType::FullHouse,
                    };
                }
            }
            None => {
                if num_jokers == 1 || num_jokers == 3 {
                    parsed_hand = Hand {
                        cards: cards.to_string(),
                        bid: bid.parse::<usize>().unwrap(),
                        hand_type: HandType::FourOfAKind,
                    };
                } else {
                    parsed_hand = Hand {
                        cards: cards.to_string(),
                        bid: bid.parse::<usize>().unwrap(),
                        hand_type: HandType::ThreeOfAKind,
                    };
                }
            }
        }
    } else if max_same == 2 {
        let num_pairs = card_map.into_iter().filter(|(_, v)| *v == 2).count();
        if num_pairs == 2 {
            if num_jokers == 2 {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::FourOfAKind,
                };
            } else if num_jokers == 1 {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::FullHouse,
                };
            } else {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::TwoPair,
                };
            }
        } else {
            if num_jokers == 2 || num_jokers == 1 {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::ThreeOfAKind,
                };
            } else {
                parsed_hand = Hand {
                    cards: cards.to_string(),
                    bid: bid.parse::<usize>().unwrap(),
                    hand_type: HandType::OnePair,
                };
            }
        }
    } else {
        if num_jokers == 1 {
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::OnePair,
            };
        } else {
            parsed_hand = Hand {
                cards: cards.to_string(),
                bid: bid.parse::<usize>().unwrap(),
                hand_type: HandType::HighCard,
            };
        }
    }

    parsed_hand
}

fn get_card_value_p2(card: char) -> usize {
    let val;
    if card == 'A' {
        val = 14;
    } else if card == 'K' {
        val = 13;
    } else if card == 'Q' {
        val = 12;
    } else if card == 'J' {
        val = 1;
    } else if card == 'T' {
        val = 10;
    } else {
        val = card.to_digit(10).unwrap();
    }
    val as usize
}

fn sort_hand_p2(a: &Hand, b: &Hand) -> Ordering {
    for i in 0..=4 {
        let a_card: usize = get_card_value_p2(a.cards.chars().nth(i).unwrap());
        let b_card: usize = get_card_value_p2(b.cards.chars().nth(i).unwrap());
        if a_card.cmp(&b_card) == Ordering::Greater {
            return Ordering::Less;
        }
        if a_card.cmp(&b_card) == Ordering::Less {
            return Ordering::Greater;
        }
    }
    Ordering::Equal
}

pub fn part1(input: &str) -> String {
    let mut hands: HashMap<HandType, Vec<Hand>> = HashMap::new();
    input.lines().for_each(|line| {
        let new_hand = parse_hand(line);
        hands
            .entry(new_hand.hand_type.clone())
            .or_insert(vec![])
            .push(new_hand);
    });
    let mut ranks: Vec<Hand> = Vec::new();
    hands.iter_mut().for_each(|(_, hand_vec)| {
        hand_vec.sort_by(sort_hand);
    });

    ranks.append(hands.entry(HandType::FiveOfAKind).or_default());
    ranks.append(hands.entry(HandType::FourOfAKind).or_default());
    ranks.append(hands.entry(HandType::FullHouse).or_default());
    ranks.append(hands.entry(HandType::ThreeOfAKind).or_default());
    ranks.append(hands.entry(HandType::TwoPair).or_default());
    ranks.append(hands.entry(HandType::OnePair).or_default());
    ranks.append(hands.entry(HandType::HighCard).or_default());

    ranks.reverse();
    ranks
        .iter()
        .enumerate()
        .map(|(i, hand)| (i + 1) * hand.bid)
        .sum::<usize>()
        .to_string()
}

pub fn part2(input: &str) -> String {
    let mut hands: HashMap<HandType, Vec<Hand>> = HashMap::new();
    input.lines().for_each(|line| {
        let new_hand = parse_hand_p2(line);
        hands
            .entry(new_hand.hand_type.clone())
            .or_insert(vec![])
            .push(new_hand);
    });
    let mut ranks: Vec<Hand> = Vec::new();
    hands.iter_mut().for_each(|(_, hand_vec)| {
        hand_vec.sort_by(sort_hand_p2);
    });

    ranks.append(hands.entry(HandType::FiveOfAKind).or_default());
    ranks.append(hands.entry(HandType::FourOfAKind).or_default());
    ranks.append(hands.entry(HandType::FullHouse).or_default());
    ranks.append(hands.entry(HandType::ThreeOfAKind).or_default());
    ranks.append(hands.entry(HandType::TwoPair).or_default());
    ranks.append(hands.entry(HandType::OnePair).or_default());
    ranks.append(hands.entry(HandType::HighCard).or_default());

    ranks.reverse();
    ranks
        .iter()
        .enumerate()
        .map(|(i, hand)| (i + 1) * hand.bid)
        .sum::<usize>()
        .to_string()
}

pub fn main() {
    let input = include_str!("../input.txt");
    println!("Part 1: {}", part1(input));
    println!("Part 2: {}", part2(input));
}
