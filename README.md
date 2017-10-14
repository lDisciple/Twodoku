# Twodoku
1st year First Semester Project

This was a program where we needed to create a Competitive Sudoku game based on specifications given to us by our lecturers.
The instructions are included.1

You will need to import the Standard library from introcs.cs.princeton.edu in order to run this application.


#Instructions

Project: Twodoku

In this project you will implement a game called Twodoku, based on Sudoku.  The human player and the computer take turns to fill in an empty grid with numbers 1, 2, 3, 4... as long as they satisfy the Sudoku constraints:

    In each row, each number must appear exactly once.
    In each column, each number must appear exactly once.
    In each subgrid, each number must appear exactly once.

A sudoku grid is (usually) 9x9.  It comprises 3x3 subgrids, each of size 3x3.  In Twodoku, three different grid size are available: 2x2, 3x3, and 4x4.  Here are an example of a 2x2 grid:

The game ends under the following conditions:

    The computer cannot make another move without violating the constraints.  The human wins!
    The human cannot make another move without violating the constraints.  The computer wins!
    The computer makes the last move (fills in the last square).  The computer wins!
    The human makes the last move.  The human wins!

The game starts with the computer making the first move.  Here is a sample game.  The computer's moves are shown in red:

In the last grid you should be able to see that the computer has just played the "2" in the bottom row and that the human cannot make a legal move.  Therefore, the computer wins this game.

Your program must allow the human to play against the computer.  Follow all of the rules above.  You will have to write code that checks that a move is legal.  You will also have to write code that picks a move for the computer.  For a passing grade, you can simply make the computer choose a random legal move.  It does not have to be clever.
Command line

Your program MUST be able to operate from the command line.  It must accept three arguments: the horizontal grid size W, the vertical grid size H, and a mode integer M.  The value of W must be 2, 3, or 4, and the value of H must be equal to W.

If M is 0, your program must read the human's move from the standard input (using, for example, StdIn) in the following format: x y m, where x is the column number (1, 2, ..., H2) and y is the row number (1, 2, ..., W2), and m is the number to fill in (1, 2, 3, ..., WxH).  If WxH is 4x4, then the numbers are 1, 2, 3, 4, ..., 16.  (In the GUI, you may want to use 1, 2, 3, ..,  9, "A", "B", ..., "G" to make it easier to layout out the "digits" on the screen.)  If a move is not legal (because the row or column or the number to fill in is out of bounds), the program must display the message "Illegal move" and terminate immediately.  In this mode, the computer must make its moves (including the first move) either randomly or in some clever way, and the program must prompt the user to enter their move.

If M is 1, your program must read both the human's and the computer's moves from the standard input using the format above.  In other words, there is not really a computer player and all moves are entered from the standard input.

Your program can display the grid in any format that is readable for the user, but – importantly – your program must terminate by printing either "Human wins" or "Computer wins" or "Illegal move".

Here is an example of a program running from the command line:

$ java Twodoku 2 2 0
+---+---++---+---+
| 1 |   ||   |   |
+---+---++---+---+
|   |   ||   |   |
+===+===++===+===+
|   |   ||   |   |
+---+---++---+---+
|   |   ||   |   |
+---+---++---+---+
Enter your move:
> 2 2 3

+---+---++---+---+
| 1 |   ||   |   |
+---+---++---+---+
|   | 3 ||   |   |
+===+===++===+===+
|   |   ||   |   |
+---+---++---+---+
| 4 |   ||   |   |
+---+---++---+---+
Enter your move:
> 4 3 4

+---+---++---+---+
| 1 |   ||   |   |
+---+---++---+---+
|   | 3 ||   |   |
+===+===++===+===+
|   |   || 3 | 4 |
+---+---++---+---+

And so forth until:

Enter your move:
> 2 4 1

+---+---++---+---+
| 1 | 4 ||   | 2 |
+---+---++---+---+
|   | 3 || 1 |   |
+===+===++===+===+
| 2 |   || 3 | 4 |
+---+---++---+---+
| 4 | 1 || 2 |   |
+---+---++---+---+
Computer wins

Here is one more example:

$ java Twodoku 2 2 0
+---+---++---+---+
| 1 |   ||   |   |
+---+---++---+---+
|   |   ||   |   |
+===+===++===+===+
|   |   ||   |   |
+---+---++---+---+
|   |   ||   |   |
+---+---++---+---+
Enter your move:
> 2 2 7
Illegal move

And one more:

$ java Twodoku 3 4 0
Illegal grid size.  Please use 2x2, 3x3, or 4x4.

GUI

The Graphical User Interface (GUI) must have the following components:

    A grid area where the current game is displayed.
    A button area with the following buttons:
        One button for each of the grid sizes
        One button to surrender in the current game
        One button to quit the whole program
        Optionally, buttons for each of the numbers to fill in
    A message area where the game displays information to the user (such as who must play next, who has won the last game, prompting the user to start a game, etc.)

The buttons to select the grid size must not be active once a game has started and the button to surrender the current game must only be active while a game is in progress.

If you prefer, you can use some alternative way for the user to enter numbers in squares when it is their move.  For example, a menu can pop up with potential choices.  (This is much harder and is not required.)  For a good grade it is enough for the user to click on a number button and then on the square where they want to enter their move.  (Or perhaps to click on a square first and then on a number button.)  Whatever method you use, the user must be able to enter exactly one legal move when it is their turn.  The program must prevent users from entering moves when it is not their turn or from entering illegal moves at all.  For example, if a user tries to enter an illegal move, the program may display the message "Illegal move" in the message area and prompt the user to enter a legal move.

The computer's and the human's moves on the grid must displayed clearly so that the user can distinguish the moves from each other.

    It is your own choice whether you want the GUI version of the game to start when the M command-line parameter is 2 (for example, $ java Twoduko 3 3 2 starts the GUI) or whether you want the user to run an entirely different program to start the GUI version ($ java TwodukoGUI).
    You may use any library for the GUI (StdDraw is recommended, but Java Swing is fine).
    There is an example program of how to use the mouse with StdDraw.

Bonus marks

You can earn bonus marks for:

    Adding sounds when the computer/human makes a move
    Adding sounds when the computer/human wins/loses a game
    Adding an intelligent computer player that tries to win the game as soon as possible
    Keeping a score of how many games (in the current session) the human has won and lost
    Allowing alternative grid sizes: 2x3, 2x4, 2x5, 3x4.
