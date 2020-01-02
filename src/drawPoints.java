import processing.core.*;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class drawPoints extends PApplet {

	public void settings() {
    size(500, 500);
	}
  
	public void setup() {
    	background(180);
    	noLoop();
  	}

	public void draw() {

		double x, y;

		String[] lines = loadStrings("drawMe.txt");
//		println("there are " + lines.length);
		for (int i=0; i < lines.length; i++){
			if (lines[i].length() > 0 ) {
				String[] words= lines[i].split(",");
				x = Double.parseDouble(words[0]);
				y = Double.parseDouble(words[1]);
//				println("xy: " + x + " " + y);
				ellipse((int)x, (int)y, 1, 1);
			}
		}
	}


  	public static void main(String args[]) {
		PApplet.main("drawPoints");

		List<Point> thePoints = new ArrayList<>();

		readInPoints(thePoints);

		List<Point> trans = thePoints.stream().filter(p -> p.getZ() <= 2.0)
				.map(p -> new Point (p.getX()*0.5, p.getY()*0.5, p.getZ()))
				.map(p -> new Point (p.getX() -150, p.getY() -37, p.getZ()))
				.collect(Collectors.toList());

		writePoints(trans);
   }


   public static void readInPoints(List<Point> points){
		try {
			Scanner sc = new Scanner(new File ("positions.txt"));

			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] values = line.split(",");

				points.add(new Point(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2])));
			}
		}
		catch(Exception e){
			System.out.println("Can't open input file");

		}
   }

   public static void writePoints(List<Point> points){
		try{
			PrintStream ps = new PrintStream("drawMe.txt");
			for (Point p: points){
				ps.println(Double.toString(p.getX())+ ", " + Double.toString(p.getY()) + ", " + Double.toString(p.getZ()));
			}
		}
		catch (Exception e){
			System.out.println("Can't open output file.");
		}
   }


}
