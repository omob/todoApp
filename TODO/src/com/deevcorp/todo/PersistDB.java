package com.deevcorp.todo;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class PersistDB implements IPersistDB{
	private Session _session = null;
	private Transaction _tx = null;
	
	public PersistDB() {
		 // Creating configuration object
      Configuration cfg = new Configuration();
      cfg.configure("hibernate.cfg.xml"); // populates the data of the configuration file

      this._session= cfg.buildSessionFactory().openSession();
      this._tx = _session.beginTransaction();
      
	}

	@Override
	public void saveToDB(ITodo todos) {
		try {
			this._session.save(todos);
			this._tx.commit();
			
		} catch (HibernateException e) {
			if (this._tx != null) {
				this._tx.rollback();
			}
			e.printStackTrace();
		} finally {
			this._session.close();			
		}		
	}
	
	public Todo fetch(ITodo todo, String username) {
		Todo _todo = null;
		try {
			_todo = (Todo) this._session.get(todo.getClass(), username);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} 
		
		return _todo;
	}

	public List<Todo> fetchAll(ITodo todo) {
		List<Todo> obj = null;
		try {
			 obj = (List<Todo>) this._session.createQuery(
					"from Todo", 
					todo.getClass()
				).list();
			
			this._tx.commit();
		} catch (Exception e) {
			System.out.println("Error: " +  e.getMessage());
		} 
		
		return obj;
	}

}
