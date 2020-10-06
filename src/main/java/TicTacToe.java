public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private int size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.rows=new int[n];
        this.cols=new int[n];
        this.diagonal=0;
        this.antiDiagonal=0;
        this.size=n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int pl=player==1?1:-1;
        this.rows[row]+=pl;
        this.cols[col]+=pl;

        if(row==col) diagonal+=pl;
        if(row+col==this.size-1) antiDiagonal+=pl;

        if(Math.abs(this.rows[row])==this.size ||
                Math.abs(this.cols[col])==this.size ||
                Math.abs(diagonal)==this.size||
                Math.abs(antiDiagonal)==this.size){
            return player;
        }
        return 0;
    }

    public static void main(String args[]){
        TicTacToe toe = new TicTacToe(3);
        toe.move(0, 0, 1);
        toe.move(0, 2, 2);
        toe.move(2, 2, 1);
        toe.move(1, 1, 2);
        toe.move(2, 0, 1);
        toe.move(1, 0, 2);
        toe.move(2, 1, 1);
    }
}
