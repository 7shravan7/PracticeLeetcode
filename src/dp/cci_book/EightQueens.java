package dp.cci_book;

import java.util.ArrayList;
import java.util.List;

public class EightQueens {
	
	public void eightQueensWay(int noOfQueens) {
		List<List<Integer>> solutionsList =  new ArrayList<>();
		List<Integer> placedColumnIndexList = new ArrayList<>(); 
		placeQueen(0, placedColumnIndexList, noOfQueens, solutionsList);
		printAllSolutions(solutionsList, noOfQueens);
		return;
	}
	
	private void placeQueen(int rowIndex, List<Integer> placedColumnIndexList, int noOfQueens,
			List<List<Integer>> solutionList) {
		if(rowIndex == noOfQueens) {
			solutionList.add(new ArrayList<Integer>(placedColumnIndexList));
		} else {
			for(int col=0;col<noOfQueens;col++) {
				placedColumnIndexList.add(col);
				if(isAllowedColumnIndex(placedColumnIndexList)) {
					placeQueen(rowIndex+1, placedColumnIndexList, noOfQueens, solutionList);
				}
				placedColumnIndexList.remove(placedColumnIndexList.size()-1);
			}
		}
	}

	private boolean isAllowedColumnIndex(List<Integer> placedColumnIndexList) {
		int currRowIndex = placedColumnIndexList.size()-1;
		int currColIndex = placedColumnIndexList.get(currRowIndex);
		for(int rowIndex=0;rowIndex<currRowIndex;rowIndex++) {
			int colIndex = placedColumnIndexList.get(rowIndex);
			int diff = Math.abs(currColIndex-colIndex);
			if(diff==0 || diff == currRowIndex-rowIndex) {
				return false;
			}
		}
		return true;
	}
	
	public void printAllSolutions(List<List<Integer>> solutionsList, int noOfQueens) {
		System.out.println("No of ways we can place "+noOfQueens +" queens : "+solutionsList.size());
		solutionsList.forEach(solutionList->{
			int[][] chessBoard = new int[noOfQueens][noOfQueens];
			for(int row=0;row<noOfQueens;row++) {
				int solutionColValue = solutionList.get(row);
				for(int col=0;col<noOfQueens;col++) {
					if(col == solutionColValue) {
						chessBoard[row][col] =1;
					} else {
						chessBoard[row][col] =0;
					}
				}
			}
			printChessBoard(chessBoard);
		});
	}

	private void printChessBoard(int[][] chessBoard) {
		System.out.println("---- solution ---");
		for(int row=0;row<chessBoard.length;row++) {
			for(int col=0;col<chessBoard[0].length;col++) {
				System.out.print(chessBoard[row][col]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		EightQueens eightQueens = new EightQueens();
		eightQueens.eightQueensWay(5);
	}

}
