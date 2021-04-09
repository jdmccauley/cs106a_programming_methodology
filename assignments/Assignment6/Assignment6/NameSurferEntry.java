/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		// The line is read from a reader.
		//Assume it has the name and the values.
		findSpaces(line);
		this.name = line.substring(0, spaceLocations[0]);
		fillYears(line);		
	}
	
	
	/* Method: findSpaces() */
	/** 
	 * Finds and assigns indices of space characters in a String to the
	 * instance variable array spaceLocations in order of appearance.
	 * @param line: String representing Name data.
	 */
	private void findSpaces(String line) {
		// Initialize variables.
		spaceLocations = new int[N_SPACES];
		int position = 0;
		char space = ' ';
		for (int i = 0; i < spaceLocations.length; i++) {
			position = line.indexOf(space, position);
			spaceLocations[i] = position;
			position++;
		}
	}
	
	
	/* Method: fillYears() */
	/**
	 * Fills a HashMap of year keys with year data.
	 * @param line: String of data.
	 */
	private void fillYears(String line) {
		ranks = new HashMap<Integer, Integer>();
		String yearString;
		int yearInt;
		for (int i = 0; i < spaceLocations.length; i++) {
			// Allow for last i to get end of line.
			if (i < spaceLocations.length - 1) {
				yearString = line.substring(
					// Start at spaceLocations[i] + 1 to avoid space.
					spaceLocations[i] + 1, spaceLocations[i + 1]
				);
			} else {
				yearString = line.substring(spaceLocations[i] + 1);
			}
			yearInt = Integer.parseInt(yearString);
			ranks.put(YEARS[i], yearInt);
		}
	}

	
	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 * @return name: String representing the name for the entry.
	 */
	public String getName() {
		return this.name;
	}
	

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		return this.ranks.get(YEARS[decade]);
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String total = "";
		total += this.name + " [";
		for (int i = 0; i < YEARS.length; i++) {
			total += Integer.toString(ranks.get(YEARS[i]));
			if (i < YEARS.length - 1) {
				total += " ";
			} else {
				total += "]";
			}
		}
		return total;
	}
	
	/* Instance variables. */
	private String name;
	private int[] spaceLocations;
	private HashMap<Integer, Integer> ranks;
	
	// There are 12 values per name: 1 name and 11 values
	// This has 11 spaces then
	
	/* Constants. */
	private static final int N_SPACES = 11;
	private static final int[] YEARS = {
		1900, 1910, 1920, 1930, 1940, 1950,
		1960, 1970, 1980, 1990, 2000
	};
	
}

