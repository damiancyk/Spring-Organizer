package net.organizer.dao;

import java.util.Date;
import java.util.List;

import net.organizer.form.Details;
import net.organizer.form.Role;
import net.organizer.form.User;

public interface AdminDAO {

	public List<Details> listUserToSend(Date dateFrom, Date dateTo);
	
	public List<User> listUser(Integer firstResult, Integer maxResults);
	
	public boolean deleteUser(Integer idUser);
	
	public List<Role> listRoleOfUser(Integer idUser);
	
	public Details getUserDetails(Integer idUser);
	
	public boolean isAdmin(Integer idUser);
	
}
