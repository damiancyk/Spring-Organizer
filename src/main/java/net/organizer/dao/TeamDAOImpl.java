package net.organizer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.organizer.form.Action;
import net.organizer.form.Invitation;
import net.organizer.form.Team;
import net.organizer.form.User;
import net.organizer.form.UserTeam;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<UserTeam> listUserTeamByUser(User idUser) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idUser", idUser));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Team> listTeamByIdUser(int idUser) {
		User user = new User();
		user.setIdUser(idUser);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idUser", user));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<UserTeam> listUserTeamByIdTeam(int idTeam) {
		Team team = new Team();
		team.setIdTeam(idTeam);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idTeam", team));

		return criteria.list();
	}

	public UserTeam oneUserTeamById(int idUserTeam) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idUserTeam", idUserTeam));

		return (UserTeam) criteria.uniqueResult();

	}

	public UserTeam oneUserTeam(int idTeam, int idUser) {
		Team teamRestriction = new Team();
		teamRestriction.setIdTeam(idTeam);
		User userRestriction = new User();
		userRestriction.setIdUser(idUser);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idTeam", teamRestriction));
		criteria.add(Restrictions.like("idUser", userRestriction));

		if (criteria.list().size() > 0)
			return (UserTeam) criteria.list().get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<Action> listActionByIdTeam(int typeAction, int idTeam) {
		Team teamRestriction = new Team();
		teamRestriction.setIdTeam(idTeam);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Action.class);
		criteria.add(Restrictions.like("idTeam", teamRestriction));
		criteria.add(Restrictions.like("idAction", typeAction));

		return criteria.list();
	}

	public void addTeam(Team team, User user, boolean isAdmin) {
		Session session = sessionFactory.getCurrentSession();

		Serializable serializable = session.save(team);
		Team teamReturn = (Team) session.get(Team.class, serializable);
		teamReturn.getIdTeam();
		addUserTeam(teamReturn, user, isAdmin);
	}

	public void addUserTeam(Team idTeam, User idUser, boolean isAdmin) {
		UserTeam userTeam = new UserTeam();
		userTeam.setIdTeam(idTeam);
		userTeam.setIdUser(idUser);
		userTeam.setIsAdmin(isAdmin);
		sessionFactory.getCurrentSession().save(userTeam);
	}

	public void deleteTeam(int idTeam) {
		Team team = new Team();
		team.setIdTeam(idTeam);
		team.setName("");
		team.setDescription("");
		sessionFactory.getCurrentSession().delete(team);
	}

	public boolean sendInvitation(int idUser, int idTeam) {
		boolean success = false;
		UserTeam heIsInTeam = oneUserTeam(idTeam, idUser);

		if (idUser != idTeam && idUser != 0 && idTeam != 0 & heIsInTeam == null) {
			User user = new User();
			user.setIdUser(idUser);
			Team team = new Team();
			team.setIdTeam(idTeam);

			Invitation invitation = new Invitation();
			invitation.setIdUser(user);
			invitation.setIdTeam(team);

			sessionFactory.getCurrentSession().saveOrUpdate(invitation);
			success = true;
		}

		return success;
	}

	@SuppressWarnings("unchecked")
	public List<Invitation> listInvitation(int idUser) {
		User user = new User();
		user.setIdUser(idUser);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invitation.class);
		criteria.add(Restrictions.like("idUser", user));

		return criteria.list();
	}

	public void acceptInvitation(Invitation invitation) {
		UserTeam userTeam = new UserTeam();
		userTeam.setIdUser(invitation.getIdUser());
		userTeam.setIdTeam(invitation.getIdTeam());

		sessionFactory.getCurrentSession().save(userTeam);
		sessionFactory.getCurrentSession().delete(invitation);
	}

	public void rejectInvitation(int idInvitation) {
		Invitation invitation = oneInvitationById(idInvitation);
		sessionFactory.getCurrentSession().delete(invitation);
	}

	private Invitation oneInvitationById(int idInvitation) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invitation.class);
		criteria.add(Restrictions.like("idInvitation", idInvitation));

		return (Invitation) criteria.uniqueResult();
	}

	public Team oneTeamById(int idTeam) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Team.class);
		criteria.add(Restrictions.like("idTeam", idTeam));

		return (Team) criteria.uniqueResult();
	}

	public boolean isAdmin(int idUser, int idTeam) {
		UserTeam userTeam = oneUserTeam(idTeam, idUser);

		if (userTeam != null) {
			return userTeam.getIsAdmin();
		} else {
			return false;
		}
	}

	public void quitTeam(int idUser, int idTeam) {
		Session session = sessionFactory.getCurrentSession();

		User user = new User();
		user.setIdUser(idUser);
		Team team = new Team();
		team.setIdTeam(idTeam);

		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idTeam", team));
		criteria.add(Restrictions.like("idUser", user));
		UserTeam userTeam = (UserTeam) criteria.uniqueResult();

		if (userTeam != null) {
			session.delete(userTeam);
		}
	}

	public void updateTeam(Team team) {
		sessionFactory.getCurrentSession().saveOrUpdate(team);
	}

	public void setPrivileges(int idTeam, int idUser) {
		Session session = sessionFactory.getCurrentSession();

		Team team = new Team();
		team.setIdTeam(idTeam);
		User user = new User();
		user.setIdUser(idUser);

		Criteria criteria = session.createCriteria(UserTeam.class);
		criteria.add(Restrictions.like("idTeam", team));
		criteria.add(Restrictions.like("idUser", user));
		UserTeam userTeam = (UserTeam) criteria.uniqueResult();
		if (userTeam != null)
			userTeam.setIsAdmin(true);
	}
}
