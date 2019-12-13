package pa2;

import java.util.ArrayList;
import pa2.MatrixCuts;

/**
 * 
 * @author Matthew Smith and Mitchell Knoth
 *
 */
public class ImageProcessor {

	public static Picture reduceWidth(int x, String inputImage) {
		Picture p = new Picture(inputImage);
		Picture croppedPicture = null;


		for(int k = 1; k <= x; k++) {
			int[][] importance = null;
			if(croppedPicture == null) {
				int[][] mat = new int[p.height()][p.width()];
				importance = importance(mat, p);
			}else {
				int[][] mat = new int[p.height()][p.width() - k];
				importance = importance(mat, croppedPicture);
			}
			ArrayList<Tuple> widthCut = MatrixCuts.widthCut(importance);
			widthCut.remove(0);
			croppedPicture = new Picture(p.width() - k, p.height()); 
			for(Tuple tuple: widthCut) {
				int i = tuple.getX();
				for(int j = 0; j < tuple.getY(); j++) { //left pixels of cut
					if(j+1 > croppedPicture.width()) {
						break;
					}
					croppedPicture.set(j, i, p.get(j, i));
				}
				for(int j = tuple.getY(); j <= croppedPicture.width(); j++) { //right pixels after cut
					if(j+1 > croppedPicture.width() && j == tuple.getY()) {
						croppedPicture.set(j-2, i, p.get(j-1, i));
						croppedPicture.set(j-1, i, p.get(j, i));
						break;
					}
					if(j+1 > croppedPicture.width()) {
						croppedPicture.set(j-1, i, p.get(j, i));
						break;
					}
					croppedPicture.set(j, i, p.get(j+1, i));
				}
			}

		}

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
