package com.deevcorp.todo;

import java.util.List;
import java.util.Map;

public interface IPersistDB {
	void saveToDB(ITodo todos);
	List<Todo> fetchAll(ITodo todo);
	
	Todo fetch(ITodo todo, String username);

}
