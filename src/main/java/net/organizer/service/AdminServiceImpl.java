package net.organizer.service;

import java.util.Date;
import java.util.List;

import net.organizer.dao.AdminDAO;
import net.organizer.form.Details;
import net.organizer.form.Role;
import net.organizer.form.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Transactional
	public List<Details> listUserToSend(Date dateFrom, Date dateTo) {
		return adminDAO.listUserToSend(dateFrom, dateTo);
	}

	@Transactional
	public List<User> listUser(Integer firstResult, Integer maxResults) {
		return adminDAO.listUser(firstResult, maxResults);
	}

	@Transactional
	public boolean deleteUser(Integer idUser) {
		return adminDAO.deleteUser(idUser);
	}

	@Transactional
	public List<Role> listRoleOfUser(Integer idUser) {
		return adminDAO.listRoleOfUser(idUser);
	}

	@Transactional
	public Details getUserDetails(Integer idUser) {
		return adminDAO.getUserDetails(idUser);
	}

	@Transactional
	public boolean isAdmin(Integer idUser) {
		return adminDAO.isAdmin(idUser);
	}

}
