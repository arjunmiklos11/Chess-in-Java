import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.omg.CORBA.portable.InputStream;

public class Cell {
	private int myX, myY; // x,y position on grid
	private Piece piece;
	private Color color;
	private Color brown = new Color(139,69,19);
	private Color tan = new Color(210,180,140);
	private BufferedImage whitebishop, blackbishop, whitepawn, blackpawn, whiteking, blackking, whitequeen, blackqueen, whiterook, blackrook, blackknight, whiteknight;
   // InputStream input = classLoader.getResourceAsStream("image.jpg");
	public Cell(int row, int col) {
    	myX = col;
    	myY = row;
    	piece = null;
    	color = makeColor();
	}
    
	public Cell(int row, int col, Piece piece){
    	myX = col;
    	myY = row;
    	this.piece = piece;
    	color = makeColor();
    	//loadImages();
	}
//	private void loadImages() {
//
//    	try {
//
//        	whitebishop = ImageIO.read(new File("wbishop.png"));
//        	blackbishop = ImageIO.read(new File("bbishop.png"));
//        	whiterook = ImageIO.read(new File("wrook.png"));
//        	blackrook = ImageIO.read(new File("brook.png"));
//        	whitequeen = ImageIO.read(new File("wqueen.png"));
//        	blackqueen = ImageIO.read(new File("bqueen.png"));
//        	whiteknight = ImageIO.read(new File("wknight.png"));
//        	blackknight = ImageIO.read(new File("bknight.png"));
//        	whiteking = ImageIO.read(new File("wking.png"));
//        	blackking = ImageIO.read(new File("bking.png"));
//        	whitepawn = ImageIO.read(new File("wpawn.png"));
//        	blackpawn = ImageIO.read(new File("bpawn.png"));
//       	 
//       	// pane = ImageIO.read(new File("images\\pane.png"));
//
//    	} catch (IOException ex) {
//
//        	Logger.getLogger(Cell.class.getName()).log(
//                	Level.SEVERE, null, ex);
//    	}
//	}
	public Color makeColor(){
    	Color c = null;
    	if(myY % 2 == 0 && myX % 2 != 0){
        	c = tan;
    	}
    	else if(myY % 2 == 0 && myX % 2 == 0){
        	c = brown;
    	}
    	else if(myY % 2 != 0 && myX % 2 == 0){
        	c = tan;
    	}
    	else if(myY % 2 != 0 && myX % 2 != 0){
        	c = brown;
    	}
    	return c;
	}
    
	public int getX() {
    	return myX;
	}

	public int getY() {
    	return myY;
	}
    
	public void setPiece(Piece piece){
    	this.piece = piece;
	}
	public void removePiece(){
   	 this.piece = null;
	}
	public Piece getPiece(){
    	return piece;
	}
    
	public boolean isValid(){
    	return (myX >= 0 && myX <= 7) && (myY >= 0 && myY <= 7);
	}
    
	public boolean isEmpty(){
    	return (getPiece() == null);
	}
    
	public void setColor(Color color){
    	this.color = color;
	}
    
	public Color getColor(){
    	return color;
	}

	public void draw(int x_offset, int y_offset, int width, int height,
        	Graphics g) {
   	 //Image wall = ImageIO.read(new File("C:/eclipse/projects/Pong/wall.png"));
    	// I leave this understanding to the reader
   	 //Image whitebishop = ImageIO.read(new File("C:/desktop/images/wbishop.png"));
    	int xleft = x_offset + 1 + (myX * (width + 1));
    	int xright = x_offset + width + (myX * (width + 1));
    	int ytop = y_offset + 1 + (myY * (height + 1));
    	int ybottom = y_offset + height + (myY * (height + 1));
    	Color temp = g.getColor();
    	g.setColor(color);
    	g.fillRect(xleft, ytop, width, height);
    	if(piece != null){
   		 for (int i = 0; i<8; i++){
   			 for (int j = 0; j<8; j++){
        	if(piece.getName() != null){
       		 if (piece.getName().equals("wB")){
           		 g.drawImage(whitebishop, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bB")){
           		 g.drawImage(blackbishop, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("wKn")){
           		 g.drawImage(whiteknight, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bKn")){
           		 g.drawImage(blackknight, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 
       		 if (piece.getName().equals("wP")){
           		 g.drawImage(whitepawn, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bP")){
           		 g.drawImage(blackpawn, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("wK")){
           		 g.drawImage(whiteknight, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bK")){
           		 g.drawImage(blackknight, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("wR")){
           		 g.drawImage(whiterook, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bR")){
           		 g.drawImage(blackrook, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("wQ")){
           		 g.drawImage(whitequeen, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (piece.getName().equals("bQ")){
           		 g.drawImage(blackqueen, xleft +  5, ytop + 5, 60, 60, null);
       		 }
       		 if (getColor() == tan){
       			 g.setColor(Color.BLACK);
       		 }
       		 else{
            	g.setColor(Color.WHITE);
       		 }
           	g.drawString(piece.getName(), (xleft + xright)/2 - 5, (ytop + ybottom)/2 + 5);
        	}
   		 }
   		 }
    	}
	}
public boolean isInCheck(GameBoard game) {
   	 // TODO Auto-generated method stub
   	 return false;
    }
}





