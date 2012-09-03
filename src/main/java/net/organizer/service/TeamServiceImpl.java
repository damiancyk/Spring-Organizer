package net.organizer.service;

import java.util.List;

import net.organizer.dao.TeamDAO;
import net.organizer.form.Action;
import net.organizer.form.Invitation;
import net.organizer.form.Team;
import net.organizer.form.User;
import net.organizer.form.UserTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDAO teamDAO;

	@Transactional
	public List<UserTeam> listUserTeamByUser(User idUser) {
		return teamDAO.listUserTeamByUser(idUser);
	}

	@Transactional
	public List<Team> listTeamByIdUser(int idUser) {
		return teamDAO.listTeamByIdUser(idUser);
	}

	@Transactional
	public List<UserTeam> listUserTeamByIdTeam(int idTeam) {
		return teamDAO.listUserTeamByIdTeam(idTeam);
	}

	@Transactional
	public UserTeam oneUserTeam(int idTeam, int idUser) {
		return teamDAO.oneUserTeam(idTeam, idUser);
	}

	@Transactional
	public UserTeam oneUserTeamById(int idUserTeam) {
		return teamDAO.oneUserTeamById(idUserTeam);
	}

	@Transactional
	public List<Action> listActionByIdTeam(int typeAction, int id) {
		return teamDAO.listActionByIdTeam(typeAction, id);
	}

	@Transactional
	public void addTeam(Team team, User user, boolean isAdmin) {
		teamDAO.addTeam(team, user, isAdmin);
	}

	@Transactional
	public void deleteTeam(int idTeam) {
		teamDAO.deleteTeam(idTeam);
	}

	@Transactional
	public boolean sendInvitation(int idUser, int idTeam) {
		return teamDAO.sendInvitation(idUser, idTeam);
	}

	@Transactional
	public List<Invitation> listInvitation(int idUser) {
		return teamDAO.listInvitation(idUser);
	}

	@Transactional
	public void acceptInvitation(Invitation invitation) {
		teamDAO.acceptInvitation(invitation);
	}

	@Transactional
	public void rejectInvitation(int idInvitation) {
		teamDAO.rejectInvitation(idInvitation);
	}

	@Transactional
	public Team oneTeamById(int idTeam) {
		return teamDAO.oneTeamById(idTeam);
	}

	@Transactional
	public boolean isAdmin(int idUser, int idTeam) {
		return teamDAO.isAdmin(idUser, idTeam);
	}

	@Transactional
	public void quitTeam(int idUser, int idTeam) {
		teamDAO.quitTeam(idUser, idTeam);
	}

	@Transactional
	public void updateTeam(Team team) {
		teamDAO.updateTeam(team);
	}

	@Transactional
	public void setPrivileges(int idTeam, int idUser) {
		teamDAO.setPrivileges(idTeam, idUser);
	}

}
