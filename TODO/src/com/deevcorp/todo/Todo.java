package com.deevcorp.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
@Inheritance
public class Todo implements Serializable, ITodo{
	
//    @Id @GeneratedValue
//    private Long id;
	
	@Id
	private String username;

	@ElementCollection 
	Map<String, String> todoMap = new TreeMap<String, String>();
	
	@Embedded
	IPersistDB persistDB;

	
	public Todo() { }
	
	public Todo(IPersistDB persistDB) {
		this.persistDB = persistDB;
	}

	@Override
	public String getTodo(String id) {
		return todoMap.get(id);
	}

	@Override
	public void addTodo(String todoId, String todo) {
		todoMap.put(todoId, todo);
	}

	@Override
	public void delete(Todo todo) {
	}

	@Override
	public Map<String, String> getAll() {
		return this.todoMap;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username.toLowerCase().trim();
	}

	@Override
	public Todo fetchTodo(String username) {
		Todo todo = this.persistDB.fetch(this, username);
		if (todo == null) {
			throw new NullPointerException("Username does not exist");
		}
		
		return todo;
	}
	
	public void save() {	
		this.persistDB.saveToDB(this);
		System.out.println("Saved to DB");
	}

	public void fetchAll() {
		this.persistDB.fetchAll(this).forEach(todo -> {
			todo.displayInfo();
		});
		System.out.println("Fetched");
	}
}
