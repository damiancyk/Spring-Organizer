package net.organizer.dao;

import java.util.List;

import net.organizer.form.Contact;
import net.organizer.form.User;

public interface ContactDAO {

	public List<Contact> listContact(User idOwner);
	
	public Contact oneContact(int idOwner, int idPosition);

	public List<User> findUser(String login);

	public void addContact(Contact contact);

	public boolean existContact(User idOwner, User idPosition);
	
	public void removeContact(Integer idContact);

}