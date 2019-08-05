package com.deevcorp.todo;

import java.util.List;
import java.util.Map;

public interface ITodo {
	String getTodo(String id);	
	void delete(Todo todo);
	
	Map<String, String> getAll();
	void addTodo(String todoId, String todo);
	
	String getUsername();
	
	Todo fetchTodo(String username);
	
	default void displayInfo() {
		System.out.printf("%s to-do list: \n", this.getUsername().toUpperCase());
		this.getAll().forEach((String id, String todo) -> System.out.println(todo));
	}
}
