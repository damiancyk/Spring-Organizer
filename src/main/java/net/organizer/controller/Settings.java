package net.organizer.controller;

import org.springframework.stereotype.Component;

@Component
public class Settings {
	public User user = new User();
	public Admin admin = new Admin();
	public Note note = new Note();

	public Settings() {
		admin.login="admin";
		admin.email="organizerdamiana@gmail.com";
		admin.hashEmail="0cab59af6633c8d8338d04c8430c0268";
		
		note.order = "year,month,day,hour,minute,second";
		note.firstResult = 0;
		note.maxResults = 10;
	}

	public class Admin {
		public int id;
		public String login;
		public String email;
		public String hashEmail;

		public Admin() {

		}
	}

	public class User {
		public int id;
		public String login;
		public String firstname;
		public String lastname;
		public String hashEmail;

		public User() {

		}
	}

	public class Note {
		public String order;
		public int firstResult;
		public int maxResults;

		public Note() {

		}
	}

}
