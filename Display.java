import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

//Created by: Arjun Miklos
//NOTES: Pieces are represented on the board with letters:
//	- the first letter tells the color (b for black, w for white)
//	- the second letter(s) tell(s) the type of piece (P for pawn, B for bishop, Kn for knight, K for king, Q for queen, R for rook)
//  - The game does not include en passant

public class Display extends JComponent implements MouseListener, MouseMotionListener {

    public static final int ROWS = 8;
    public static final int COLS = 8;
    public GameBoard game;
    private final int X_GRID_OFFSET = 50; // 25 pixels from left
    private final int Y_GRID_OFFSET = 50; // 40 pixels from top
    private final int CELL_WIDTH = 70;
    private final int CELL_HEIGHT = 70;
    private final int DISPLAY_WIDTH;
    private final int DISPLAY_HEIGHT;
    private boolean paintloop = false;
    private Color brown = new Color(139, 69, 19);
    private Color tan = new Color(210, 180, 140);
    private int prevX;
    private int prevY;
    private String turn = "white";
    private int choice;
    private boolean kingInCheck = false;
    private ArrayList<Cell> totalMoves = new ArrayList<Cell>();
    private boolean queenSideCastle = false;
    private boolean kingSideCastle = false;
    private boolean kingClearPath = true;
    private boolean queenClearPath = true;

    public Display(int width, int height) {
   	 DISPLAY_WIDTH = width;
   	 DISPLAY_HEIGHT = height;
   	 game = new GameBoard();
   	 init();
    }

    public void togglePaintLoop() {
   	 paintloop = !paintloop;
    }

    public void setPaintLoop(boolean value) {
   	 paintloop = value;
    }

    public void init() {
   	 setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
   	 // initCells();
   	 addMouseListener(this);
   	 addMouseMotionListener(this);
   	 repaint();
    }

    void drawGrid(Graphics g) {
   	 for (int row = 0; row <= ROWS; row++) {
   		 g.drawLine(X_GRID_OFFSET, Y_GRID_OFFSET + (row * (CELL_HEIGHT + 1)),
   				 X_GRID_OFFSET + COLS * (CELL_WIDTH + 1), Y_GRID_OFFSET + (row * (CELL_HEIGHT + 1)));
   	 }
   	 for (int col = 0; col <= COLS; col++) {
   		 g.drawLine(X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET,
   				 X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET + ROWS * (CELL_HEIGHT + 1));
   	 }
    }

    void drawCells(Graphics g) {
   	 // Have each cell draw itself
   	 for (int row = 0; row < ROWS; row++) {
   		 for (int col = 0; col < COLS; col++) {
   			 // The cell cannot know for certain the offsets nor the height
   			 // and width; it has been set up to know its own position, so
   			 // that need not be passed as an argument to the draw method
   			 game.cell[row][col].draw(X_GRID_OFFSET, Y_GRID_OFFSET, CELL_WIDTH, CELL_HEIGHT, g);
   		 }
   	 }
    }

    // public void initCells() {
    // for (int row = 0; row < ROWS; row++) {
    // for (int col = 0; col < COLS; col++) {
    // game.cell[row][col] = new Cell(row, col);
    // }
    // }
    // }

    public void paintComponent(Graphics g) {
   	 g.setColor(Color.BLACK);
   	 drawGrid(g);
   	 drawCells(g);
   	 g.setColor(Color.BLACK);
   	 g.drawString("A", 75, 35);
   	 g.drawString("B", 145, 35);
   	 g.drawString("C", 215, 35);
   	 g.drawString("D", 285, 35);
   	 g.drawString("E", 355, 35);
   	 g.drawString("F", 425, 35);
   	 g.drawString("G", 495, 35);
   	 g.drawString("H", 565, 35);
   	 g.drawString("A", 75, 645);
   	 g.drawString("B", 145, 645);
   	 g.drawString("C", 215, 645);
   	 g.drawString("D", 285, 645);
   	 g.drawString("E", 355, 645);
   	 g.drawString("F", 425, 645);
   	 g.drawString("G", 495, 645);
   	 g.drawString("H", 565, 645);
   	 g.drawString("1", 30, 75);
   	 g.drawString("2", 30, 145);
   	 g.drawString("3", 30, 215);
   	 g.drawString("4", 30, 285);
   	 g.drawString("5", 30, 355);
   	 g.drawString("6", 30, 425);
   	 g.drawString("7", 30, 495);
   	 g.drawString("8", 30, 565);
   	 g.drawString("1", 635, 75);
   	 g.drawString("2", 635, 150);
   	 g.drawString("3", 635, 225);
   	 g.drawString("4", 635, 300);
   	 g.drawString("5", 635, 375);
   	 g.drawString("6", 635, 450);
   	 g.drawString("7", 635, 525);
   	 g.drawString("8", 635, 600);
	

   	 repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
   	 // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
   	 // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
   	 // TODO Auto-generated method stub

    }

    public void displayNextMoves(int x, int y) {
      	//Checks for possible castling
    	if(game.cell[y][x].getPiece().isKing() == true){
    	if(turn.equals("white") && y == 0 && x == 3){	
      		for(int a = 0; a <= 3; a++){
      			if(a > 0 && a < 3 && game.cell[0][a].isEmpty() == false){
      				kingClearPath = false;
      			}
      			else{
      				Piece temp = game.cell[0][a].getPiece();
      				game.cell[0][a].removePiece();
      				game.cell[0][a].setPiece(new King("k", 0, a, turn));
      				if(game.cell[0][a].getPiece().isInCheck(game) == true){
      					kingClearPath = false;
      				 }
      				game.cell[0][a].removePiece();
      				game.cell[0][a].setPiece(temp);
      			}
      		 }
      		if(kingClearPath == true){
      			if(game.cell[0][3].getPiece().getFirstMove() == true && game.cell[0][0].getPiece().getFirstMove() == true){
      				kingSideCastle = true;
      			}
      		}
      		for(int a = 3; a <= 7; a++){
      			if(a > 3 && a < 7 && game.cell[0][a].isEmpty() == false){
      				queenClearPath = false;
      			}
      			else{
      			Piece temp = game.cell[0][a].getPiece();
      			game.cell[0][a].removePiece();
      			game.cell[0][a].setPiece(new King("k", 0, a, turn));
  				if(game.cell[0][a].getPiece().isInCheck(game) == true){
  					queenClearPath = false;
  				 }
  				game.cell[0][a].removePiece();
  				game.cell[0][a].setPiece(temp);
      			}
  		 }
      		if(queenClearPath == true){
      			if(game.cell[0][3].getPiece().getFirstMove() == true && game.cell[0][7].getPiece().getFirstMove() == true){
      				queenSideCastle = true;
      			}
      		}
      		if(queenSideCastle == true && kingSideCastle == true){
      		 JPanel panel = new JPanel();
			 JPanel box = new JPanel();
			 box.add(panel);
			 JFrame frame = new JFrame();
			 frame.setContentPane(box);
			 frame.setSize(400, 300);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 JPanel message = new JPanel();
			 message.add(new JLabel("Castle?"));
			 String[] options = new String[] { "Queenside", "Kingside", "No" };
			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
			 if(castle == 2){
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
			   	 for (int i = 0; i < pos.size(); i++) {
			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
			 }
			 }
			 else if(castle == 1){
				 Piece oldKing = game.cell[0][3].getPiece();
				 game.cell[0][3].removePiece();
				 game.cell[0][2].setPiece(game.cell[0][0].getPiece());
				 game.cell[0][2].getPiece().setCol(2);
				 game.cell[0][2].getPiece().setRow(0);
				 game.cell[0][2].getPiece().changeFirstMove();
				 game.cell[0][1].setPiece(oldKing);
				 game.cell[0][1].getPiece().setCol(1);
				 game.cell[0][1].getPiece().setRow(0);
				 game.cell[0][1].getPiece().changeFirstMove();
				 game.cell[0][0].removePiece();
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 if (turn.equals("white")) {
			   		 turn = "black";
			   	 } else {
			   		 turn = "white";
			   	 }
			 }
			 else if(castle == 0){
				 Piece oldKing = game.cell[0][3].getPiece();
				 game.cell[0][3].removePiece();
				 game.cell[0][4].setPiece(game.cell[0][7].getPiece());
				 game.cell[0][4].getPiece().setCol(4);
				 game.cell[0][4].getPiece().setRow(0);
				 game.cell[0][4].getPiece().changeFirstMove();
				 game.cell[0][5].setPiece(oldKing);
				 game.cell[0][5].getPiece().setCol(5);
				 game.cell[0][5].getPiece().setRow(0);
				 game.cell[0][5].getPiece().changeFirstMove();
				 game.cell[0][7].removePiece();
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 if (turn.equals("white")) {
			   		 turn = "black";
			   	 } else {
			   		 turn = "white";
			   	 }
			 }
      		}
      		else if(queenSideCastle == true && kingSideCastle == false){
         		 JPanel panel = new JPanel();
    			 JPanel box = new JPanel();
    			 box.add(panel);
    			 JFrame frame = new JFrame();
    			 frame.setContentPane(box);
    			 frame.setSize(400, 300);
    			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			 JPanel message = new JPanel();
    			 message.add(new JLabel("Castle?"));
    			 String[] options = new String[] { "Queenside", "No" };
    			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
    			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
    			 if(castle == 1){
    				 queenSideCastle = false;
    				 kingSideCastle = false;
    				 kingClearPath = true;
    				 queenClearPath = true;
    				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
    			   	 for (int i = 0; i < pos.size(); i++) {
    			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
    			 }
    			 }
    			 else if(castle == 0){
    				 Piece oldKing = game.cell[0][3].getPiece();
    				 game.cell[0][3].removePiece();
    				 game.cell[0][4].setPiece(game.cell[0][7].getPiece());
    				 game.cell[0][4].getPiece().setCol(4);
    				 game.cell[0][4].getPiece().setRow(0);
    				 game.cell[0][4].getPiece().changeFirstMove();
    				 game.cell[0][5].setPiece(oldKing);
    				 game.cell[0][5].getPiece().setCol(5);
    				 game.cell[0][5].getPiece().setRow(0);
    				 game.cell[0][5].getPiece().changeFirstMove();
    				 game.cell[0][7].removePiece();
    				 queenSideCastle = false;
    				 kingSideCastle = false;
    				 kingClearPath = true;
    				 queenClearPath = true;
    				 if (turn.equals("white")) {
    			   		 turn = "black";
    			   	 } else {
    			   		 turn = "white";
    			   	 }
    			 }
        	}
      		else if(queenSideCastle == false && kingSideCastle == true){
         		 JPanel panel = new JPanel();
   			 JPanel box = new JPanel();
   			 box.add(panel);
   			 JFrame frame = new JFrame();
   			 frame.setContentPane(box);
   			 frame.setSize(400, 300);
   			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   			 JPanel message = new JPanel();
   			 message.add(new JLabel("Castle?"));
   			 String[] options = new String[] { "Kingside", "No" };
   			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
   			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
   			 if(castle == 1){
   				 queenSideCastle = false;
   				 kingSideCastle = false;
   				 kingClearPath = true;
   				 queenClearPath = true;
   				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
   			   	 for (int i = 0; i < pos.size(); i++) {
   			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
   			 }
   			 }
   			 else if(castle == 0){
   				 Piece oldKing = game.cell[0][3].getPiece();
   				 game.cell[0][3].removePiece();
   				 game.cell[0][2].setPiece(game.cell[0][0].getPiece());
   				 game.cell[0][2].getPiece().setCol(2);
   				 game.cell[0][2].getPiece().setRow(0);
   				 game.cell[0][2].getPiece().changeFirstMove();
   				 game.cell[0][1].setPiece(oldKing);
   				 game.cell[0][1].getPiece().setCol(1);
   				 game.cell[0][1].getPiece().setRow(0);
   				 game.cell[0][1].getPiece().changeFirstMove();
   				 game.cell[0][0].removePiece();
   				 queenSideCastle = false;
   				 kingSideCastle = false;
   				 kingClearPath = true;
   				 queenClearPath = true;
   				if (turn.equals("white")) {
   		   		 turn = "black";
   		   	 } else {
   		   		 turn = "white";
   		   	 }
   			 }
      		}	
      		else{
      			queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
      			ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
          		for (int i = 0; i < pos.size(); i++) {
          			game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
      		}
      		}
      	 }
    	else if(turn.equals("black") && y == 7 && x == 3){	
      		for(int a = 0; a <= 3; a++){
      			if(a > 0 && a < 3 && game.cell[7][a].isEmpty() == false){
      				kingClearPath = false;
      			}
      			else{
      				Piece temp = game.cell[7][a].getPiece();
      				game.cell[7][a].removePiece();
      				game.cell[7][a].setPiece(new King("k", 7, a, turn));
      				if(game.cell[7][a].getPiece().isInCheck(game) == true){
      					kingClearPath = false;
      				 }
      				game.cell[7][a].removePiece();
      				game.cell[7][a].setPiece(temp);
      			}
      		 }
      		if(kingClearPath == true){
      			if(game.cell[7][3].getPiece().getFirstMove() == true && game.cell[7][0].getPiece().getFirstMove() == true){
      				kingSideCastle = true;
      			}
      		}
      		for(int a = 3; a <= 7; a++){
      			if(a > 3 && a < 7 && game.cell[7][a].isEmpty() == false){
      				queenClearPath = false;
      			}
      			else{
      			Piece temp = game.cell[7][a].getPiece();
      			game.cell[7][a].removePiece();
      			game.cell[7][a].setPiece(new King("k", 7, a, turn));
  				if(game.cell[7][a].getPiece().isInCheck(game) == true){
  					queenClearPath = false;
  				 }
  				game.cell[7][a].removePiece();
  				game.cell[7][a].setPiece(temp);
      			}
  		 }
      		if(queenClearPath == true){
      			if(game.cell[7][3].getPiece().getFirstMove() == true && game.cell[7][7].getPiece().getFirstMove() == true){
      				queenSideCastle = true;
      			}
      		}
      		if(queenSideCastle == true && kingSideCastle == true){
      		 JPanel panel = new JPanel();
			 JPanel box = new JPanel();
			 box.add(panel);
			 JFrame frame = new JFrame();
			 frame.setContentPane(box);
			 frame.setSize(400, 300);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 JPanel message = new JPanel();
			 message.add(new JLabel("Castle?"));
			 String[] options = new String[] { "Queenside", "Kingside", "No" };
			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
			 if(castle == 2){
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
			   	 for (int i = 0; i < pos.size(); i++) {
			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
			 }
			 }
			 else if(castle == 1){
				 Piece oldKing = game.cell[7][3].getPiece();
				 game.cell[7][3].removePiece();
				 game.cell[7][2].setPiece(game.cell[7][0].getPiece());
				 game.cell[7][2].getPiece().setCol(2);
				 game.cell[7][2].getPiece().setRow(7);
				 game.cell[7][2].getPiece().changeFirstMove();
				 game.cell[7][1].setPiece(oldKing);
				 game.cell[7][1].getPiece().setCol(1);
				 game.cell[7][1].getPiece().setRow(7);
				 game.cell[7][1].getPiece().changeFirstMove();
				 game.cell[7][0].removePiece();
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 if (turn.equals("white")) {
			   		 turn = "black";
			   	 } else {
			   		 turn = "white";
			   	 }
			 }
			 else if(castle == 0){
				 Piece oldKing = game.cell[7][3].getPiece();
				 game.cell[7][3].removePiece();
				 game.cell[7][4].setPiece(game.cell[7][7].getPiece());
				 game.cell[7][4].getPiece().setCol(4);
				 game.cell[7][4].getPiece().setRow(7);
				 game.cell[7][4].getPiece().changeFirstMove();
				 game.cell[7][5].setPiece(oldKing);
				 game.cell[7][5].getPiece().setCol(5);
				 game.cell[7][5].getPiece().setRow(7);
				 game.cell[7][5].getPiece().changeFirstMove();
				 game.cell[7][7].removePiece();
				 queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
				 if (turn.equals("white")) {
			   		 turn = "black";
			   	 } else {
			   		 turn = "white";
			   	 }
			 }
      		}
      		else if(queenSideCastle == true && kingSideCastle == false){
         		 JPanel panel = new JPanel();
    			 JPanel box = new JPanel();
    			 box.add(panel);
    			 JFrame frame = new JFrame();
    			 frame.setContentPane(box);
    			 frame.setSize(400, 300);
    			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			 JPanel message = new JPanel();
    			 message.add(new JLabel("Castle?"));
    			 String[] options = new String[] { "Queenside", "No" };
    			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
    			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
    			 if(castle == 1){
    				 queenSideCastle = false;
    				 kingSideCastle = false;
    				 kingClearPath = true;
    				 queenClearPath = true;
    				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
    			   	 for (int i = 0; i < pos.size(); i++) {
    			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
    			 }
    			 }
    			 else if(castle == 0){
    				 Piece oldKing = game.cell[7][3].getPiece();
    				 game.cell[7][3].removePiece();
    				 game.cell[7][4].setPiece(game.cell[7][7].getPiece());
    				 game.cell[7][4].getPiece().setCol(4);
    				 game.cell[7][4].getPiece().setRow(7);
    				 game.cell[7][4].getPiece().changeFirstMove();
    				 game.cell[7][5].setPiece(oldKing);
    				 game.cell[7][5].getPiece().setCol(5);
    				 game.cell[7][5].getPiece().setRow(7);
    				 game.cell[7][5].getPiece().changeFirstMove();
    				 game.cell[7][7].removePiece();
    				 queenSideCastle = false;
    				 kingSideCastle = false;
    				 kingClearPath = true;
    				 queenClearPath = true;
    				 if (turn.equals("white")) {
    			   		 turn = "black";
    			   	 } else {
    			   		 turn = "white";
    			   	 }
    			 }
        	}
      		else if(queenSideCastle == false && kingSideCastle == true){
         		 JPanel panel = new JPanel();
   			 JPanel box = new JPanel();
   			 box.add(panel);
   			 JFrame frame = new JFrame();
   			 frame.setContentPane(box);
   			 frame.setSize(400, 300);
   			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   			 JPanel message = new JPanel();
   			 message.add(new JLabel("Castle?"));
   			 String[] options = new String[] { "Kingside", "No" };
   			 int castle = JOptionPane.showOptionDialog(frame, message, "Castle Possible",
   			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
   			 if(castle == 1){
   				 queenSideCastle = false;
   				 kingSideCastle = false;
   				 kingClearPath = true;
   				 queenClearPath = true;
   				 ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
   			   	 for (int i = 0; i < pos.size(); i++) {
   			   		 game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
   			 }
   			 }
   			 else if(castle == 0){
   				 Piece oldKing = game.cell[7][3].getPiece();
   				 game.cell[7][3].removePiece();
   				 game.cell[7][2].setPiece(game.cell[7][0].getPiece());
   				 game.cell[7][2].getPiece().setCol(2);
   				 game.cell[7][2].getPiece().setRow(7);
   				 game.cell[7][2].getPiece().changeFirstMove();
   				 game.cell[7][1].setPiece(oldKing);
   				 game.cell[7][1].getPiece().setCol(1);
   				 game.cell[7][1].getPiece().setRow(7);
   				 game.cell[7][1].getPiece().changeFirstMove();
   				 game.cell[7][0].removePiece();
   				 queenSideCastle = false;
   				 kingSideCastle = false;
   				 kingClearPath = true;
   				 queenClearPath = true;
   				if (turn.equals("white")) {
   		   		 turn = "black";
   		   	 } else {
   		   		 turn = "white";
   		   	 }
   			 }
      		}	
      		else{
      			queenSideCastle = false;
				 kingSideCastle = false;
				 kingClearPath = true;
				 queenClearPath = true;
      			ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
          		for (int i = 0; i < pos.size(); i++) {
          			game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
      		}
      		}
      	 }
    	else{
    		ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
      		for (int i = 0; i < pos.size(); i++) {
      			game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
      		}
    	}
      	}
      	else{
      		ArrayList<Cell> pos = game.cell[y][x].getPiece().nextMove(game);
      		for (int i = 0; i < pos.size(); i++) {
      			game.cell[pos.get(i).getY()][pos.get(i).getX()].setColor(Color.GREEN);
   	 }
      	}
    }

    public void displayMove(int fx, int fy, int sx, int sy) {
   	 repaint();
   	 Piece temp = game.cell[fy][fx].getPiece();
   	 game.cell[fy][fx].setPiece(null);
   	 game.cell[fy][fx].setColor(game.cell[fy][fx].makeColor());
   	 game.cell[sy][sx].setPiece(temp);
   	 game.cell[sy][sx].setColor(game.cell[sy][sx].makeColor());
   	 for (int row = 0; row < ROWS; row++) {
   		 for (int col = 0; col < COLS; col++) {
   			 game.cell[row][col].setColor(game.cell[row][col].makeColor());
   		 }
   	 }
   	 game.cell[sy][sx].getPiece().setCol(sx);
   	 game.cell[sy][sx].getPiece().setRow(sy);
   	 game.cell[sy][sx].getPiece().changeFirstMove();
   //Checks to see if a player is in check mate or if there is a stalemate
   	 for(int i = 0; i < COLS; i++){
   		 for(int j = 0; j < ROWS; j++){
   			 if(game.cell[j][i].isEmpty() == false && game.cell[j][i].getPiece().isInCheck(game)){
   				 kingInCheck = true;
   			 }
   			 if(game.cell[j][i].isEmpty() == false && game.cell[j][i].getPiece().getTeam() != turn){
   				 totalMoves.addAll(game.cell[j][i].getPiece().nextMove(game));
   			 }
   		 }
   	 }
   	 //Checkmate
   	 if(kingInCheck == true && totalMoves.size() == 0){
   		 	 JPanel panel = new JPanel();
			 JPanel box = new JPanel();
			 box.add(panel);
			 JFrame frame = new JFrame();
			 frame.setContentPane(box);
			 frame.setSize(400, 300);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 JPanel message = new JPanel();
			 message.add(new JLabel("Checkmate, " + turn + " wins!"));
			 String[] options = new String[] { "OK" };
			 int checkmate = JOptionPane.showOptionDialog(frame, message, "Game Over---Checkmate",
			 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			 java.lang.System.exit(checkmate);
   	 }
   	 //Stalemate
   	 else if(kingInCheck == false && totalMoves.size() == 0){
   		 JPanel panel = new JPanel();
		 JPanel box = new JPanel();
		 box.add(panel);
		 JFrame frame = new JFrame();
		 frame.setContentPane(box);
		 frame.setSize(400, 300);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JPanel message = new JPanel();
		 message.add(new JLabel("Stalemate!"));
		 String[] options = new String[] { "OK" };
		 int stalemate = JOptionPane.showOptionDialog(frame, message, "Game Over---Stalemate",
		 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		 java.lang.System.exit(stalemate);
   	 }
   	 else{
   		 kingInCheck = false;
   		 totalMoves.clear();
   	 }
   	 //Checks if a player is in check
   	 for(int i = 0; i < COLS; i++){
   		 for(int j = 0; j < ROWS; j++){
   			 if(game.cell[j][i].isEmpty() == false && game.cell[j][i].getPiece().isInCheck(game) == true){
   				 JPanel panel = new JPanel();
  				 JPanel box = new JPanel();
  				 box.add(panel);
  				 JFrame frame = new JFrame();
  				 frame.setContentPane(box);
  				 frame.setSize(400, 300);
  				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  				 JPanel message = new JPanel();
  				 message.add(new JLabel("Move out of check!"));
  				 String[] options = new String[] { "OK" };
  				 int check = JOptionPane.showOptionDialog(frame, message, "Check!",
  				 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
   			 }
   		 }
   	 }
   	 // Checks to see if a pawn needs to be promoted to a queen
   	 if (game.cell[sy][sx].getY() == 0 && game.cell[sy][sx].getPiece().isPawn()) {
   				 JPanel panel = new JPanel();
   				 JPanel box = new JPanel();
   				 box.add(panel);
   				 JFrame frame = new JFrame();
   				 frame.setContentPane(box);
   				 frame.setSize(400, 300);
   				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   				 JPanel message = new JPanel();
   				 message.add(new JLabel("Promotion!"));
   				 String[] options = new String[] { "queen", "knight", "rook", "bishop" };
   				 int promotion = JOptionPane.showOptionDialog(frame, message, "Choose a piece",
   				 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
   				 choice = promotion;
   		 if (choice == 0){
   			 game.cell[sx][sy].removePiece();

   			 game.cell[sy][sx].setPiece(new Queen("bQ", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
   		 }
   		 else if (choice == 1){
   			 game.cell[sx][sy].removePiece();

   			 game.cell[sy][sx].setPiece(new Knight("bKn", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
   			 
   		 }
   		 else if (choice == 2){
   			 game.cell[sx][sy].removePiece();

   			 game.cell[sy][sx].setPiece(new Rook("bR", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
   			 
   		 }
   		 else if (choice == 3){
   			 game.cell[sx][sy].removePiece();

   			 game.cell[sy][sx].setPiece(new Bishop("bB", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
   			 
   		 }
   		 else{
  			 game.cell[sy][sx].setPiece(new Pawn("bP", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
  			 
  		 }
   		 game.cell[sy][sx].getPiece().setCol(sx);
   		 game.cell[sy][sx].getPiece().setRow(sy);
   		 //
   	 }
   	 else if (game.cell[sy][sx].getY() == 7 && game.cell[sy][sx].getPiece().isPawn()) {
   		 
   				 JPanel panel1 = new JPanel();
   				 JPanel box1 = new JPanel();
   				 box1.add(panel1);
   				 JFrame frame1 = new JFrame();
   				 frame1.setContentPane(box1);
   				 frame1.setSize(400, 300);
   				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   				 JPanel message1 = new JPanel();
   				 message1.add(new JLabel("Promotion!"));
   				 String[] options1 = new String[] { "queen", "knight", "rook", "bishop" };
   				 int promotion = JOptionPane.showOptionDialog(frame1, message1, "Choose a piece",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
   				 choice = promotion;
   		 if (choice == 0){
   			 game.cell[sx][sy].removePiece();
   			 game.cell[sy][sx].setPiece(new Queen("wQ", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "white"));
   		 }
   		 else if (choice == 1){
   			 game.cell[sx][sy].removePiece();
   			 game.cell[sy][sx].setPiece(new Knight("wKn", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "white"));
   			 
   		 }
   		 else if (choice == 2){
   			 game.cell[sx][sy].removePiece();
   			 game.cell[sy][sx].setPiece(new Rook("wR", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "white"));
   			 
   		 }
   		 else if (choice == 3){
   			 game.cell[sx][sy].removePiece();
   			 game.cell[sy][sx].setPiece(new Bishop("wB", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "white"));
   			 
   		 }
   		 else{
  			 game.cell[sy][sx].setPiece(new Pawn("bP", game.cell[sy][sx].getX(), game.cell[sy][sx].getY(), "black"));
   			 
   		 }
   		 game.cell[sy][sx].getPiece().setCol(sx);
   		 game.cell[sy][sx].getPiece().setRow(sy);
   		 //
   	 }
   	 if (turn.equals("white")) {
   		 turn = "black";
   	 } else {
   		 turn = "white";
   	 }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
   	 // TODO Auto-generated method stub
   	 repaint();
   	 int x = arg0.getX();
   	 int y = arg0.getY();
   	 int a = (x - X_GRID_OFFSET) / (CELL_WIDTH + 1);
   	 int b = (y - Y_GRID_OFFSET) / (CELL_HEIGHT + 1);
   	 if (game.cell[b][a].getColor().equals(tan) || game.cell[b][a].getColor().equals(brown)) {
   		 for (int row = 0; row < ROWS; row++) {
   			 for (int col = 0; col < COLS; col++) {
   				 game.cell[row][col].setColor(game.cell[row][col].makeColor());
   			 }
   		 }
   		 try {
   			 if (game.cell[b][a].getPiece().getTeam().equals(turn)) {
   				 prevX = a;
   				 prevY = b;
   				 displayNextMoves(a, b);
   			 }
   		 } catch (Exception e) {
   		 }
   	 }
   	 if (game.cell[b][a].getColor().equals(Color.GREEN)) {
   		 try {
   			 displayMove(prevX, prevY, a, b);
   		 } catch (Exception e) {
   		 }
   	 }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
   	 // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
   	 // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
   	 // TODO Auto-generated method stub

    }

}



