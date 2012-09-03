package net.organizer.dao;

import java.util.List;

import net.organizer.form.Action;
import net.organizer.form.Invitation;
import net.organizer.form.Team;
import net.organizer.form.User;
import net.organizer.form.UserTeam;

public interface TeamDAO {

	public List<UserTeam> listUserTeamByUser(User idUser);
	
	public List<Team> listTeamByIdUser(int idUser);
	
	public List<UserTeam> listUserTeamByIdTeam(int idTeam);
	
	public UserTeam oneUserTeamById(int idUserTeam);
	
	public UserTeam oneUserTeam(int idTeam, int idUser);
	
	public List<Action> listActionByIdTeam(int typeAction, int id);
	
	public void addTeam(Team team, User user, boolean isAdmin);
	
	public void deleteTeam(int idTeam);
	
	public boolean sendInvitation(int idUser, int idTeam);
	
	public List<Invitation> listInvitation(int idUser);
	
	public void acceptInvitation(Invitation invitation);
	
	public void rejectInvitation(int idInvitation);
	
	public Team oneTeamById(int idTeam);
	
	public boolean isAdmin(int idUser, int idTeam);
	
	public void quitTeam(int idUser, int idTeam);
	
	public void updateTeam(Team team);
	
	public void setPrivileges(int idTeam, int idUser);
	
}
