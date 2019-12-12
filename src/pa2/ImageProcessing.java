package pa2;

import java.awt.Color;
import java.util.ArrayList;

import pa2.MatrixCuts;

public class ImageProcessing {

	public static Picture reduceWidth(int x, String inputImage) {
		Picture p = new Picture(inputImage);
		int[][] mat = new int[p.height()][p.width()];
		int width = p.width();
		int height = p.height();

		for(int i = 0; i <= x; i++) {
			System.out.println("We are here");
			int[][] importance = importance(mat, p);
			ArrayList<Tuple> widthCut = MatrixCuts.widthCut(importance);
			int[][] newPic = new int[p.height()][width--];
			widthCut.remove(0);

			ImageStitch.crop(p, 1);


		}



		//		for(int w = p.width() - 1; w >= p.width() - x; w--) {
		//			int[][] importance = importance(mat, p);
		//			ArrayList<Tuple> widthCut = MatrixCuts.widthCut(importance);
		//			widthCut.remove(0);
		//			for(Tuple tuple: widthCut) {
		//				int count = 0;
		//				for(int i = tuple.getY(); i < w; i++) {
		//					p.set(tuple.getY() + count, tuple.getX(), p.get(tuple.getY() + count, tuple.getX()));
		//					count++;
		//					
		//				}
		//			}
		//			croppedImage = ImageStitch.crop(p, 1);	
		//			
		//		}
		return null;
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
		Picture p = reduceWidth(500, "iastate1.jpg");
		p1.show();
		p.show();
	}



}
