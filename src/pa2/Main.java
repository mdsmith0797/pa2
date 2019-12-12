package pa2;

public class Main {

	public static void main(String[] args) {
		int array[][] = new int[][] {
			{ 5, 7,  },
			  { 2, 3,   },
			  { 2, 0,},
			  { 3, 1, },
			  { 50, 51, }
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
