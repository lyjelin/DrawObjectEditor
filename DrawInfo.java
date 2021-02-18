//package ass6;

import java.io.Serializable;

/**
 * Class DrawInfo for serialization of java objects
 * @author Lee Yoon Jeong
 *
 */
public class DrawInfo implements Serializable {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String shapename;
	private double radius;
	private int no_of_sides;
	private double x1, y1, x2, y2, x3, y3, x4, y4;
	private int r, g, b;
	
	/**
	 * DrawInfo Constructor for Line
	 * 
	 * @param shapename : line
	 * @param x1 start point : x-coordinate
	 * @param y1 start point : y-coordinate 
	 * @param x2 end point : x-coordinate 
	 * @param y2 end point : y-coordinate 
	 * @param r Color.Red()
	 * @param g Color.Green()
	 * @param b Color.Blue()
	 */
	public DrawInfo(String shapename, double x1, double y1, double x2, double y2, int r, int g, int b) {
		super();
		this.shapename = shapename;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * DrawInfo Constructor for Circle
	 * 
	 * @param shapename : circle
	 * @param x1 center point : x-coordinate
	 * @param y1 center point : y-coordinate
	 * @param radius of circle
	 * @param r Color.Red()
	 * @param g Color.Green()
	 * @param b Color.Blue()
	 */
	public DrawInfo(String shapename, double x1, double y1, double radius, int r, int g, int b) {
		super();
		this.shapename = shapename;
		this.radius = radius;
		this.x1 = x1;
		this.y1 = y1;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * DrawInfo Constructor for Triangle
	 * 
	 * @param shapename : triangle
	 * @param x1 top left corner point : x-coordinate
	 * @param y1 top left corner point : y-coordinate 
	 * @param x2 bottom left corner point : x-coordinate 
	 * @param y2 bottom left corner point : y-coordinate 
	 * @param x3 bottom right corner point : x-coordinate 
	 * @param y3 bottom right corner point : y-coordinate 
	 * @param r Color.Red()
	 * @param g Color.Green()
	 * @param b Color.Blue()
	 */
	public DrawInfo(String shapename, int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		super();
		this.shapename = shapename;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * DrawInfo Constructor for Quadrilateral
	 * 
	 * @param shapename : quadrilateral
	 * @param no_of_sides : 4
	 * @param x1 top left corner point : x-coordinate
	 * @param y1 top left corner point : y-coordinate 
	 * @param x2 bottom left corner point : x-coordinate 
	 * @param y2 bottom left corner point : y-coordinate 
	 * @param x3 bottom right corner point : x-coordinate 
	 * @param y3 bottom right corner point : y-coordinate 
	 * @param x4 top right corner point : x-coordinate
	 * @param y4 top right corner point : y-coordinate 
	 * @param r Color.Red()
	 * @param g Color.Green()
	 * @param b Color.Blue()
	 */
	public DrawInfo(String shapename, int no_of_sides, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4,
			int r, int g, int b) {
		super();
		this.shapename = shapename;
		this.no_of_sides = no_of_sides;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Getter of name of shape : line/circle/triangle/quadrilateral 
	 * 
	 * @return shape name
	 */
	public String getShapename() {
		return shapename;
	}
	
	/**
	 * Getter of radius
	 * 
	 * @return radius of circle
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Getter of number of sides of polygon
	 * 
	 * @return number of sides
	 */
	public int getNo_of_sides() {
		return no_of_sides;
	}
	
	
	/**
	 * Getter of x1
	 * 
	 * @return x1 1st coordinate x1
	 */
	public double getX1() {
		return x1;
	}
	
	/**
	 * Getter of y1
	 * 
	 * @return y1 1st coordinate y1
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * Getter of x2
	 * 
	 * @return x2 2nd coordinate x2
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * Getter of y2
	 * 
	 * @return y2 2nd coordinate y2
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * Getter of x3
	 * 
	 * @return x3 3rd coordinate x3
	 */
	public double getX3() {
		return x3;
	}

	/**
	 * Getter of y3
	 * 
	 * @return y3 3rd coordinate y3
	 */
	public double getY3() {
		return y3;
	}

	/**
	 * Getter of x4
	 * 
	 * @return x4 4th coordinate x4
	 */
	public double getX4() {
		return x4;
	}

	/**
	 * Getter of y4
	 * 
	 * @return y4 4th coordinate y4
	 */
	public double getY4() {
		return y4;
	}

	/**
	 * Getter of Color.Red()
	 * 
	 * @return r graphic object color Color.
	 */
	public int getR() {
		return r;
	}

	/**
	 * Getter of Color.Green()
	 * 
	 * @return g graphic object color Color.Green()
	 */
	public int getG() {
		return g;
	}

	/**
	 * Getter of Color.Blue()
	 * 
	 * @return b graphic object color Color.Blue()
	 */
	public int getB() {
		return b;
	}

	/**
	 * Setter for name of shape : line/circle/triangle/quadrilateral
	 * 
	 * @param shapename name of shape object
	 */
	public void setShapename(String shapename) {
		this.shapename = shapename;
	}

	/**
	 * Setter for radius
	 * 
	 * @param radius radius of circle
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * Setter for no_of_sides of polygon
	 * 
	 * @param no_of_sides
	 */
	public void setNo_of_sides(int no_of_sides) {
		this.no_of_sides = no_of_sides;
	}

	/**
	 * Setter of x1
	 * 
	 * @param x1 1st coordinate x1
	 */
	public void setX1(double x1) {
		this.x1 = x1;
	}

	/**
	 * Setter of y1
	 * 
	 * @param y1 1st coordinate y1
	 */
	public void setY1(double y1) {
		this.y1 = y1;
	}
	
	/**
	 * Setter of x2
	 * 
	 * @param x2 2nd coordinate x2
	 */
	public void setX2(double x2) {
		this.x2 = x2;
	}

	/**
	 * Setter of y2
	 * 
	 * @param y2 2nd coordinate y2
	 */
	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	/**
	 * Setter of x3
	 * 
	 * @param x3 3rd coordinate x3
	 */
	public void setX3(double x3) {
		this.x3 = x3;
	}

	/**
	 * Setter of y3
	 * 
	 * @param y3 3rd coordinate y3
	 */
	public void setY3(double y3) {
		this.y3 = y3;
	}

	/**
	 * Setter of x4
	 * 
	 * @param x4 4th coordinate x4
	 */
	public void setX4(double x4) {
		this.x4 = x4;
	}

	/**
	 * Setter of y4
	 *
	 * @param y4 4th coordinate y4
	 */
	public void setY4(double y4) {
		this.y4 = y4;
	}

	/**
	 * Setter of Color.Red()
	 * @param r graphic object color Color.Red()
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * Setter of Color.Green()
	 * 
	 * @param g graphic object color Color.Green()
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * Setter of Color.Blue()
	 * 
	 * @param b graphic object color Color.Blue()
	 */
	public void setB(int b) {
		this.b = b;
	}



}
