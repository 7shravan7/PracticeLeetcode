package design.lld.tic_tac_toe;

import java.io.IOException;
import java.util.Scanner;


public class Board {
	
	int[][] ticTacToeBoard;
	
	int size;
	
	Player player1;
	
	int player1Val;
	
	Player player2;
	
	int player2Val;
	
	Scanner scanner;
	
	public Board(int size) {
		this.size = size;
		ticTacToeBoard = new int[size][size];
		scanner = new Scanner(System.in);
	}
	
	public void setPlayer1(long id, String name) {
		player1 = new Player(id, name);
		player1Val = 1;
	}
	
	public void setPlayer2(long id, String name) {
		player2 = new Player(id, name);
		player2Val = 2;
	}
	
	public void play() throws IOException {
		boolean turn = true;
		while(true) {
			printBoard();
			Player player = turn ? player1 : player2;
			System.out.println(player.getName()+"'s turn. Input coordinates" );
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			if(row<0 || row>=size || col<0 || col>=size) {
				throw new IOException("invalid coordinates");
			}
			if(ticTacToeBoard[row][col]!=0) {
				throw new IOException("Coordinates already taken");
			}
			ticTacToeBoard[row][col] = turn ? player1Val : player2Val;
			if(isWin(player1Val, row, col)) {
				System.out.println("!!! "+player.getName()+" wins!!!");
				break;
			}
			turn = !turn;
		}
	}

	private boolean isWin(int playerVal, int i, int j) {
		boolean isRowEqual = true;
		for(int col=0; col<size; col++) {
			if(ticTacToeBoard[i][col]!=playerVal) {
				isRowEqual = false;
				break;
			}
		}
		if(isRowEqual) {
			return true;
		}
		boolean isColEqual = true;
		for(int row=0; row<size; row++) {
			if(ticTacToeBoard[row][j]!=playerVal) {
				isColEqual = false;
				break;
			}
		}
		if(isColEqual) {
			return true;
		}
		// check diagonal or anti-diagonal
		if(i == j || (i+j+1 == size)) {
			if(i==j) {
				int ii=0;
				while(ii<size) {
					if(ticTacToeBoard[ii][ii]!=playerVal) {
						break;
					}
					ii++;
				}
				return (ii == size);
			} else {
				int ii=0;
				int jj=size-1;
				while(ii<=jj) {
					if(ticTacToeBoard[ii][jj]!=playerVal) {
						break;
					}
					ii++;
					jj--;
				}
				return ii>jj;
			}
		}
		return false;
	}
	
	private void printBoard() {
		System.out.println("The Board");
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.print(ticTacToeBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
}
