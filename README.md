# 2048
code for game 2048
***uses PennDraw features***
How to run?: 
simply compile and type "java Game" to the compiler.
click on the screen and press w,a,s, or d, to move the tiles 

File Descriptions:

Board.java: 
this class holds the methods that  run the game. It draws 
itself and the tiles. controls the movement and merging of the tiles. checks if 
the game is over yet. and generates a random tile whenever a valid move is made. 
it also holds the number of moves the player has made.

Spaces.java:
this class represents the tiles on the board. It has a draw method which specifies 
how, depending on the value, each tile should be drawn. it implements a color 
gradient where each value draws with its own color. It also allows for classes
other than itself (ie. Board.java) to retrive the value of a certain space.

Game.java:
this class actually runs the game. depending on if the user inputs a w, a, s, or d
the main method will move/merge the tiles up, left, down, or right respectively if
possible. it also ensures that a random tile is generated only when a valid move is 
made.
