import java.util.ArrayList;

public class Piece {
	
	private String name;
	private int col;
	private int row;
	private String team;
	private boolean firstMove;
	protected boolean empty;
	public Piece(String name, int row, int col, String team){
		this.name = name;
		this.col = col;
		this.row = row;
		if(team.equals("black")){
			this.team = "black";
		}
		else{
			this.team = "white";
		}
		firstMove = true; 
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		return null;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getTeam(){
		return team;
	}
	
	public int getCol(){
		return col;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int y){
		row = y;
	}
	
	public void setCol(int x){
		col = x;
	}
	
	public void changeFirstMove(){
		firstMove = false;
	}
	
	public boolean getFirstMove(){
		return firstMove;
	}
	
	public boolean validMove(Cell pos){
		if(!pos.isEmpty() && pos.getPiece().getTeam().equals(getTeam())){
			return false;
		}
		else{
			return true;
		}
	}

    private boolean canMove() {
   	 // TODO Auto-generated method stub
   	 return empty;
    }
	public boolean isCapture(Cell pos){
		if(pos.isEmpty()){
			return false;
		}
		if(pos.getPiece().getTeam().equals(getTeam()) == false){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isPawn(){
		return false;
	}
	
	public boolean isKing(){
		return false;
	}
	
	public boolean isQueen(){
		return false;
	}
	
	public boolean isRook(){
		return false;
	}
	
	public boolean isBishop(){
		return false;
	}
	
	public boolean isKnight(){
		return false;
	}
	
	public boolean isInCheck(GameBoard game){
		return false;
	}
}


