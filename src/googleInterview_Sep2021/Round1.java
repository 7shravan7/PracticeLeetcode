package googleInterview_Sep2021;
/*
    Round 1 happened on 13 Sep 21 9.30-10.15 pm
 */
public class Round1 {

    /*
        Q1 : Print a given matrix in Spiral Matrix

     */

    public void printSpiralMatrix(int[][] mat) {
        int rowCount = mat.length;
        int colCount = rowCount>0 ? mat[0].length : 0;
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = rowCount-1;
        int colEnd = colCount-1;
        while(rowStart<=rowEnd && colStart<=colEnd){
            // left to right
            for(int j=colStart;j<=colEnd;j++){
                System.out.print(mat[rowStart][j]);
            }
            rowStart++;
            // top to bottom
            for(int i=rowStart;i<=rowEnd;i++){
                System.out.println(mat[i][colEnd]);
            }
            colEnd--;
            if(rowStart<=rowEnd) {
                // left to right
                for (int j = colEnd; j >= colStart; j--) {
                    System.out.println(mat[rowEnd][j]);
                }
                rowEnd--;
            }
            if(colStart<=colEnd) {
                // bottom to top
                for (int i = rowEnd; i >= rowStart; i--) {
                    System.out.println(mat[i][colStart]);
                }
                colStart++;
            }
        }
    }

}
