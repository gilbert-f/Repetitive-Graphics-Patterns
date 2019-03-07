import java.awt.Color;
import java.util.*;
import java.awt.Point;

import uwcse.graphics.*;

import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 * Gilbert Febrianto
 */

public class GraphicsElements { 

	/** Maximum number of wedges in a pie */
	public static final int MAXIMUM_NUMBER_OF_PIE_WEDGES = 100;

	/** Maximum number of stripes of one color in the pattern of stripes */
	public static final int MAXIMUM_NUMBER_OF_STRIPES = 15;

	/** Maximum number of divisions in a Koch snow flake */
	public static final int MAXIMUM_NUMBER_OF_DIVISIONS = 5;

	/**
	 * Create the view of a pie Use filled arcs. The color of each arc is
	 * random. The pie should fill the window. Store the arcs in an ArrayList
	 * and return that ArrayList. The number of pie wedges (= arcs) is given by
	 * the user (use a dialog box). If that number is less than or equal to 0 or
	 * greater than MAXIMUM_NUMBER_OF_PIE_WEDGES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again. Make sure that no gap
	 * appears in the pie when drawing it.
	 */
	public ArrayList<Arc> createAPie() {
		// Initialize ArrayList<Arc> graphicsList
		ArrayList<Arc> graphicsList = new ArrayList<Arc>();
		
		// Ask for user input for the number of wedges
		int numOfWedges = inputInteger("arcs", MAXIMUM_NUMBER_OF_PIE_WEDGES, 1);
		
		// Let into the statement if numOfWedges >= 1 and numOfWedges <= MAXIMUM_NUMBER_OF_PIE_WEDGES
		if (numOfWedges >= 1 && numOfWedges <= MAXIMUM_NUMBER_OF_PIE_WEDGES) {
			// Initialize the angle of the arc
			int arcAngle = 360 / numOfWedges;
			int initialAngle = 0;
			int leftAngle = 360 % numOfWedges;
			int counter = 0;
			
			// For loop that executes numOfWedges times
			for (int i = 1; i <= numOfWedges; i++) {
				Color c = new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
						(int)(Math.random() * 256));
				
				// Execute the actions if counter < leftAngle
				if (counter < leftAngle) {
					// Add arc to graphicsList
					Arc arc = new Arc(50, 0, 300, 300, initialAngle, arcAngle + 1, c, true);
					graphicsList.add(arc);
					
					// Increment counter and initialAngle
					counter++;
					initialAngle++;

				} else {
					// Add arc to graphicsList
					Arc arc = new Arc(50, 0, 300, 300, initialAngle, arcAngle, c, true);
					graphicsList.add(arc);
				}
				
				// Increment initialAngle by arcAngle
				initialAngle += arcAngle;
			}
		}
		
		// Return graphicsList
		return graphicsList;
	}

	/**
	 * Create a pattern of stripes as shown in the homework instructions. Store
	 * the triangles in an ArrayList and return that ArrayList. Use two colors
	 * only to paint the triangles. The pattern should cover most of the window.
	 * The number of stripes (of one color) is given by the user (use a dialog
	 * box). If that number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_STRIPES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Triangle> createStripes() {
		// Initialize ArrayList<Triangle> graphicsList
		ArrayList<Triangle> graphicsList = new ArrayList<Triangle>();
		
		// Ask user for the number of stripes
		int numOfStripes = inputInteger("stripes", MAXIMUM_NUMBER_OF_STRIPES, 1);
		
		// Executes the actions if numOfStripes >= 1 and numOfStripes <= MAXIMUM_NUMBER_OF_STRIPES
		if (numOfStripes >= 1 && numOfStripes <= MAXIMUM_NUMBER_OF_STRIPES) {
			int height = 300 / numOfStripes;
			
			// Define the colors for the triangles
			Color c1 = new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
					(int)(Math.random() * 256));
			Color c2 = new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
					(int)(Math.random() * 256));
			
			// For loop that executes numOfStripes times
			for (int i = 0; i < numOfStripes; i++) {
				// For loop that executes numOfStripes times
				for (int j = 0; j < numOfStripes; j++) {
					int x = j * height + 50;
					int y = i * height;
					
					// Define the triangles
					Triangle t1 = new Triangle(x, y, x + height, y, x + height, y + height, c1,
							true);
					Triangle t2 = new Triangle(x, y, x, y + height, x + height, y + height, c2,
							true);
					
					// If counter % 2 == 0, switch the color of the triangles
					if ((i + j) % 2 == 0) {
						t1.setColor(c2);
						t2.setColor(c1);
					}
					
					// Add the triangles to the graphicsList
					graphicsList.add(t1);
					graphicsList.add(t2);
				}
			}
		}
		
		// Return graphicsList
		return graphicsList;
	}

	/**
	 * Create a Koch snow flake. Use the class java.awt.Point. Store the Points
	 * in an ArrayList and return that ArrayList. Note that you can't set the
	 * color of a point. The initial color of the lines making up the snow flake
	 * is black. But, you can change the color in the method
	 * changeColorOfSnowFlake below. The snow flake should cover most of the
	 * window, and be always visible. The number of divisions of the initial
	 * triangle is given by the user (use a dialog box). If that number is less
	 * than 0 or greater than MAXIMUM_NUMBER_OF_DIVISIONS, display an error
	 * message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Point> createASnowFlake() {
		// Initialize ArrayList<Point> graphicsList
		ArrayList<Point> graphicsList = new ArrayList<Point>();
		
		// Ask for user input of number of divisions
		int numOfDivisions = inputInteger("divisions", MAXIMUM_NUMBER_OF_DIVISIONS, 0);
		
		// If numOfDivisions >= 0 and numOfDivisions <= MAXIMUM_NUMBER_OF_DIVISIONS
		// execute the actions
		if (numOfDivisions >= 0 && numOfDivisions <= MAXIMUM_NUMBER_OF_DIVISIONS) {
			graphicsList.add(new Point(100, 50));
			graphicsList.add(new Point(300, 150));
			graphicsList.add(new Point(100, 250));
			
			// For loop that executes numOfDivisions times
			for (int j = 0; j < numOfDivisions; j++) {
				int lastIndex = graphicsList.size() - 1;
				
				// For loop that executes actions
				for (int i = 0; i <= lastIndex; i += 4) {
					// Define p by assigning it to the ith element 
					Point p = graphicsList.get(i);
					// Define q by assigning it to the ((i + 1) % graphicsList.size())th element
					Point q = graphicsList.get((i + 1) % graphicsList.size());
					
					// Define the additonal points
					Point a = new Point((int) (p.x + (q.x - p.x) / 3.0),
							(int) (p.y + (q.y - p.y) / 3.0));
					Point c = new Point((int) (p.x + 2 * (q.x - p.x) / 3.0),
							(int) (p.y + 2 * (q.y - p.y) / 3.0));
					Point b = new Point();
					b.x = (int) (a.x + (c.x - a.x) * Math.cos(Math.PI / 3.0) + (c.y - a.y)
							* Math.sin(Math.PI / 3.0));
					b.y = (int) (a.y - (c.x - a.x) * Math.sin(Math.PI / 3.0) + (c.y - a.y)
							* Math.cos(Math.PI / 3.0));

					// Add those points to the graphicsList respectively to their index
					graphicsList.add(i + 1, a);
					graphicsList.add(i + 2, b);
					graphicsList.add(i + 3, c);
					
					// Increment lastIndex by 3
					lastIndex += 3;
				}
			}
		}
		
		// Return graphicsList
		return graphicsList;
	}

	/**
	 * Rotate the colors in the pie, in a clockwise direction. Precondition:
	 * graphicsList describes a pie Return the updated ArrayList
	 */
	public ArrayList<Arc> rotateColorsInPie(ArrayList<Arc> graphicsList) {
		// For loop that executes graphicsList.size() - 1 times
		for (int i = graphicsList.size() - 1; i > 0; i--) {
			// Get the color of the arcs
			Color c1 = graphicsList.get(i).getColor();
			Color c2 = graphicsList.get(0).getColor();
			
			// Switch the color of the arcs 
			graphicsList.get(i).setColor(c2);
			graphicsList.get(0).setColor(c1);
		}
		
		// Return graphicsList
		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the pattern of stripes Precondition: graphicsList
	 * describes a pattern of stripes Return the updated ArrayList
	 */
	public ArrayList<Triangle> flipColorsInStripes(ArrayList<Triangle> graphicsList) {
		Color c1 = graphicsList.get(0).getColor();
		Color c2 = graphicsList.get(1).getColor();
		
		// For loop that executes graphicsList.size() times
		for (int i = 0; i < graphicsList.size(); i++) {
			// Switch the color of the arcs
			if (c1.equals(graphicsList.get(i).getColor())) {
				graphicsList.get(i).setColor(c2);
			} else {
				graphicsList.get(i).setColor(c1);
			}
		}
		
		// Returns graphicsLists
		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake (select a new random color) Use
	 * the Random class (in java.util) to select the new random color. The color
	 * that you create and return in this method will automatically be assigned
	 * to the snow flake.
	 */
	public Color changeColorOfSnowFlake() {
		// Create random color
		Color color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256),
				(int)(Math.random() * 256));
		
		// Return color
		return color;
	}
	
	public static int inputInteger(String type, final int  max, final int min) {
		String errorMessage = "";
		int value = -1;
		// Execute the loop at least once
		do {
		    // Show input dialog with current error message, if any
		    String stringInput = JOptionPane.showInputDialog(errorMessage + 
		    		"Enter the number of " + type + " (between " + min + " and "
					+ max + ")");
		   
		    // If the stringInput has a value, go into the statement
		    if (stringInput != null ) {
		    	// Accept only valid int within the range of int
		    	try {
		    		// Parse the stringInput into int
			        value = Integer.parseInt(stringInput);
			       
			        // If value is greater than max or less than min,
			        // go into the statement
			        if (value > max || value < min) {
			        	// Change the display of errorMessageMonth
			        	errorMessage = "That integer is not within the \n" + "allowed range!\n";
			        }
			        
			        else {
			        	// Change the display of errorMessage to null,
			        	// so the loop closes
			            errorMessage = ""; // no more error
			        }
			    }
		    	
		    	// If value is an invalid int, go into the statement
		    	catch (NumberFormatException e) {
		    		// The typed text was not an integer
			        errorMessage = "The text you typed is not an integer \n"
			        		+ "or you have not input anything.\n";
			    }
		    }
		    
		    // Closes the loop if cancel is pressed
		    else errorMessage = "";
		  // Execute the loop as long as the errorMessage is not empty
		} while (!errorMessage.isEmpty());
		
		// Return value
		return value;
	}
}
