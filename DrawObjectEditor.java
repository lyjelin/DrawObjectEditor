//package ass6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Public class DrawFrame controlling the program
 * DrawFrame class inheriting JFrame
 * Contains editor panel where graphic(s) are drawn
 * 
 * COMP2396 Assignment_6
 * 
 * @author Lee Yoon Jeong
 * @since 2020-04
 *
 */

public class DrawObjectEditor extends JFrame {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Declaring static , private, public variables 
	 */
	private DrawPanel drawPane;
	private JButton[] B = new JButton[13];
	private String label = null;
	private String filePath = "";
	private JFileChooser fileChooserForSave = new JFileChooser();
	private JFileChooser fileChooserForExport = new JFileChooser();
	
	/**
	 * Main : Declares and create new instance of DrawFrame
	 * 
	 * @param args an array of command-line arguments for the application
	 */
	public static void main (String[] args) {
		DrawObjectEditor d = new DrawObjectEditor();
		d.editorFrame(); // set up gui for program
	}
	
	/**
	 * Base GUI for DrawObjectEditor program
	 */
	public void editorFrame() {
	
		setTitle("Draw Object Editor");
		setVisible(true);
		setSize(400, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawPane = new DrawPanel();
		drawPane.setSize(400, 450);
		
		/*
	     * JButtons for function
	     */
		JPanel buttonHolder = new JPanel();
		GridLayout G = new GridLayout(4, 4);
		
		B[0] = new JButton("Line");
		B[0].addActionListener(new lineListener());
		B[1] = new JButton("Circle");
		B[1].addActionListener(new circleListener());
		B[2] = new JButton("Triangle");
		B[2].addActionListener(new triangleListener());
		B[3] = new JButton("Quadrilateral");
		B[3].addActionListener(new quadrilateral());
		B[4] = new JButton("Select");
		B[4].addActionListener(new selector());
		B[5] = new JButton("Move");
		B[5].addActionListener(new moveListener());
		B[6] = new JButton("Delete");
		B[6].addActionListener(new deleteListener());
		B[7] = new JButton("Copy");
		B[7].addActionListener(new copyListener());
		B[8] = new JButton("Random Color");
		B[8].addActionListener(new randomColorListener());
		B[9] = new JButton("Save");
		B[9].addActionListener(new saveListener());
		B[10] = new JButton("Load");
		B[10].addActionListener(new loadListener());
		B[11] = new JButton("Export");
		B[11].addActionListener(new exportListener());
		B[12] = new JButton("Import");
		B[12].addActionListener(new importListener());
		
		for (JButton button:B) {
			button.setOpaque(true);
			buttonHolder.add(button);
		}
		
		//Initially mova/delete/copy/random color buttons switched off
		B[5].setEnabled(false);
		B[6].setEnabled(false);
		B[7].setEnabled(false);
		B[8].setEnabled(false);
		
		buttonHolder.setLayout(G);
		
		getContentPane().add(buttonHolder, BorderLayout.SOUTH);
		getContentPane().add(drawPane, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * 
	 * LineListener class handling events when Line button is clicked
	 *
	 */
	class lineListener implements ActionListener {
		/**
		 * Starts drawing line by user clicking on the drawing panel
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[0]);
			label ="L";
			drawPane.userOperation(label, B[0]);
		}
		
	}
	/**
	 * 
	 * CircleListener class handling events when Circle button is clicked
	 *
	 */
	class circleListener implements ActionListener {
		/**
		 * Starts drawing circle by user clicking on the drawing panel
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[1]);
			label ="C";
			drawPane.userOperation(label, B[1]);
			
		}
		
	}
	/**
	 * 
	 * TriangleListener class handling events when Triangle button is clicked
	 *
	 */
	class triangleListener implements ActionListener {
		/**
		 * Starts drawing triangle by user clicking on the drawing panel
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[2]);
			label ="T";
			drawPane.userOperation(label, B[2]);
		}
		
	}
	/**
	 * 
	 * Quadrilateral class handling events when Quadrilateral button is clicked
	 *
	 */
	class quadrilateral implements ActionListener {
		/**
		 * Starts drawing quadrilateral by user clicking on the drawing panel
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[3]);
			label ="Q";
			drawPane.userOperation(label, B[3]);
		}
		
	}
	/**
	 * 
	 * SelectorListener class handling events when Select button is clicked
	 *
	 */
	class selector implements ActionListener {
		/**
		 * Selects shape
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			label ="SELECT";
			drawPane.userOperation(label, B[4]);
			drawPane.switchOff(B[4]);
			
			B[5].setEnabled(true);
			B[6].setEnabled(true);
			B[7].setEnabled(true);
			B[8].setEnabled(true);
			
		}
		
	}
	/**
	 * 
	 * MoveListener class handling events when Move button is clicked
	 *
	 */
	class moveListener implements ActionListener {
		/**
		 * Move selected shape
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOn(B[4]);
			drawPane.switchOff(B[5]);
			label ="MOVE";
			drawPane.userOperation(label, B[5]);
		}
		
	}
	/**
	 * 
	 * DeleteListener class handling events when Delete button is clicked
	 *
	 */
	class deleteListener implements ActionListener {
		/**
		 * Delete selected shape
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOn(B[4]);
			drawPane.switchOff(B[6]);
			label ="DELETE";
			drawPane.userOperation(label, B[6]);
		}
		
	}
	/**
	 * 
	 * CopyListener class handling events when Copy button is clicked
	 *
	 */
	class copyListener implements ActionListener {
		/**
		 * Copy selected shape
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOn(B[4]);
			drawPane.switchOff(B[7]);
			label ="COPY";
			drawPane.userOperation(label, B[7]);
		}
		
	}
	/**
	 * 
	 * RandomListener class handling events when Random Color button is clicked
	 *
	 */
	class randomColorListener implements ActionListener {
		/**
		 * Randomly color selected shape
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOn(B[4]);
			drawPane.switchOff(B[8]);
			label ="RANDOMCOLOR";
			drawPane.userOperation(label, B[8]);
		}
		
	}
	/**
	 * 
	 * SaveListener class handling events when Save button is clicked
	 *
	 */
	class saveListener implements ActionListener {
		/**
		 * Save current shape(s) on DrawPanel in file(.object)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[9]);
			fileChooserForSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int check =  fileChooserForSave.showSaveDialog(null);
			
			if (check == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooserForSave.getSelectedFile().toString();
			}
			
			if (!filePath.equals("")) {
				try {
					label = "SAVE";
					drawPane.saveShape(label, filePath, B[9]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	/**
	 * 
	 * LoadListener class handling events when Load button is clicked
	 *
	 */
	class loadListener implements ActionListener {
		/**
		 * Bring file with extension of (.object) selected by user and load 
		 * shape(s) from selected file
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			drawPane.switchOff(B[10]);
			int check = fileChooserForSave.showOpenDialog(null);
			
			if (check == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooserForSave.getSelectedFile().toString();
			}
			
			if (!filePath.equals("")) {
				try {
					label = "LOAD";
					drawPane.loadShape(label, filePath, B[10]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		}
		
	}
	/**
	 * 
	 * ExportListener class handling events when Export button is clicked
	 *
	 */
	class exportListener implements ActionListener {
		/**
		 * Export objects in the DrawFrame to an ASCII file with extension .txt
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[11]);
			fileChooserForExport.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int check =  fileChooserForExport.showSaveDialog(null);
			
			if (check == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooserForExport.getSelectedFile().toString();
			}
			
			if (!filePath.equals(""))
				try {
					label = "EXPORT";
					drawPane.exportShape(label, filePath, B[11]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		
	}
	
	/**
	 * 
	 * ImportListener class handling events when Import button is clicked
	 *
	 */
	class importListener implements ActionListener {
		/**
		 * When the user clicks the Import button, objects in the 
		 * exported ASCII file are imported into the DrawFrame
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			drawPane.switchOff(B[12]);
			fileChooserForExport.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int check =  fileChooserForExport.showOpenDialog(null);
			
			if (check == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooserForExport.getSelectedFile().toString();
			}
			
			if (!filePath.equals("")) {
				try {
					label = "IMPORT";
					drawPane.importShape(label, filePath, B[12]);
				} catch (IOException e1) {
					//e1.printStackTrace();
				B[12].setEnabled(true);
				}
			}
		}
	}

}
