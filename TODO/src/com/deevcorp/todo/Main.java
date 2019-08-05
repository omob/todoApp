package com.deevcorp.todo;

import java.util.Date;
import java.util.Scanner;

/*
 * A persistent TODO LIST APP program
 */
public class Main {

	public static void main(String[] args) {
		Todo todoList = new Todo(new PersistDB());
		
		Scanner scannerObj = new Scanner(System.in);
		String username = null;
		String task = null;
		
		System.out.println("Enter your username");
		username = scannerObj.nextLine();
		todoList.setUsername(username);
		
		System.out.println("Enter tasks and press 'end' to quit");
		
		while(task != "end") {
			System.out.println("Enter Task: ");
			task = scannerObj.nextLine().trim();
			
			if (task.equalsIgnoreCase("end")) break;
			
			String id = Long.toString(new Date().getTime());
			todoList.addTodo(id, task);
		}

		todoList.getAll().forEach((String id, String tt) -> {
			System.out.printf("%s: %s \n", id, tt);
		});

		todoList.save();
	}

}
