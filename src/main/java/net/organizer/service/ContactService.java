package net.organizer.service;

import java.util.List;

import net.organizer.form.Contact;
import net.organizer.form.User;

public interface ContactService {

	public List<Contact> listContact(User idOwner);
	
	public Contact oneContact(int idOwner, int idPosition);

	public List<User> findUser(String login);

	public void addContact(Contact contact);

	public void removeContact(Integer idContact);
	
	public boolean existContact(User idOwner, User idPosition);

}