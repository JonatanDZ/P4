# P4
## BoardGameLang
A domain-specific language for prototyping and testing board game logic.

## Features
- Board declaration
- Piece ownership
- Piece placement
- Win/draw conditions
- Rules for placement
- Ast construction
- Interpretation

## Example program
board(3,3);  
player A has 1 piece X;  
gamerules position piece {!occupied(position)};  
win when positions {piece((1,1)) == piece((1,2))};  
place piece X at (3,3);

## Get started
Clean and compile using Maven:
- In the tool window, click the M button to open Maven.
- In Maven, open Lifecycle.
- Click clean.
- When finished running clean, then click compile.

Running Main:
- Open Run/Debug configuration.
- Click Edit configuration.
- Select Main.
- Insert path to 'game.txt'.








