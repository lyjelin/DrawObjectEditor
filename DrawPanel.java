//package ass6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * DrawPanel inherits JPanel
 * Acts to as editor panel for the DrawFrame
 * 
 * @author Lee Yoon Jeong
 *
 */
public class DrawPanel extends JPanel implements MouseListener {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Point2D> points = new ArrayList<Point2D>();
	private ArrayList<Color> shapeColor = new ArrayList<Color>();
	private ArrayList<graphicInfo> saveInfo = new ArrayList<graphicInfo>();
	private ArrayList<DrawInfo> myFile = new ArrayList<DrawInfo>();
	//private DataInputStream din;
	private DataOutputStream dout;
	private Shape checkShape = null;
	private Point2D start, end;
	private String m = null;
	private int selectedShape = -1;
	private int count = 0;
	private int dx, dy;
	private int c = 0;
	private JButton bttn;
	
	/**
	 * Constructor for DrawPanel
	 */
	public DrawPanel() {
		addMouseListener(this);
	}
	
	/**
	 * Draw & Paint all the objects on DrawPanel
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
		//Graphics2D gc =(Graphics2D) g;
		
		for(Shape s:shapes) {	
			if (shapeColor.get(shapes.indexOf(s))!=null) {
				g2.setColor(shapeColor.get(shapes.indexOf(s)));
				g2.fill(s);
			}
			
			if ( shapes.indexOf(s) == selectedShape ) {
				g2.setColor(Color.GREEN);		
				
			}
			
			else {
				if (shapeColor.get(shapes.indexOf(s))==null)
					g2.setColor(Color.BLACK);
				else 
					g2.setColor(shapeColor.get(shapes.indexOf(s)));
			}
		
			
			g2.draw(s);
			//gc.draw(s);
		}
	}
	
	/**
	 * Control button to be disabled
	 */
	public void switchOff(JButton bttn) {
		bttn.setEnabled(false);
	}
	
	/**
	 * Control buttons to be disabled
	 * 
	 * @param b1 Input JButton
	 * @param b2 Input JButton
	 * @param b3 Input JButton
	 * @param b4 Input JButton
	 */
	public void switchOff(JButton b1, JButton b2, JButton b3, JButton b4) {
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
	}
	/**
	 * Control buttons to be enabled
	 * 
	 * @param bttn Input JButton
	 */
	public void switchOn(JButton bttn) {
		this.bttn=bttn;
		bttn.setEnabled(true);
	}
	
	/**
	 * Sets the mode of DrawPanel from DrawFrame
	 * 
	 * @param m user mode
	 * 			LINE : draw line
	 * 			CIRCLE : draw circle
	 * 			TRIANGLE : draw triangle
	 * 			QUADRILATERAL : draw quadrilateral
	 * 			SELECT : select shape
	 * 			MOVE : move selected shape
	 * 			DELETE : delete selected shape
	 * 			COPY : copy selected shape
	 * 			RANDOM COLOR : fill in selected shape with random color
	 * 			SAVE : save all objects on DrawFrame in .object file
	 * 			LOAD : load .object file
	 * 			EXPORT : export all objects on DrawFrame into ASCII file (.txt)
	 * 			IMPORT : import all object from exported file onto DrawFrame
	 * @param bttn Input JButton
	 */
	public void userOperation (String m, JButton bttn) {
		this.bttn=bttn;
		this.m = m;
		
		deleteShape(bttn);
		copyShape(bttn);
		colorShape(bttn);
		
	}
	

	/**
	 * Draw graphic according to the button clicked by the user:
	 * 		Line drawable if "Line" button clicked
	 * 		Circle drawable if "Circle" button clicked
	 * 		Triangle drawable if "Triangle" button clicked
	 * 		Quadrilateral drawable if "Quadrilateral" button clicked
	 * 
	 * @param e Mouse pressed by user
	 */
	public void drawGraphic(MouseEvent e) {
		switch (m) {
			case "L" :{
				int x=e.getX(); int  y=e.getY();
				Point2D.Float point = new Point2D.Float(x, y);
				points.add(point);
				count++;
				// line to be drawn in every two clicks
				if (count % 2 == 0) {
					Line2D line = new Line2D.Float();
					line.setLine(points.get(0),points.get(1));
					checkShape=line;
					shapes.add(checkShape);
					shapeColor.add(null);
					points.clear();
			
					count=0;
					switchOn(bttn);
				}
				break;
			}

			case "C" :{
				int x=e.getX(); int  y=e.getY();
				Point2D.Float point = new Point2D.Float(x, y);
				points.add(point);
				count++;
		
				if (count % 2 == 0) {
					Ellipse2D.Float ellipse = new Ellipse2D.Float();
					double r = (points.get(0).distance(points.get(1)));
					ellipse.setFrame(points.get(0).getX()-r, points.get(0).getY()-r, r*2, r*2);
					checkShape=ellipse;
					shapes.add(checkShape);
					shapeColor.add(null);
					points.clear();
				
					count=0;
					switchOn(bttn);
				}
				break;
			}
	 
			case "T" :{
				int x =e.getX(); int y=e.getY();
				Point2D.Float point = new Point2D.Float(x, y);
				points.add(point);
				count++;
				// triangle to be drawn in every three clicks
				if (count % 3 == 0) {
				
					int tx[] = {(int)points.get(0).getX(), (int)points.get(1).getX(), (int)points.get(2).getX()};
					int ty[] = {(int)points.get(0).getY(), (int)points.get(1).getY(), (int)points.get(2).getY()};
				
					Polygon triangle = new Polygon(tx, ty, count);
					checkShape=triangle;
					shapes.add(triangle);
					shapeColor.add(null);
					points.clear();
			
					count=0;
					switchOn(bttn);
				}
				break;
			}
	
			case "Q" :{
				int x =e.getX(); int y=e.getY();
				Point2D.Float point = new Point2D.Float(x, y);
				points.add(point);
				count++;
				// triangle to be drawn in every three clicks
				if (count % 4 == 0) {
		
					int qx[] = {(int)points.get(0).getX(), (int)points.get(1).getX(), (int)points.get(2).getX(),(int)points.get(3).getX()};
					int qy[] = {(int)points.get(0).getY(), (int)points.get(1).getY(), (int)points.get(2).getY(),(int)points.get(3).getY()};
					Polygon quad = new Polygon(qx, qy, count);
					checkShape=quad;
					shapes.add(quad);
					shapeColor.add(null);
					points.clear();
			
					count=0;
					switchOn(bttn);
				}	
			break;
			}	
		}
	}
	
	/**
	 * Select shape on the panel
	 * 
	 * @param e Mouse pressed by user
	 */
	public void selectShape(MouseEvent e) {
		switch(m) {
			case "SELECT" :{
				for (Shape s : shapes) { 
					int sx=e.getX(); 
					int sy=e.getY();
					Point2D.Float point = new Point2D.Float(sx, sy);
		
					if (s.getBounds2D().contains(point)){
						selectedShape = shapes.indexOf(s);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Move the selected shape by a certain distance
	 * Defines the start position
	 * 
	 * @param e Mouse pressed by user
	 */
	public void moveShape(MouseEvent e) {
		switch (m) {
			case "MOVE" :{
				int sx = e.getX();
				int sy = e.getY();
	
				Point2D.Float sp = new Point2D.Float(sx, sy);
				start = sp;
		
				break;
			}
		}
	}
	
	/**
	 * Move the selected shape by a certain distance
	 * @param dx distance moved by x-coordinate
	 * @param dy distance moved by y-coordinate
	 * @param bttn Input JButton
	 */
	public void moveShape(int dx, int dy, JButton bttn) {
		switch (m){
			case "MOVE":{
				if (selectedShape > -1) {
					Shape currentShape = shapes.get(selectedShape);
				
					if (currentShape instanceof Line2D.Float) {
						Line2D.Float  newLine = (Float) ((Line2D.Float) currentShape).clone();
						Point2D p1 = (newLine).getP1();
						Point2D p2 = (newLine).getP2();
						p1.setLocation(p1.getX()-dx, p1.getY()-dy);
						p2.setLocation(p2.getX()-dx, p2.getY()-dy);
						newLine.setLine(p1, p2);
						shapes.set(selectedShape, newLine);
					
						selectedShape = -1;
					}
					
					if (currentShape instanceof Ellipse2D.Float) {
						Ellipse2D.Float circle = new Ellipse2D.Float();
		
						double x = ((Ellipse2D.Float) currentShape).getX();
						double y = ((Ellipse2D.Float) currentShape).getY();
						double w = ((Ellipse2D.Float) currentShape).getWidth();
						double h = ((Ellipse2D.Float) currentShape).getHeight();
						
						circle.setFrame(x-dx, y-dy, w, h);
						shapes.set(selectedShape, circle);
					
						selectedShape = -1;
					}
					if (currentShape instanceof Polygon) {
						int edge = ((Polygon) currentShape).npoints;
						int[] x = ((Polygon) currentShape).xpoints;
						int[] y = ((Polygon) currentShape).ypoints;
					
						int[] newx = new int[edge];
						int[] newy = new int[edge];
					
						for (int i=0;i<edge;i++) {
							newx[i]=x[i]-dx;
							newy[i]=y[i]-dy;
						}
						Polygon poly = new Polygon(newx, newy, edge);
						shapes.set(selectedShape, poly);
					
						points.clear();
						start = null; 
						end=null;
						selectedShape = -1;
					}
				}
			
			}
			switchOn(bttn);
			break;
		}
	}
	
	/**
	 * Deletes selected shape by removing from the shapeArray
	 * 
	 * @param bttn Input JButton
	 */
	public void deleteShape(JButton bttn) {
		switch (m) {
			case "DELETE" :{
	
				if (selectedShape > -1) {
					shapes.remove(selectedShape);
					shapeColor.remove(selectedShape);
				}
				selectedShape=-1;
				repaint();
			}
			switchOn(bttn);
			break;
		}
	}
	
	/**
	 * Make a copy of selected shape and draw it on the panel
	 * 
	 * @param bttn Input JButton
	 */
	public void copyShape(JButton bttn) {
		switch (m){
			case "COPY":{
				if (selectedShape>-1) {
					Shape currentShape = shapes.get(selectedShape);
					Color originalColor = shapeColor.get(selectedShape);
			
					if (currentShape instanceof Line2D.Float) {
						Line2D.Float  newLine = (Float) ((Line2D.Float) currentShape).clone();
						Point2D p1 = (newLine).getP1();
						Point2D p2 = (newLine).getP2();
						p1.setLocation(p1.getX()-20, p1.getY()-20);
						p2.setLocation(p2.getX()-20, p2.getY()-20);
						newLine.setLine(p1, p2);
						shapes.add(newLine);
						shapeColor.add(originalColor);
						selectedShape = -1;
					}
			
			
					if (currentShape instanceof Ellipse2D.Float) {
						Ellipse2D.Float circle = new Ellipse2D.Float();
						double x = ((Ellipse2D.Float) currentShape).getX();
						double y = ((Ellipse2D.Float) currentShape).getY();
						double w = ((Ellipse2D.Float) currentShape).getWidth();	
						double h = ((Ellipse2D.Float) currentShape).getHeight();
						circle.setFrame(x-20, y-20, w, h);
						shapes.add(circle);
						shapeColor.add(originalColor);
						selectedShape = -1;
					}
			
					if (currentShape instanceof Polygon) {
						int edge = ((Polygon) currentShape).npoints;
						int x[] = ((Polygon) currentShape).xpoints;
						int y[] = ((Polygon) currentShape).ypoints;
						int newx[] = new int[edge];
						int newy[] = new int[edge];
						for (int i = 0; i < edge; i ++) {
							newx[i] = x[i] - 20;
							newy[i] = y[i] - 20;
						}
						Polygon poly = new Polygon(newx, newy, edge);
						shapes.add(poly);
						shapeColor.add(originalColor);
						selectedShape = -1;
					}
				}
				repaint();
			}
			switchOn(bttn);
			break;
		}
	}
	
	/**
	 * Fill in selected shape with random color
	 * 
	 * @param bttn Input JButton
	 */
	public void colorShape(JButton bttn) {
		switch (m){
			case "RANDOMCOLOR" :{
				int x= (int) (Math.random()*250);
				int y= (int) (Math.random()*250);
				int z= (int) (Math.random()*250);
				Color random = new Color(x, y, z);
        
				Shape currentShape = shapes.get(selectedShape);
				
				for (Shape s : shapes) {
					if (s.equals(currentShape)) {
						shapeColor.set(shapes.indexOf(s), random);
					}
				}
				
				selectedShape=-1;
			}
			repaint();
			switchOn(bttn);
			break;
		}
	}
	
	/**
	 * Save all objects on DrawPanel with extension .object
	 * 
	 * @param m user mode
	 * @param filePath path to the file
	 * @param bttn Input JButton
	 * @throws IOException if stream to file cannot be written to or closed
	 */
	public void saveShape(String m, String filePath, JButton bttn) throws IOException {
		switch (m) {
			case "SAVE" :{		
				FileOutputStream f = new FileOutputStream(new File(filePath+".object"));
				ObjectOutputStream out = new ObjectOutputStream(f);
		
				for (int i=0;i<shapes.size();i++) {
					out.writeObject(new graphicInfo(shapes.get(i), shapeColor.get(i)));
				}
				for (graphicInfo info: saveInfo) {
					out.writeObject(info);
				}
				out.flush();
				out.close();
				f.close();
				
				switchOn(bttn);
				filePath="";
				
				break;
			}
		}
		
	}
	
	/**
	 * Load objects from user specified file into DrawPanel
	 * 
	 * @param m user mode
	 * @param filePath path to the file
	 * @param bttn Input JButton
	 * @throws IOException if stream to file cannot be written to or closed
	 */
	public void loadShape(String m, String filePath, JButton bttn) throws IOException {
		switch (m) {
			case "LOAD" :{
				FileInputStream f = new FileInputStream(new File(filePath));
				ObjectInputStream in = null;
			
				try {
					in = new ObjectInputStream(f);
					
					shapes.clear();
					shapeColor.clear();
					saveInfo.clear();
				
					while (true) {
						try {
							graphicInfo info = (graphicInfo) in.readObject();
							shapes.add(info.getMyShape());
							shapeColor.add(info.getMyColor());
							
						} catch (ClassNotFoundException e) {
							break;
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					
				} catch (EOFException e) {
					
				}
				catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
						f.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				filePath="";
				repaint();
				switchOn(bttn);
				break;
			}
		}
	}
	
	/**
	 * Export data of shape(s) from DrawFrame into ASCII file 
	 * 
	 * @param m user mode
	 * @param filePath path to the file
	 * @param bttn Input JButton
	 * @throws IOException if stream to file cannot be written to or closed
	 */
	public void exportShape(String m, String filePath, JButton bttn) throws IOException {
		switch (m) {
			case "EXPORT" :{
				FileOutputStream f = new FileOutputStream(new File(filePath+".txt"));
				dout = new DataOutputStream(f);
				
				int r=0;
				int g=0;
				int b=0;
				
				for (Shape shape : shapes) {
					
					if (shapeColor.get(shapes.indexOf(shape))!=null) {
						r = shapeColor.get(shapes.indexOf(shape)).getRed();
						g = shapeColor.get(shapes.indexOf(shape)).getGreen();
						b = shapeColor.get(shapes.indexOf(shape)).getBlue();
					}
					
					else if (shapeColor.get(shapes.indexOf(shape))==null) {
						
					}
					
					if (shape instanceof Line2D.Float) {
						myFile.add( new DrawInfo("line", ((Line2D.Float) shape).getX1(), ((Line2D.Float) shape).getY1(), ((Line2D.Float) shape).getX2(), ((Line2D.Float) shape).getY2(), r, g, b ));
						
					}
					if (shape instanceof Ellipse2D.Float) {
						myFile.add( new DrawInfo("circle", ((Ellipse2D.Float) shape).getX(), ((Ellipse2D.Float) shape).getY(), ((Ellipse2D.Float) shape).getWidth()/2, r, g, b));
						
					}
					if (shape instanceof Polygon) {
						int edge = ((Polygon) shape).npoints;
						
						if (edge==3) {
							int[] X = ((Polygon) shape).xpoints;
							int[] Y = ((Polygon) shape).ypoints;
							
							myFile.add(new DrawInfo("triangle", X[0], Y[0], X[1], Y[1], X[2], Y[2], r, g, b ));
							
						}
						if (edge==4) {
							int[] X = ((Polygon) shape).xpoints;
							int[] Y = ((Polygon) shape).ypoints;
							myFile.add( new DrawInfo("quadrilateral", edge, X[0], Y[0], X[1], Y[1], X[2], Y[2], X[3], Y[3], r, g, b ) );
							
						}
					}
					
					r=0;
					g=0;
					b=0;
					
				}
				
				for (DrawInfo draw : myFile) {
					writeInfo(draw);
					dout.writeBytes("\n");
				}
				
				dout.flush();
				dout.close();
				f.close();
				
				switchOn(bttn);
				filePath="";
				
				break;
			}
		}
	}
	
	/**
	 * Write on file to be exported in ASCII with following format
	 * 
	 * 		line;x1;y1;x2;y2;r;g;b
	 * 		circle;x;y;radius;r;g;b
	 * 		triangle;x1;y1;x2;y2;x3;y3;r;g;b
	 * 		quadrilateral;no_of_sides;x1;y1;x2;y2...xn;yn;r;g;b
	 * 
	 * @param draw DrawInfo object (serialized)
	 * 
	 */
	public void writeInfo(DrawInfo draw)  {
		
		if (draw.getShapename().equals("line")) {
			try {
				dout.writeBytes(draw.getShapename()+";"+draw.getX1()+";"+draw.getY1()+";"+draw.getX2()+";"+draw.getY2()+";"+draw.getR()+";"+draw.getG()+";"+draw.getB());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (draw.getShapename().equals("circle")) {
			try {
				dout.writeBytes(draw.getShapename()+";"+draw.getX1()+";"+draw.getY1()+";"+draw.getRadius()+";"+draw.getR()+";"+draw.getG()+";"+draw.getB());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (draw.getShapename().equals("triangle")) {
			try {
				dout.writeBytes(draw.getShapename()+";"+draw.getX1()+";"+draw.getY1()+";"+draw.getX2()+";"+draw.getY2()+";"+draw.getX3()+";"+draw.getY3()+";"+draw.getR()+";"+draw.getG()+";"+draw.getB());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (draw.getShapename().equals("quadrilateral")) {
			try {
				dout.writeBytes(draw.getShapename()+";"+draw.getNo_of_sides()+";"+draw.getX1()+";"+draw.getY1()+";"+draw.getX2()+";"+draw.getY2()+";"+draw.getX3()+";"+draw.getY3()+";"+draw.getX4()+";"+draw.getY4()+";"+draw.getR()+";"+draw.getG()+";"+draw.getB());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Import data from the ASCII file into DrawFrame
	 * 
	 * @param m user mode
	 * @param filePath path to the file
	 * @param bttn Input JButton
	 * @throws IOException if stream to file cannot be written to or closed
	 * 
	 */
	public void importShape(String m, String filePath, JButton bttn) throws IOException  {
		switch (m) {
			case "IMPORT" :{
					
				FileInputStream f = new FileInputStream(new File(filePath));
				//din = new DataInputStream(f);
				BufferedReader reader = new BufferedReader(new InputStreamReader(f));
				
				StreamTokenizer token = new StreamTokenizer(reader);
				
				String name; 
				String command=null;
				
				ArrayList<Double> point = new ArrayList<Double>();
				ArrayList<Integer> polygonPoint = new ArrayList<Integer>();
				ArrayList<Integer> colorRGB = new ArrayList<Integer>();
				
				shapes.clear(); 
				shapeColor.clear();
				
				while (token.nextToken()!=StreamTokenizer.TT_EOF) {
					
					if (token.ttype == StreamTokenizer.TT_WORD) {
						name = token.sval;
						if (name.equals("line")) {
						 	command=name;
						}
						if (name.equals("circle")) {
						 	command=name;
						}
						if (name.equals("triangle")) {
						 	command=name;
						}
						if (name.equals("quadrilateral")) {
						 	command=name;
						}
					}
					
					if (token.ttype == StreamTokenizer.TT_NUMBER) {
						switch (command) {
							case "line":{
								if (c<4) {
									point.add(token.nval);
									c++;
								}
								else if (c==4 || c==5) {
									colorRGB.add((int) token.nval);
									
									c++;
								}
								else if (c==6) {
									colorRGB.add((int) token.nval);
									Line2D line = new Line2D.Float();
									
									line.setLine(point.get(0), point.get(1), point.get(2), point.get(3));
									shapes.add(line);
									
									Color myColor = checkIfColorNull(colorRGB.get(0), colorRGB.get(1), colorRGB.get(2));
									shapeColor.add(myColor);
									
									//repaint();
									point.clear();
									colorRGB.clear();
									command="";
									c=0;
								}
								break;
							}
							
							case "circle":{
								//Add center point x, y
								if (c<3) {
									point.add(token.nval);
									c++;
								}
								//Add color RGB	
								else if (c==3|| c==4) {
									colorRGB.add((int) token.nval);
									c++;
								}
								//All essential informations fulfilled to draw new circle
								else if (c==5) {
									colorRGB.add((int) token.nval);
									Ellipse2D circle = new Ellipse2D.Float();
									double r = point.get(2);
									
									circle.setFrame(point.get(0), point.get(1), r*2, r*2);
									shapes.add(circle);
									
									Color myColor = checkIfColorNull(colorRGB.get(0), colorRGB.get(1), colorRGB.get(2));
									shapeColor.add(myColor);
									
									//repaint();
									point.clear();
									colorRGB.clear();
									command="";
									c=0;
								}
								
								break;
							}
							
							case "triangle":{
								if (c<6) {
									polygonPoint.add((int) token.nval);
									c++;
								}
								else if (c==6||c==7) {
									colorRGB.add((int) token.nval);
									c++;
								}
								else if (c==8) {
									colorRGB.add((int) token.nval);
									Polygon triangle = new Polygon();
									int tx[] = {polygonPoint.get(0), polygonPoint.get(2), polygonPoint.get(4)};
									int ty[] = {polygonPoint.get(1), polygonPoint.get(3), polygonPoint.get(5)};
							
									triangle = new Polygon(tx, ty, 3);
									shapes.add(triangle);
									
									Color myColor = checkIfColorNull(colorRGB.get(0), colorRGB.get(1), colorRGB.get(2));
									shapeColor.add(myColor);
									
									//repaint();
									polygonPoint.clear();
									colorRGB.clear();
									command="";
									c=0;
								}
								break;
							}
							
							case "quadrilateral":{
								int side=4;
						
								if (c==0) {	
									side = (int) token.nval;
									c++;
								}
								else if (c==1||c==2||c==3||c==4||c==5||c==6||c==7||c==8) {
									polygonPoint.add((int) token.nval);
									c++;
								}
								else if (c==9 || c==10) {
									colorRGB.add((int) token.nval);
									c++;
								}
								else if (c==11) {
									colorRGB.add((int) token.nval);
									
									Polygon quadrilateral = new Polygon();
									
									int px[] = {polygonPoint.get(0), polygonPoint.get(2), polygonPoint.get(4), polygonPoint.get(6)};
									int py[] = {polygonPoint.get(1), polygonPoint.get(3), polygonPoint.get(5), polygonPoint.get(7)};
									
									quadrilateral = new Polygon(px, py, side);
									shapes.add(quadrilateral);
									
									Color myColor = checkIfColorNull(colorRGB.get(0), colorRGB.get(1), colorRGB.get(2));
									shapeColor.add(myColor);
									
									//repaint();
									polygonPoint.clear();
									colorRGB.clear();
									command="";
									c=0;
								}
								break;
							}
						}
					}
				}
				
				reader.close();
				//din.close();
				f.close();
				
				filePath="";
				switchOn(bttn);
				repaint();
				break;
			}
		}
	}
	
	/**
	 * Check if Color (r, g, b) is null or not
	 * 
	 * @param r Red() of Color
	 * @param g Green() of Color
	 * @param b Blue() of Color
	 * @return Color(r, g, b) or null
	 */
	public Color checkIfColorNull(int r, int g, int b) {
		Color color;
		if (r==0 && g==0 && b==0) {
			return null;
		}
		else {
			color = new Color(r, g, b);
			return color;
		}
	}
	
	/**
	 * Handle MouseClick event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Mouse Press listener
	 * Record the mouse move position and used to draw/select graphics
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch (m) {
			case ("L"):{	
				drawGraphic(e);
				break;
			}
			case ("C"):{
				drawGraphic(e);
				break;
			}
			case ("T") :{
				drawGraphic(e);
				break;
			}
			case ("Q") :{
				drawGraphic(e);
				break;
			}
			case ("SELECT"): {
				selectShape(e);
				break;
			}
			case ("MOVE"):{
				moveShape(e);
			}
		}
		repaint();
		
	}
	
	/**
	 * Mouse Release listener
	 * Used to detect appropriate point base for the desired action 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		switch (m) {
			case "MOVE" :{
				int ex = e.getX();
				int ey = e.getY();
				Point2D.Float point = new Point2D.Float(ex, ey);
				end = point;
				dx = (int)(start.getX()-end.getX());
				dy = (int)(start.getY()-end.getY());
				moveShape(dx, dy, bttn);
				repaint();
			}
			break;
		}
		
	}
	
	/**
	 * Handle MouseEntered event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * Handle MouseExcited event
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

}

/**
 * 
 * Class graphicInfo for Serialization of variables
 *
 */
class graphicInfo implements Serializable {
	
	/**
	 * default Serialized UID
	 */
	private static final long serialVersionUID = 1L;
	
	Shape myShape;
	
	/**
	 * Getter of shape
	 */
	Color myColor;

	public Shape getMyShape() {
		return myShape;
	}

	
	/**
	 * Getter of color
	 * @return color
	 */
	public Color getMyColor() {
		return myColor;
	}
	
	/**
	 * Setter of shape
	 * @param myShape shape
	 */
	public void setMyShape(Shape myShape) {
		this.myShape = myShape;
	}

	
	/**
	 * Setter of color
	 * @param myColor color
	 */
	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	/**
	 * Constructor of graphicInfo
	 * 
	 * @param myShape shape
	 * @param myColor color
	 */
	public graphicInfo (Shape myShape, Color myColor) {
		super();
		this.myShape = myShape;
		this.myColor = myColor;
	}
	
	
	/**
	 * Constructor of graphicInfo
	 * 
	 */
	public graphicInfo() {
		super();
	}
	
}


