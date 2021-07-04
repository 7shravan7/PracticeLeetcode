package dp.cci_book;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PaintFill {
	
	public void fillPaintDfs(int[][] inputArr, int rowIndex, int colIndex, int newColor) {
		if(inputArr[rowIndex][colIndex] == newColor) {
			return;
		}
		fillPaintDfs(inputArr, rowIndex, colIndex, inputArr[rowIndex][colIndex], newColor);
	}
	
	
	private void fillPaintDfs(int[][] arr, int rowIndex, int colIndex, int orginalColor, int newColor) {
		if(rowIndex<0 || rowIndex>=arr.length || colIndex<0 || colIndex>=arr[0].length) {
			return;
		}
		if(arr[rowIndex][colIndex] == orginalColor) {
			arr[rowIndex][colIndex] = newColor;
			fillPaintDfs(arr, rowIndex-1, colIndex, orginalColor, newColor);
			fillPaintDfs(arr, rowIndex+1, colIndex, orginalColor, newColor);
			fillPaintDfs(arr, rowIndex, colIndex-1, orginalColor, newColor);
			fillPaintDfs(arr, rowIndex, colIndex+1, orginalColor, newColor);
		}
	}
	
	public void fillPaintBfs(int[][] inputArr, int rowIndex, int colIndex, int newColor) {
		if(inputArr[rowIndex][colIndex] == newColor) {
			return;
		}
		fillPaintBfs(inputArr, rowIndex, colIndex, inputArr[rowIndex][colIndex], newColor);
	}
	
	private void fillPaintBfs(int[][] arr, int rowIndex, int colIndex, int orginalColor, int newColor) {
		Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
		queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(rowIndex, colIndex));
		while(!queue.isEmpty()) {
			Map.Entry<Integer, Integer> points = queue.poll();
			int row = points.getKey();
			int col = points.getValue();
			if(arr[row][col] == orginalColor) {
				arr[row][col] = newColor;
				if(row-1>=0) {
					queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(row-1, col));
				}
				if(row+1<arr.length) {
					queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(row+1, col));
				}
				if(col-1>=0) {
					queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(row, col-1));
				}
				if(col+1<arr[0].length) {
					queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(row, col+1));
				}
			}
		}
	}


	public void printArray(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] inputArr = {{1,2,1,1,1},
							{0,1,0,0,1},
							{1,1,1,2,1},
							{0,1,0,1,0},
							{1,1,1,1,0}};
		PaintFill paintFill = new PaintFill();
		// paintFill.fillPaintDfs(inputArr, 1, 0, 3);
		paintFill.fillPaintBfs(inputArr, 1, 1, 3);
		paintFill.printArray(inputArr);
	}

}
