/**
* Name: Lucy Fekade
* Pennkey: lucyfe
* Execution: N/A no main
*
* Description: this class creates the board on which the game is played
**/
public class Board {

    private Spaces[][] grid;
    private int numMoves;

    public Board() {
        //initialize numMoves
        this.numMoves = 0;
        //initialize 2d array of spaces
        grid = new Spaces[4][4];

        //probability variables
        double val1 = Math.random();
        double val2 = Math.random();
        //indeces that will be chosen at random
        int y = 0;
        int x = 0;
        int y2 = 0;
        int x2 = 0;
        //loop to ensure there aren't two tiles on top of each other when starting
        while (x == x2 && y == y2) {
            //random values between 0 and 3 for row and column
            y = (int) (Math.random() * 4);
            x = (int) (Math.random() * 4);
            y2 = (int) (Math.random() * 4);
            x2 = (int) (Math.random() * 4);
        }
        /**
         * creating two spaces that hold values of either 2, 4 depending on
         * probablity variables above
         **/
        if (val1 >= 0.5 && val2 >= 0.5) {
                grid[y][x] = new Spaces(x, y, 2);
                grid[y2][x2] = new Spaces(x2, y2, 2);
        } else if (val1 >= 0.5 && val2 < 0.5) {
                grid[y][x] = new Spaces(x, y, 2);
                grid[y2][x2] = new Spaces(x2, y2, 4);
        } else if (val1 < 0.5 && val2 < 0.5) {
                grid[y][x] = new Spaces(x, y, 4);
                grid[y2][x2] = new Spaces(x2, y2, 4);
        } else if (val1 < 0.5 && val2 >= 0.5) {
                grid[y][x] = new Spaces(x, y, 4);
                grid[y2][x2] = new Spaces(x2, y2, 2);
        }

        //traverse 2d array to fill all other null Spaces with a value of 0
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == null) {
                    grid[row][col] = new Spaces(col, row, 0);
                }
            }
        }

    }

    //draws array
    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method draws the board and the tiles that go on it.
    */
    public void drawBoard() {
        PennDraw.setYscale(1, 0);
        //draw background
        PennDraw.setPenColor(219, 217, 202);
        PennDraw.filledSquare(0.5, 0.5, 0.5);
        //draw grid
        PennDraw.setPenColor(181, 178, 152);
        PennDraw.setPenRadius(0.027);
        PennDraw.line(0.0, 0.0, 0.0, 1.0);
        PennDraw.line(0.0, 0.0, 1.0, 0.0);
        PennDraw.line(0.0, 1.0, 1.0, 1.0);
        PennDraw.line(1.0, 0.0, 1.0, 1.0);

        PennDraw.line(0.25, 0.0, 0.25, 1.0);
        PennDraw.line(0.5, 0.0, 0.5, 1.0);
        PennDraw.line(0.75, 0.0, 0.75, 1.0);
        PennDraw.line(0.0, 0.25, 1.0, 0.25);
        PennDraw.line(0.0, 0.5, 1.0, 0.5);
        PennDraw.line(0.0, 0.75, 1.0, 0.75);
        //draw tiles (or spaces)
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col].draw();
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: N/A void
    * Description: if a merge between tiles is possible when moving up,
    * this method will find the possible merge and combine the tiles adjacent
    * to each other with the same value
    */
    public void mergeUp() {
        //move up first if possible
        moveUp();
        //traverse 2d array of spaces
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                //ensure no ArrayIndexOutOfBoundsException
                if (row + 1 < grid[0].length) {
                    //if current index has value equal to index below, merge
                    if (grid[row][col].getValue() ==
                          grid[row + 1][col].getValue()) {
                        //update value to be double its original value
                        grid[row][col] = new Spaces(col, row,
                                                 grid[row][col].getValue() * 2);
                        //set value of row below to be 0
                        grid[row + 1][col] = new Spaces(col, row + 1, 0);
                        moveUp();
                    }
                }
            }
        }
        //increment number of moves
        numMoves++;
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: if a tile can move up, this method will look for how much
    * the tile should move up and move it up
    */
    private void moveUp() {
        //traverse 2d array column wise
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                //check if move up once is possible
                if (row - 1 >= 0) {
                    /**
                     * look for first value in column that is not a 0
                     * and check if tile above is empty to move
                     **/
                    if (grid[row][col].getValue() != 0 &&
                       grid[row - 1][col].getValue() == 0) {
                        //if so, change value of empty tile to that of the non-empty
                        grid[row - 1][col] = new Spaces(col, row - 1,
                                                    grid[row][col].getValue());
                        //then change value of that tile to 0
                        grid[row][col] = new Spaces(col, row, 0);
                        /**
                         * if more than one move up is possible, repeat same
                         * steps as above
                         **/
                        if (row - 2 >= 0) {
                            if (grid[row - 2][col].getValue() == 0) {
                                grid[row - 2][col] = new Spaces(col,
                                        row - 2, grid[row - 1][col].getValue());
                                grid[row - 1][col] = new Spaces(col, row - 1, 0);
                            }
                        }
                        if (row - 3 >= 0) {
                            if (grid[row - 3][col].getValue() == 0) {
                                grid[row - 3][col] = new Spaces(col, row - 3,
                                                    grid[row - 2][col].getValue());
                                grid[row - 2][col] = new Spaces(col, row - 2, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method checks to see if a merge down is possible. if so, it
    * will complete the merge
    */
    public void mergeDown() {
        //move down all columns if possible
        moveDown();
        //traverse 2d array column wise
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 1; row >= 0; row--) {
                if (row - 1 >= 0) {
                    //if current index has a value equal to the row above - merge
                    if (grid[row][col].getValue() ==
                          grid[row - 1][col].getValue()) {
                        //update value to be double its original value
                        grid[row][col] = new Spaces(col, row,
                                                    grid[row][col].getValue() * 2);
                        //set value of row above to be 0
                        grid[row - 1][col] = new Spaces(col, row - 1, 0);
                        moveDown();
                    }
                }
            }
        }
        //increment numMoves
        numMoves++;
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: if a tile can move down, this method will look for how much
    * the tile should move down by and move it down by that many spaces
    */
    private void moveDown() {
        //traverse 2d array column wise (start from last row)
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 1; row >= 0; row--) {
                //check if move down once is possible
                if (row + 1 < grid.length) {
                    /**
                     * look for first value in column that is not a 0
                     * and check if tile above is empty to move
                     **/
                    if (grid[row][col].getValue() != 0 &&
                       grid[row + 1][col].getValue() == 0) {
                        //if so, change value of empty tile to that of the non-empty
                        grid[row + 1][col] = new Spaces(col, row + 1,
                                                        grid[row][col].getValue());
                        //then change value of that tile to 0
                        grid[row][col] = new Spaces(col, row, 0);
                        /**
                         * if more than one move down is possible, repeat same
                         * steps as above
                         **/
                        if (row + 2 < grid.length) {
                            if (grid[row + 2][col].getValue() == 0) {
                                grid[row + 2][col] = new Spaces(col, row + 2,
                                                    grid[row + 1][col].getValue());
                                grid[row + 1][col] = new Spaces(col, row + 1, 0);
                            }
                        }
                        if (row + 3 < grid.length) {
                            if (grid[row + 3][col].getValue() == 0) {
                                grid[row + 3][col] = new Spaces(col, row + 3,
                                                    grid[row + 2][col].getValue());
                                grid[row + 2][col] = new Spaces(col, row + 2, 0);
                            }
                        }
                    }

                }
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method checks to see if a merge left is possible. if so, it
    * will complete the merge
    */
    public void mergeLeft() {
        //move all values to the left if possible
        moveLeft();
         //traverse 2d array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
              //ensure no ArrayIndexOutOfBoundsException
                if (col + 1 < grid[row].length) {
                    /**
                     * if current index has a value equal to the column
                     * to the right - merge
                     **/
                    if (grid[row][col].getValue() ==
                         grid[row][col + 1].getValue()) {
                        //update value to be double its original value
                        grid[row][col] = new Spaces(col, row,
                                                    grid[row][col].getValue() * 2);
                        //set value of column at right to be 0
                        grid[row][col + 1] = new Spaces(col + 1, row, 0);
                        moveLeft();
                    }
                }
            }
        }
        //increment numMoves
        numMoves++;
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: if a tile can move left, this method will look for how much
    * the tile should move left by and move it left by that many spaces
    */
    private void moveLeft() {
        //traverse 2d array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                //check if move left once is possible
                if (col - 1 >= 0) {
                    /**
                     * look for first value that is not a 0
                     * and check if tile at left is empty to move
                     **/
                    if (grid[row][col].getValue() != 0 &&
                       grid[row][col - 1].getValue() == 0) {
                        //if so, change value of empty tile to that of the non-empty
                        grid[row][col - 1] = new Spaces(col - 1, row,
                                                        grid[row][col].getValue());
                        //then change value of that tile to 0
                        grid[row][col] = new Spaces(col, row, 0);
                        /**
                         * if more than one move left is possible, repeat same
                         * steps as above
                         **/
                        if (col - 2 >= 0) {
                            if (grid[row][col - 2].getValue() == 0) {
                                grid[row][col - 2] = new Spaces(col - 2, row,
                                                    grid[row][col - 1].getValue());
                                grid[row][col - 1] = new Spaces(col - 1, row, 0);
                            }
                        }
                        if (col - 3 >= 0) {
                            if (grid[row][col - 3].getValue() == 0) {
                                grid[row][col - 3] = new Spaces(col - 3, row,
                                                      grid[row][col - 2].getValue());
                                grid[row][col - 2] = new Spaces(col - 2, row, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method checks to see if a merge right is possible. if so, it
    * will complete the merge
    */
    public void mergeRight() {
        //move all values to the right if possible
        moveRight();
        //traverse 2d array
        for (int row = 0; row < grid.length; row++) {
            for (int col = grid[row].length - 1; col >= 0; col--) {
                //ensure no ArrayIndexOutOfBoundsException
                if (col - 1 >= 0) {
                    /**
                     * if current index has a value equal to the column
                     * to the right - merge
                     **/
                    if (grid[row][col].getValue() ==
                                               grid[row][col - 1].getValue()) {
                        //update value to be double its original value
                        grid[row][col] = new Spaces(col, row,
                                                    grid[row][col].getValue() * 2);
                        //set value of index at column at left to be 0
                        grid[row][col - 1] = new Spaces(col - 1, row, 0);
                        moveRight();
                    }
                }
            }
        }
        //increment numMoves
        numMoves++;
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: if a tile can move right, this method will look for how much
    * the tile should move right by and move it right by that many spaces
    */
    private void moveRight() {
        //traverse 2d array  (start at last column)
        for (int row = 0; row < grid.length; row++) {
            for (int col = grid[row].length - 1; col >= 0; col--) {
                //check if move right once is possible
                if (col + 1 < grid[row].length) {
                    /**
                     * look for first value that is not a 0
                     * and check if tile at right is empty to move the current index
                     **/
                    if (grid[row][col].getValue() != 0 &&
                       grid[row][col + 1].getValue() == 0) {
                         //if so, change value of empty tile to that of the non-empty
                        grid[row][col + 1] = new Spaces(col + 1, row,
                                                        grid[row][col].getValue());
                        //then change value of that tile to 0
                        grid[row][col] = new Spaces(col, row, 0);
                        /**
                         * if more than one move right is possible, repeat same
                         * steps as above
                         **/
                        if (col + 2 < grid[row].length) {
                            if (grid[row][col + 2].getValue() == 0) {
                                grid[row][col + 2] = new Spaces(col + 2, row,
                                                      grid[row][col + 1].getValue());
                                grid[row][col + 1] = new Spaces(col + 1, row, 0);
                            }
                        }
                        if (col + 3 < grid[row].length) {
                            if (grid[row][col + 3].getValue() == 0) {
                                grid[row][col + 3] = new Spaces(col + 3, row,
                                                      grid[row][col + 2].getValue());
                                grid[row][col + 2] = new Spaces(col + 2, row, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    public Spaces[][] getBoard() {
        Spaces[][] grid2 = new Spaces[4][4];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid2[row][col] = grid[row][col];
            }
        }

        return grid2;
    }

    /**
    * Inputs: N/A
    * Outputs: boolean (true or false)
    * Description: this method returns whether or not the player has either won or
    * lost the game. (if the game is over or not)
    */
    public boolean isGameOver() {
        //check if the player has won the game
        if (gameWon() == true) {
            //draw message that the player has won
            PennDraw.text(0.5, 0.5, "Game Won!");
            //show number of moves
            PennDraw.text(0.5, 0.7, "Moves: " + getNumMoves());
            return true;
        }
        //traverse 2d array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                //if any spaces are empty, tha game isn't over
                if (grid[row][col].getValue() == 0) {
                    return false;
                }
                if (grid[row][col].getValue() > 0) {
                    //if not in 3rd row and not in 3rd col
                    if ((row >= 0 && row <= 2) && (col >= 0 && col <= 2)) {
                        //check if value to right or below have same value
                        if ((grid[row][col].getValue() ==
                                  grid[row][col + 1].getValue()) ||
                                  (grid[row][col].getValue() ==
                                  grid[row + 1][col].getValue())) {
                            //if so, moves are still possible, so game is not over
                            return false;
                        }
                    //if in 3rd row and 3rd col
                    } else if (row == 3 && (col >= 0 && col <= 2)) {
                        //check if spaces to the right has the same value
                        if (grid[row][col].getValue() ==
                           grid[row][col + 1].getValue()) {
                            //if so, moves are still possible, so game is not over
                            return false;
                        }
                    //if in 3rd colum and not 3rd row
                    } else if ((row >= 0 && row <= 2) && col == 3) {
                        //check if value below has same value
                        if (grid[row][col].getValue() ==
                           grid[row + 1][col].getValue()) {
                            //if so, moves are still possible, so game is not over
                            return false;
                        }
                    }
                }
            }
        }
        //if none above are true, the game is over
        PennDraw.setFontSize(50);
        PennDraw.text(0.5, 0.5, "Game Over!");
        PennDraw.text(0.5, 0.7, "Moves: " + getNumMoves());
        return true;
    }

    /**
    * Inputs: N/A
    * Outputs: boolean (true or false)
    * Description: this method tests to see if the player has won the game
    */
    private boolean gameWon() {
        //traverse 2d array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                //check if any tiles on board have a value of 2048
                if (grid[row][col].getValue() == 2048) {
                    return true;
                }
            }
        }
        //if not - game not won yet
        return false;
    }

    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method generates a random tile at a random empty position
    * on the board
    */
    public void generateRandomTile() {
        //positions
        int a = 0;
        int b = 0;
        //boolean for if position holds a value if zero
        boolean isZero = false;
        //loop until boolean is true
        while (!isZero) {
            //assign random value between 0 and 3 to position variables
            a = (int) (Math.random() * 4);
            b = (int) (Math.random() * 4);
            //check if position's value is 0
            if (grid[a][b].getValue() == 0) {
                //if so, boolean is true
                isZero = true;
            }
        }
        //probablity variable
        double val = Math.random();
        //equal chances of value at random position is either a 2 or 4
        if (val >= 0.5) {
            grid[a][b] = new Spaces(b, a, 2);
        } else {
            grid[a][b] = new Spaces(b, a, 4);
        }
    }

    /**
    * Inputs: N/A
    * Outputs: integer value
    * Description: returns the number of moves the player made while playing
    */
    public int getNumMoves() {
        return this.numMoves;
    }

}
