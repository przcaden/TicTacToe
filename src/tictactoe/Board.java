
/*    --------------------- Tic Tac Toe ---------------------

    Written by: Caden Perez
    Project start date: 1/16/2021

    Desc: Utility class for the Tic Tac Toe project. This class manages the state of each section on the game board, simultaneously
    checking if a win condition is met after each turn.

      -------------------------------------------------------     */

package tictactoe;
import java.util.stream.*;

public class Board {
    private String[][] board = new String[3][3];
    
    public Board() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                board[i][j] = ""; // Sets each spot to default (no value)
            }
        }
    }
    
    // Makes the user's turn and switches their value on the board.
    public void makeTurn(int player, int r, int c) {
        if (player == 1) {
             board[r][c] = "X";
        }
        if (player == 2) {
            board[r][c] = "O";
        }
    }
    // Verifies that the user's input is legitimate.
    public boolean checkTurn(int r, int c) {
        return !(board[r][c].equals("X") | board[r][c].equals("O"));
    }
    // Checks every win condition for player 1 and returns true if any are met.
    public boolean checkWinPlayerOne() {
        return (Stream.of(board[0][0], board[1][1], board[2][2]).allMatch("X"::equals) // If all spots on board contain "X", method returns true
                || Stream.of(board[0][2], board[1][1], board[2][0]).allMatch("X"::equals)
                || Stream.of(board[0][0], board[1][0], board[2][0]).allMatch("X"::equals)
                || Stream.of(board[0][1], board[1][1], board[2][1]).allMatch("X"::equals)
                || Stream.of(board[0][2], board[1][2], board[2][2]).allMatch("X"::equals)
                || Stream.of(board[0][0], board[0][1], board[0][2]).allMatch("X"::equals)
                || Stream.of(board[1][0], board[1][1], board[1][2]).allMatch("X"::equals)
                || Stream.of(board[2][0], board[2][1], board[2][2]).allMatch("X"::equals));
    }
    // Checks every win condition for player 2 and returns true if any are met.
    public boolean checkWinPlayerTwo() {
        return (Stream.of(board[0][0], board[1][1], board[2][2]).allMatch("O"::equals)
                || Stream.of(board[0][2], board[1][1], board[2][0]).allMatch("O"::equals)
                || Stream.of(board[0][0], board[1][0], board[2][0]).allMatch("O"::equals)
                || Stream.of(board[0][1], board[1][1], board[2][1]).allMatch("O"::equals)
                || Stream.of(board[0][2], board[1][2], board[2][2]).allMatch("O"::equals)
                || Stream.of(board[0][0], board[0][1], board[0][2]).allMatch("O"::equals)
                || Stream.of(board[1][0], board[1][1], board[1][2]).allMatch("O"::equals)
                || Stream.of(board[2][0], board[2][1], board[2][2]).allMatch("O"::equals));
    }
    // Checks if there is a draw between the players.
    public boolean drawCheck() {
        return (Stream.of(board[0][0], board[0][1], board[0][2],
                board[1][0], board[1][1], board[1][2],
                board[2][0], board[2][1], board[2][2]).noneMatch(""::equals));
    }
}