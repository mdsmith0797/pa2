package pa2;

public class Main {

	public static void main(String[] args) {
		int array[][] = new int[][] {
			{ 5, 7, 9, 4, 5},
			  { 2, 3, 1, 1, 2},
			  { 2, 0, 49, 46, 8},
			  { 3, 1, 1, 1, 1},
			  { 50, 51, 25, 26, 1}
		};
		
		MatrixCuts.widthCut(array);
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.println(array[i][j]);
			}
			
		}
		
		//MatrixCuts.stitchCut(array);
		

	}

}
