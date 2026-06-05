# Number Guessing Game

A lightweight desktop number guessing game built in Java Swing. The application generates a random number between 1 and 100, and the player has 7 attempts to guess it.

## Features

- **Warmer/Colder Hints**: The game compares the current guess to the previous one to indicate whether the player is getting closer ("warmer") or further away ("colder") from the target.
- **Proximity Border Glow**: The input text box border color dynamically updates based on guess proximity (orange for warmer, blue for colder, green for correct, red for game over).
- **Guess History Log**: Displays a running list of previous guesses with directional indicators (e.g., `45↑`, `60↓`) to prevent repeating guesses.
- **Attempts Tracker**: Displays remaining attempts both textually and visually using indicator dots (`● ● ● ● ● ● ●`).
- **Leaderboard**: Automatically tracks and saves the best score per player to a local `leaderboard.txt` file, displayed as a sorted ranking.
- **Duplicate Input Handling**: Warns the player if they repeat a guess without consuming an attempt or penalizing their score.

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed and configured on your machine.

### Launch
Compile all source files and run the main entry point:
```bash
javac *.java
java Main
```

## Use Cases

This project is suitable for:
- **Casual Play**: A simple, lightweight game to pass the time and test deductive guessing strategies.
- **Academic Reference**: A template for college Java project showing how to build desktop GUIs, manage user events, and construct multi-frame applications.

## Debugging / Cheat Code
There is a hidden debugging mechanism to reveal the secret number. Players can try interacting with the games, some clicks may lead to revealing the secret number.

## Project Structure

- `Main.java`: Entry point that boots the UI.
- `startscreen.java`: Initial setup screen for player name registration.
- `game.java`: Main game loop, validation logic, visual indicators, and state handling.
- `scores.java`: Utility class for file I/O operations (saving/loading scores).
- `leaderboard.java`: Dialog popup showing the ranked leaderboard list.

## Developer

Developed by [@jigyasaphogat](https://github.com/jigyasaphogat).
