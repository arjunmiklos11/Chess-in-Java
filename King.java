import java.util.ArrayList;

public class King extends Piece{

	public King(String name, int row, int col, String team){
		super(name, row, col, team);
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		ArrayList<Cell> pos = new ArrayList<Cell>();
		Piece temp = game.cell[getRow()][getCol()].getPiece();
		game.cell[getRow()][getCol()].removePiece();
		if(new Cell(getRow() - 1, getCol()).isValid() && validMove(game.cell[getRow() - 1][getCol()]) && !new King(getName(), getRow() - 1, getCol(), getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() - 1, getCol()));
		}
		if(new Cell(getRow() + 1, getCol()).isValid() && validMove(game.cell[getRow() + 1][getCol()]) && !new King(getName(), getRow() + 1, getCol(), getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() + 1, getCol()));
		}
		if(new Cell(getRow(), getCol() + 1).isValid() && validMove(game.cell[getRow()][getCol() + 1]) && !new King(getName(), getRow(), getCol() + 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow(), getCol() + 1));
		}
		if(new Cell(getRow(), getCol() - 1).isValid() && validMove(game.cell[getRow()][getCol() - 1]) && !new King(getName(), getRow(), getCol() - 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow(), getCol() - 1));
		}
		if(new Cell(getRow() + 1, getCol() + 1).isValid() && validMove(game.cell[getRow() + 1][getCol() + 1]) && !new King(getName(), getRow() + 1, getCol() + 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() + 1, getCol() + 1));
		}
		if(new Cell(getRow() + 1, getCol() - 1).isValid() && validMove(game.cell[getRow() + 1][getCol() - 1]) && !new King(getName(), getRow() + 1, getCol() - 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() + 1, getCol() - 1));
		}
		if(new Cell(getRow() - 1, getCol() - 1).isValid() && validMove(game.cell[getRow() - 1][getCol() - 1]) && !new King(getName(), getRow() - 1, getCol() - 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() - 1, getCol() - 1));
		}
		if(new Cell(getRow() - 1, getCol() + 1).isValid() && validMove(game.cell[getRow() - 1][getCol() + 1]) && !new King(getName(), getRow() - 1, getCol() + 1, getTeam()).isInCheck(game)){
			pos.add(new Cell(getRow() - 1, getCol() + 1));
		}
		game.cell[getRow()][getCol()].setPiece(temp);
		return pos;
	}
	
	public boolean isKing(){
		return true;
	}
	
	public boolean isInCheck(GameBoard game){
    	//CHECK FOR PAWNS UP HERE
		if(getTeam().equals("white")){
			if(new Cell(getRow() + 1, getCol() - 1).isValid() && !game.cell[getRow() + 1][getCol() - 1].isEmpty() && !game.cell[getRow() + 1][getCol() - 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 1][getCol() - 1].getPiece() instanceof Pawn){
				return true;
			}
			if(new Cell(getRow() + 1, getCol() + 1).isValid() && !game.cell[getRow() + 1][getCol() + 1].isEmpty() && !game.cell[getRow() + 1][getCol() + 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 1][getCol() + 1].getPiece() instanceof Pawn){
				return true;
			}
		}
		if(getTeam().equals("black")){
			if(new Cell(getRow() - 1, getCol() - 1).isValid() && !game.cell[getRow() - 1][getCol() - 1].isEmpty() && !game.cell[getRow() - 1][getCol() - 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 1][getCol() - 1].getPiece() instanceof Pawn){
				return true;
			}
			if(new Cell(getRow() - 1, getCol() + 1).isValid() && !game.cell[getRow() - 1][getCol() + 1].isEmpty() && !game.cell[getRow() - 1][getCol() + 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 1][getCol() + 1].getPiece() instanceof Pawn){
				return true;
			}
		}
		//Checks down
    	for(int row = getRow() + 1; row < game.ROWS; row++){
    		if(new Cell(row, getCol()).isValid()){
    		if(game.cell[row][getCol()].isEmpty()){
    			continue;
    		}
    		else if(game.cell[row][getCol()].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[row][getCol()].getPiece().isRook() && !game.cell[row][getCol()].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[row][getCol()].getPiece() instanceof Queen || game.cell[row][getCol()].getPiece() instanceof Rook){
    			return true;
    		}
    	}
    	}
    	//Checks up
    	for(int row = getRow() - 1; row >= 0; row--){
    		if(game.cell[row][getCol()].isEmpty()){
    			continue;
    		}
    		else if(game.cell[row][getCol()].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[row][getCol()].getPiece().isRook() && !game.cell[row][getCol()].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[row][getCol()].getPiece() instanceof Queen || game.cell[row][getCol()].getPiece() instanceof Rook){
    			return true;
    		}
    	}
    	//Checks right
    	for(int col = getCol() + 1; col < game.COLS; col++){
    		if(game.cell[getRow()][col].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow()][col].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow()][col].getPiece().isRook() && !game.cell[getRow()][col].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[getRow()][col].getPiece() instanceof Queen || game.cell[getRow()][col].getPiece() instanceof Rook){
    			return true;
    		}
    	}
    	//Checks left
    	for(int col = getCol() - 1; col >= 0; col--){
    		if(game.cell[getRow()][col].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow()][col].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow()][col].getPiece().isRook() && !game.cell[getRow()][col].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[getRow()][col].getPiece() instanceof Queen || game.cell[getRow()][col].getPiece() instanceof Rook){
    			return true;
    		}
    	}
    	//Checks up and right
    	for(int x = 1; x + getCol() < game.COLS && getRow() - x >= 0; x++){
    		if(game.cell[getRow() - x][getCol() + x].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow() - x][getCol() + x].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow() - x][getCol() + x].getPiece().isBishop() && !game.cell[getRow() - x][getCol() + x].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[getRow() - x][getCol() + x].getPiece() instanceof Queen || game.cell[getRow() - x][getCol() + x].getPiece() instanceof Bishop){
    			return true;
    		}
    	}
    	//Checks up and left
    	for(int x = -1; x + getCol() >= 0 && getRow() + x >= 0; x--){
    		if(game.cell[getRow() + x][getCol() + x].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow() + x][getCol() + x].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow() + x][getCol() + x].getPiece().isBishop() && !game.cell[getRow() + x][getCol() + x].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[getRow() + x][getCol() + x].getPiece() instanceof Queen || game.cell[getRow() + x][getCol() + x].getPiece() instanceof Bishop){
    			return true;
    		}
    	}
    	//Checks down and right
    	for(int x = 1; x + getCol() < game.COLS && getRow() + x < game.ROWS; x++){
    		if(game.cell[getRow() + x][getCol() + x].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow() + x][getCol() + x].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow() + x][getCol() + x].getPiece().isBishop() && !game.cell[getRow() + x][getCol() + x].getPiece().isQueen()){
    			break;
    		}
    		else if(game.cell[getRow() + x][getCol() + x].getPiece() instanceof Queen || game.cell[getRow() + x][getCol() + x].getPiece() instanceof Bishop){
    			return true;
    		}
    	}
    	//Checks down and left
    	for(int x = -1; x + getCol() >= 0 && getRow() - x < game.ROWS; x--){
    		if(game.cell[getRow() - x][getCol() + x].isEmpty()){
    			continue;
    		}
    		else if(game.cell[getRow() - x][getCol() + x].getPiece().getTeam().equals(getTeam()) ){
    			break;
    		}
    		else if(!game.cell[getRow() - x][getCol() + x].getPiece().isBishop() && !game.cell[getRow() - x][getCol() + x].getPiece().isQueen()){
    			break;
    		}
    		if(game.cell[getRow() - x][getCol() + x].getPiece() instanceof Queen || game.cell[getRow() - x][getCol() + x].getPiece() instanceof Bishop){
    			return true;
    		}
    	}
    	//Checks for Knights
    	if(new Cell(getRow() - 2, getCol() - 1).isValid() && !game.cell[getRow() - 2][getCol() - 1].isEmpty() && !game.cell[getRow() - 2][getCol() - 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 2][getCol() - 1].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() - 2, getCol() + 1).isValid() && !game.cell[getRow() - 2][getCol() + 1].isEmpty() && !game.cell[getRow() - 2][getCol() + 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 2][getCol() + 1].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() - 1, getCol() - 2).isValid() && !game.cell[getRow() - 1][getCol() - 2].isEmpty() && !game.cell[getRow() - 1][getCol() - 2].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 1][getCol() - 2].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() - 1, getCol() + 2).isValid() && !game.cell[getRow() - 1][getCol() + 2].isEmpty() && !game.cell[getRow() - 1][getCol() + 2].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() - 1][getCol() + 2].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() + 1, getCol() - 2).isValid() && !game.cell[getRow() + 1][getCol() - 2].isEmpty() && !game.cell[getRow() + 1][getCol() - 2].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 1][getCol() - 2].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() + 1, getCol() + 2).isValid() && !game.cell[getRow() + 1][getCol() + 2].isEmpty() && !game.cell[getRow() + 1][getCol() + 2].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 1][getCol() + 2].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() + 2, getCol() - 1).isValid() && !game.cell[getRow() + 2][getCol() - 1].isEmpty() && !game.cell[getRow() + 2][getCol() - 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 2][getCol() - 1].getPiece() instanceof Knight){
			return true;
		}
		if(new Cell(getRow() + 2, getCol() + 1).isValid() && !game.cell[getRow() + 2][getCol() + 1].isEmpty() && !game.cell[getRow() + 2][getCol() + 1].getPiece().getTeam().equals(getTeam()) && game.cell[getRow() + 2][getCol() + 1].getPiece() instanceof Knight){
			return true;
		}
		return false;
	}
	
	

}




