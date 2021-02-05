
/*    --------------------- Tic Tac Toe ---------------------

    Written by: Caden Perez
    Project start date: 1/16/2021

    Desc: Graphical user interface class for the Tic Tac Toe project. Utilizes Java's Swing framework for the GUI.
    This class will manage the turn of the players and modifies itself after each position on the board is clicked.

      -------------------------------------------------------     */

package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    Board board;
    JFrame fr;
    int turn = 1;
    // Declare panels, labels, and buttons.
    JPanel contentPanel = new JPanel(); // Main panel, holds contents of the board
    JPanel buttonPanel = new JPanel(); // Holds all 9 buttons in a panel
    JButton[][] buttons = new JButton[3][3]; // 2D array of buttons
    JButton rematch; // Button for rematches. Activated when the game ends
    JLabel toplabel; // Displays instructions for the game and announces which player wins
    
    // Set image objects for future reference.
    ImageIcon p1_img = new ImageIcon("player1.png");
    ImageIcon p2_img = new ImageIcon("player2.png");
    ImageIcon ttticon = new ImageIcon("ttt.png");
    
    public GUI() { // Constructor for GUI
        board = new Board();
        
        fr = new JFrame("Tic Tac Toe!");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates when window is closed
        //fr.setSize(522, 522); // Set size parameters for frame
        fr.setIconImage(ttticon.getImage());
        
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = GridBagConstraints.BOTH;
        
        layout.gridx = 1;
        layout.gridy = 0;
        toplabel = new JLabel("Click a button to select your spot. Player 1 = X, Player 2 = O");
        contentPanel.add(toplabel);
        
        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5)); // constructor: (rows, columns, hgap, vgap)
        //buttonPanel.setPreferredSize(new Dimension(394, 394));
        
        // Creates each button on the grid.
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this); // Add click function to the button
                buttons[i][j].setPreferredSize(new Dimension(128, 128));
                buttonPanel.add(buttons[i][j]);
            }
        }
        
        layout.gridx = 1;
        layout.gridy = 1;
        layout.fill = GridBagConstraints.BOTH;
        contentPanel.add(buttonPanel);
        rematch = new JButton("Rematch?");
        rematch.setPreferredSize(new Dimension(100, 40));
        rematch.addActionListener(this);
        rematch.setEnabled(false);
        
        layout.gridx = 1;
        layout.gridy = 2;
        layout.fill = GridBagConstraints.BOTH;
        contentPanel.add(rematch);
        
        fr.add(contentPanel);
        fr.setContentPane(contentPanel); // Sets main content panel
        fr.pack(); // Sizes the window to match the panels
        fr.setVisible(true); // Makes frame visible
    }
    
    // Click function for each button. Checks which button was the source of the click.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rematch)
            rematchClick();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (e.getSource() == buttons[i][j])
                    click(i, j); // Runs the click method based on the button clicked.
            }
        }
    }
    // Method that occurs when a button on the board is clicked.
    public void click(int r, int c) {
        if (board.checkTurn(r, c)) {
            if (turn % 2 == 1) {
                buttons[r][c].setIcon(p1_img);
                buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                board.makeTurn(1, r, c);
            }
            else {
                buttons[r][c].setIcon(p2_img);
                buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                board.makeTurn(2, r, c);
            }
            buttons[r][c].setEnabled(false);
            turn++; // Increment the game's turn counter after turn is effectively done
        }
        checkWin();
    }
    
    public void checkWin() {
        // Changes top text label if a win condition is met
        if (board.checkWinPlayerOne())
            toplabel.setText("Player 1 wins!");
        else if (board.checkWinPlayerTwo())
            toplabel.setText("Player 2 wins!");
        else if (board.drawCheck()) {
            toplabel.setText("It's a draw!");
        }
        
        if (board.checkWinPlayerOne() || board.checkWinPlayerTwo() || board.drawCheck()) {
            for (int r=0; r<3; r++) {
                for (int c=0; c<3; c++) {
                    buttons[r][c].setEnabled(false);
                }
            }
            rematch.setEnabled(true);
        }
    }
    // If the rematch button is clicked, then all values for the game board are reset.
    public void rematchClick() {
        board = new Board();
        turn = 1;
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                buttons[r][c].setEnabled(true);
                buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                buttons[r][c].setIcon(null);
            }
        }
        toplabel.setText("Click a button to select your spot. Player 1 = X, Player 2 = O");
        rematch.setEnabled(false);
    }
}