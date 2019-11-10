package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 * 
	 * When add task is clicked:
	 * 		ask the user for a task and save it to an array list
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list.
	 */
	
	JFrame frame;
	JPanel panel;
	JButton addTask, viewTasks, removeTask, saveList, loadList;
	ArrayList<String> tasks = new ArrayList<String>();
	
	public static void main(String[] args) {
		ToDoList list = new ToDoList();
	}
	
	public ToDoList() {
		frame = new JFrame();
		panel = new JPanel();
		addTask = new JButton("Add Task");
		viewTasks = new JButton("View Tasks");
		removeTask = new JButton("Remove Task");
		saveList = new JButton("Save List");
		loadList = new JButton("Load List");
		
		frame.add(panel);
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		panel.add(loadList);
		
		addTask.addActionListener(this);
		viewTasks.addActionListener(this);
		removeTask.addActionListener(this);
		saveList.addActionListener(this);
		loadList.addActionListener(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("src/_03_To_Do_List/savedList.txt"));
			
			String line = bReader.readLine();
			while(line != null){
				tasks.add(line);
				line = bReader.readLine();
			}
			
			bReader.close();
		} catch (FileNotFoundException ee) {
			JOptionPane.showMessageDialog(null, "Sorry, load file is missing.");
		} catch (IOException eee) {
			JOptionPane.showMessageDialog(null, "Sorry, something went wrong while loading.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		
		if(b.equals(addTask)) {
			String newTask = JOptionPane.showInputDialog("Add a task: ");
			if(newTask.length() > 0) tasks.add(newTask);
		}
		/*==================================================================*/
		else if(b.equals(viewTasks)) {
			if(tasks.size() > 0) {
				String taskList = "";
				for(int i = 0; i < tasks.size(); i++) {
					taskList += (i+1) + ": " + tasks.get(i) +  "\n";
				}
				JOptionPane.showMessageDialog(null, "Here is your list:\n" + taskList);
			}
			else JOptionPane.showMessageDialog(null, "You have no tasks.");
		}
		/*==================================================================*/
		else if(b.equals(removeTask)) {
			String remove = JOptionPane.showInputDialog("Which task would you like to remove?");
			if(!remove.equals("")) {
				for(int i = 0; i < tasks.size(); i++) {
					if(remove.equalsIgnoreCase(tasks.get(i))) {
						tasks.remove(i);
						JOptionPane.showMessageDialog(null, "Success! \"" + remove + "\" has been removed.");
						break;
					}
					else if(i == tasks.size()-1) JOptionPane.showMessageDialog(null, "Sorry, that task doesn't appear to exist. Please try again. \n (Possibly check spelling)");
				}
			}
		}
		/*==================================================================*/
		else if(b.equals(saveList)) {
			String listLoc = JOptionPane.showInputDialog("Please enter file location: \n (default save/load location: src/_03_To_Do_List/savedList.txt)");
			String save;
			
			try {
				FileWriter writer = new FileWriter(listLoc);
				writer.write("");
				
				writer.close();
			
				for(int i = 0; i < tasks.size(); i++) {
					save = tasks.get(i) + "\n";
					
					try {
						FileWriter writer2 = new FileWriter(listLoc, true);
						writer2.append(save);
						
						writer2.close();
					}
					catch(IOException c) {
						JOptionPane.showMessageDialog(null, "Sorry, something went wrong while saving.");
						break;
					}
					
					if(i == tasks.size()-1) JOptionPane.showMessageDialog(null, "Success! Your list was saved!");
				}
			}
			catch(IOException ee) {
				JOptionPane.showMessageDialog(null, "Sorry, something went wrong while saving.");
			}
		}
		/*==================================================================*/
		else if(b.equals(loadList)) {
			String listLoc = JOptionPane.showInputDialog("Please enter file location: \n (default save/load location: src/_03_To_Do_List/savedList.txt)");
			
			tasks.clear();
			
			try {
				BufferedReader bReader = new BufferedReader(new FileReader(listLoc));
				
				String line = bReader.readLine();
				while(line != null){
					tasks.add(line);
					line = bReader.readLine();
				}
				
				bReader.close();
				
				JOptionPane.showMessageDialog(null, "Success! Your list has been loaded!");
			} catch (FileNotFoundException ee) {
				JOptionPane.showMessageDialog(null, "Sorry, that file could not be found. Please try again");
			} catch (IOException eee) {
				JOptionPane.showMessageDialog(null, "Sorry, something went wrong while loading.");
			}
		}
	}
}
