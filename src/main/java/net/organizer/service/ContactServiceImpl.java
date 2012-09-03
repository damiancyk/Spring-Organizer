package net.organizer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.organizer.dao.ContactDAO;
import net.organizer.form.Contact;
import net.organizer.form.User;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;

	@Transactional
	public List<Contact> listContact(User idOwner) {
		return contactDAO.listContact(idOwner);
	}

	@Transactional
	public Contact oneContact(int idOwner, int idPosition) {
		return contactDAO.oneContact(idOwner, idPosition);
	}

	@Transactional
	public List<User> findUser(String login) {
		return contactDAO.findUser(login);
	}

	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);
	}

	@Transactional
	public void removeContact(Integer idContact) {
		contactDAO.removeContact(idContact);
	}

	@Transactional
	public boolean existContact(User idOwner, User idPosition) {
		return contactDAO.existContact(idOwner, idPosition);
	}

}
