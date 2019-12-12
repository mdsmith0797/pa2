package pa2;

import java.util.ArrayList;

public class MatrixCuts {

	public static ArrayList<Tuple> widthCut(int[][] M){
		ArrayList<Tuple> flippedList = new ArrayList<Tuple>();
		for(int i = 1; i < M.length; i++) {
			for(int j = 0; j < M[0].length; j++) {
				if(j - 1 < 0) {
					M[i][j] = M[i][j] + M[i - 1][j];
				}else if(j + 1 > M[0].length - 1) {
					M[i][j] = M[i][j] + M[i - 1][j - 1];
				}else {
					int min = findMin(M[i][j] + M[i - 1][j - 1], M[i][j] + M[i - 1][j + 1], M[i][j] + M[i - 1][j]);
					M[i][j] = min;
				}
			}
		}

		int minValue = M[M.length - 1][0];
		int jCoordinate = 0;
		for(int i = M[0].length - 1; i >= 0; i--) {
			if(M[M.length - 1][i] < minValue) {
				minValue = M[M.length - 1][i];
				jCoordinate = i;
			}
		}
		Tuple endingIndex = new Tuple(M.length - 1, jCoordinate);
		Tuple min = new Tuple(minValue, -1);
		flippedList.add(min);
		flippedList.add(endingIndex);

		for(int i = M.length - 1; i > 0; i--) {
			if(jCoordinate + 1 > M[0].length - 1) {
				int x = M[i - 1][jCoordinate - 1];
				int y = M[i - 1][jCoordinate];
				int minVal = Math.min(x, y);
				if(minVal == x) {
					Tuple tuple = new Tuple(i - 1, jCoordinate - 1);
					flippedList.add(tuple);
					jCoordinate--;
				}
				else if(minVal == y) {
					Tuple tuple = new Tuple(i - 1, jCoordinate);
					flippedList.add(tuple);
				}
			}
			else if(jCoordinate - 1 <= 0) {
				int y = M[i - 1][jCoordinate];
				int z = M[i - 1][jCoordinate + 1];
				int minVal = Math.min(y, z);
				if(minVal == y) {
					Tuple tuple = new Tuple(i - 1, jCoordinate);
					flippedList.add(tuple);
				}
				else if(minVal == z) {
					Tuple tuple = new Tuple(i - 1, jCoordinate + 1);
					flippedList.add(tuple);
					jCoordinate++;
				}
			}
			else {
				int x = M[i - 1][jCoordinate - 1];
				int y = M[i - 1][jCoordinate];
				int z = M[i - 1][jCoordinate + 1];
				int minVal = findMin(x, y, z);
				if(minVal == x) {
					Tuple tuple = new Tuple(i - 1, jCoordinate - 1);
					flippedList.add(tuple);
					jCoordinate--;
				}
				else if(minVal == y) {
					Tuple tuple = new Tuple(i - 1, jCoordinate);
					flippedList.add(tuple);
				}
				else if(minVal == z) {
					Tuple tuple = new Tuple(i - 1, jCoordinate + 1);
					flippedList.add(tuple);
					jCoordinate++;
				}
			}
		}

		ArrayList<Tuple> resultList = new ArrayList<>();
		resultList.add(flippedList.get(0));

		for(int i = flippedList.size() - 1; i >= 1; i--) {
			resultList.add(flippedList.get(i));
		}
		return resultList;
	}

	private static int findMin(int x, int y, int z) {
		int min = 0;
		if(x <= y && x <= z) {
			min = x;
		}else if(y <= x && y <= z) {
			min = y;
		}else if(z <= x && z <= y) {
			min = z;
		}
		return min;
	}

	public static ArrayList<Tuple> stitchCut(int[][] M){
		ArrayList<Tuple> flippedList = new ArrayList<Tuple>();
		for(int i = 1; i < M.length; i++) {
			for(int j = 0; j < M[0].length; j++) {
				if(j - 1 < 0) {
					M[i][j] = M[i][j] + M[i - 1][j];
				}else if(i + 1 > M.length) {
					M[i][j] = M[i][j] + M[i][j - 1];
				}else {
					int min = findMin(M[i][j] + M[i][j - 1], M[i][j] + M[i - 1][j], M[i][j] + M[i - 1][j - 1]);
					M[i][j] = min;
				}
			}
		}

		int minValue = M[M.length - 1][0];
		int jCoordinate = 0;
		for(int i = M[0].length - 1; i >= 0; i--) {
			if(M[M.length - 1][i] < minValue) {
				minValue = M[M.length - 1][i];
				jCoordinate = i;
			}
		}
		Tuple endingIndex = new Tuple(M.length - 1, jCoordinate);
		Tuple min = new Tuple(minValue, -1);
		flippedList.add(min);
		flippedList.add(endingIndex);

		int i = M.length - 1;
		while(i > 0) {
			if(jCoordinate - 1 < 0) {
				int x = M[i - 1][jCoordinate];
				Tuple tuple = new Tuple(i - 1, jCoordinate);
				flippedList.add(tuple);
				i--;
			}else {
				int x = M[i - 1][jCoordinate];
				int y = M[i][jCoordinate - 1];
				int z = M[i - 1][jCoordinate - 1];
				int minVal = findMin(x, y, z);
				if(minVal == x) {
					Tuple tuple = new Tuple(i - 1, jCoordinate);
					flippedList.add(tuple);
					i--;
				}else if(minVal == y) {
					Tuple tuple = new Tuple(i, jCoordinate - 1);
					flippedList.add(tuple);
					jCoordinate--;
				}else if(minVal == z) {
					Tuple tuple = new Tuple(i - 1, jCoordinate - 1);
					flippedList.add(tuple);
					jCoordinate--;
					i--;
				}
			}
		}



		ArrayList<Tuple> resultList = new ArrayList<>();
		resultList.add(flippedList.get(0));

		for(int k = flippedList.size() - 1; k >= 1; k--) {
			resultList.add(flippedList.get(k));
		}

		return resultList;
	}
}
