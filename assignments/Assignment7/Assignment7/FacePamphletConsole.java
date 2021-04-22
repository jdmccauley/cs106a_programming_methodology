/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Milestone 1: Make the interactors do things.
 * West: Status, Picture, Friend
 * North: Name, Add, Delete, Lookup
 */

public class FacePamphletConsole extends ConsoleProgram 
					implements FacePamphletConstants {
	/**
	 * Temp main, written by Josh.
	 */
	public static void main(String[] args) {
		new FacePamphletConsole().start(args);	
	}

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		initInteractors();
		addActionListeners();
    }
	
	/**
	 * Makes the interactors and adds them to the application.
	 */
	
	private void initInteractors() {
		// Initialize interactors.
		// West
		add(statusField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(changeStatus = new JButton("Change Status"), WEST);
		add(pictureField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(changePicture = new JButton("Change Picture"), WEST);
		add(friendField = new JTextField(TEXT_FIELD_SIZE), WEST);
		add(addFriend = new JButton("Add Friend"), WEST);
		// North
		add(nameLabel = new JLabel("Name"), NORTH);
		add(nameField = new JTextField(TEXT_FIELD_SIZE), NORTH);
		add(addName = new JButton("Add"), NORTH);
		add(deleteName = new JButton("Delete"), NORTH);
		add(lookupName = new JButton("Lookup"), NORTH);
		
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd = e.getActionCommand();
    	if (cmd == "Change Status") this.changeStatus();
    	if (cmd == "Change Picture") this.changePicture();
    	if (cmd == "Add Friend") this.addFriend();
    	if (cmd == "Add") this.addName();
    	if (cmd == "Delete") this.deleteName();
    	if (cmd == "Lookup") this.lookupName();
	}
    
    /**
     * Changes status for the current profile.
     */
    private void changeStatus() {
    	String status = this.statusField.getText();
    	println("Change Status: " + status);
    }
    
    /**
     * Changes the picture for the current profile.
     */
    private void changePicture() {
    	String picture = this.pictureField.getText();
    	println("Change picture: " + picture);
    }
    
    /**
     * Adds a friend to the current profile.
     */
    private void addFriend() {
    	String friend = this.friendField.getText();
    	println("Add Friend: " + friend);
    }
    
    /**
     * Adds a profile for the given name.
     */
    private void addName() {
    	String name = this.nameField.getText();
    	println("Add: " + name);
    }
    
    /**
     * Deletes a profile of the given name.
     */
    private void deleteName() {
    	String name = this.nameField.getText();
    	println("Delete: " + name);
    }
    
    /**
     * Looks up a profile for the given name.
     */
    private void lookupName() {
    	String name = this.nameField.getText();
    	println("Lookup: " + name);
    }
    
    /* Instance variables. */
    // West
    private JTextField statusField;
    private JButton changeStatus;
    private JTextField pictureField;
    private JButton changePicture;
    private JTextField friendField;
    private JButton addFriend;
    // North
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton addName;
    private JButton deleteName;
    private JButton lookupName;

}
