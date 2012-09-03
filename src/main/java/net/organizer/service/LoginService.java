package net.organizer.service;

import net.organizer.form.Details;
import net.organizer.form.User;

public interface LoginService {

	public User getUser(int id);

	public User getUser(String login);

	public boolean registerUser(String login, String password);
	
	public Details getDetails(int idUser);
	
	public void updateDetails(Details details);

}
