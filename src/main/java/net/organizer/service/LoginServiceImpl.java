package net.organizer.service;

import net.organizer.dao.LoginDAO;
import net.organizer.form.Details;
import net.organizer.form.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Transactional
	public User getUser(int id) {
		return loginDAO.getUser(id);
	}

	@Transactional
	public User getUser(String login) {
		return loginDAO.getUser(login);
	}

	@Transactional
	public boolean registerUser(String login, String password) {
		return loginDAO.registerUser(login, password);
	}

	@Transactional
	public Details getDetails(int idUser) {
		return loginDAO.getDetails(idUser);
	}

	@Transactional
	public void updateDetails(Details details) {
		loginDAO.updateDetails(details);
	}
}