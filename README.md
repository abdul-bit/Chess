# Chess Game in Java

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Project Structure](#project-structure)
6. [Class Diagrams](#class-diagrams)
7. [Benefits of OOP Design](#benefits-of-oop-design)
8. [Contributing](#contributing)
9. [License](#license)
10. [Contact](#contact)

## Introduction
This project is a fully-functional chess game implemented in Java, designed using object-oriented principles. It features a graphical user interface (GUI) that allows players to play chess, view taken pieces, and review the game history. Additionally, it supports loading saved games in PGN format and offers preferences to customize the game board.

## Features
- **GUI Interface:** Displays the chessboard, taken pieces, and move history.
- **Chess Pieces:** All standard chess pieces with their respective movements.
- **Players:** Support for both black and white players with turn-based play.
- **Game History:** Track and display the moves made during the game.
- **Preferences:** Options to flip the board and highlight legal moves.
- **Load Game:** Load previously saved games in PGN format.
- **Extensible Design:** Easily extendable due to the object-oriented architecture.

## Installation
To run this project locally, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/abdul-bit/Chess.git
    ```
2. **Navigate to the project directory:**
    ```sh
    cd ChessGame
    ```
3. **Compile the Java files:**
    ```sh
    javac -d bin src/**/*.java
    ```
4. **Run the application:**
    ```sh
    java -cp bin Chess
    ```

## Usage
1. **Start a new game** by running the `Chess` class.
2. **Move pieces** by clicking on a piece and then clicking on the destination square.
3. **View taken pieces** on the left panel.
4. **View move history** on the right panel.
5. **Load a saved game** by selecting "Load PGN File" from the "File" menu.
6. **Flip the board** or **highlight legal moves** from the "Preferences" menu.
7. **Exit the game** by selecting "Exit" from the "File" menu.

### Example
- **Move:** White plays `e4`, Black responds with `d5`, White captures `exd5`.
- **Load Game:** Load a previously saved game in PGN format to review or continue playing.
![image](https://github.com/abdul-bit/Chess/assets/59999587/a6152423-3bc9-42c7-884f-76c664c74e92)


## Project Overview

- **board:** Contains classes related to the chessboard and moves.
- **gui:** Contains classes for the graphical user interface.
- **pieces:** Contains classes for each chess piece and their behaviors.
- **player:** Contains classes for player-related logic and states.
- **Chess.java:** The main class to start the application.


## Benefits of OOP Design
Using object-oriented programming (OOP) principles provides several advantages:

1. **Modularity:** Each class represents a distinct component, making the code easier to manage and maintain.
2. **Reusability:** Code can be reused across different parts of the application or in other projects.
3. **Extensibility:** New features or classes can be added with minimal changes to existing code.
4. **Maintainability:** Easier to debug and update the application due to the clear separation of concerns.



## Contact
For any inquiries or issues, feel free to contact me:

- **GitHub:** [abdul-bit](https://github.com/abdul-bit)
- **Email:** abdulrahmanshigihalli@gmail.com

