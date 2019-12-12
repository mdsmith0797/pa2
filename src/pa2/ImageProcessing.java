package pa2;

import java.awt.Color;
import java.util.ArrayList;

import pa2.MatrixCuts;

public class ImageProcessing {

	public static Picture reduceWidth(int x, String inputImage) {
		Picture p = new Picture(inputImage);
		Picture croppedPicture = null;
		int[][] mat = new int[p.height()][p.width()];

		//for(int k = 0; k <= x; k++) {
		int[][] importance = importance(mat, p);
		ArrayList<Tuple> widthCut = MatrixCuts.widthCut(importance);
		widthCut.remove(0);

		for(Tuple tuple: widthCut) {
			int i = tuple.getY();
			for(int j = 0; j < p.height(); j++) {
				p.set(i - 1, j, p.get(i, j));
			}

//			for(int i = tuple.getY() + 1; i < p.width(); i++) {
//				if(j >= p.height()) {
//					break;
//				}
//				p.set(i - 1, j, p.get(i, j));
//				
//			}

		}

		croppedPicture = ImageStitch.crop(p, x);
		//}
		return croppedPicture;
	}

	private static int[][] importance(int[][] importance, Picture picture){

		for(int i = 0; i < importance.length - 1; i++) {
			for(int j = 0; j < importance[0].length - 1; j++) {
				if(j == 0) {
					importance[i][j] = ImageStitch.pixelDistance(picture.get(j, i), picture.get(j+1, i));
				}
				else if(j == importance[0].length - 1) {
					importance[i][j] = ImageStitch.pixelDistance(picture.get(j, i), picture.get(j-1, i));
				}
				else {
					importance[i][j] = ImageStitch.pixelDistance(picture.get(j-1, i), picture.get(j+1, i));
				}
			}
		}
		return importance;
	}

	public static void main(String[] args) {
		Picture p1 = new Picture("iastate1.jpg");
		Picture p = reduceWidth(50, "iastate1.jpg");
		p1.show();
		p.show();
	}



}
