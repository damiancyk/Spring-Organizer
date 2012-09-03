package net.organizer.dao;

import java.io.Serializable;
import java.util.List;

import net.organizer.form.Details;
import net.organizer.form.Team;
import net.organizer.form.User;
import net.organizer.form.Role;
import net.organizer.form.UserTeam;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public User getUser(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("login", login));
		sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT id from user WHERE login=" + login);

		List<User> u = criteria.list();
		if (u != null)
			user = u.get(0);

		return user;
	}

	public boolean registerUser(String login, String password) {
		boolean registerSuccess = false;

		if (login.trim().length() > 0 && password.trim().length() > 0) {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.like("login", login));
			sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT id from user WHERE login=" + login);

			if (criteria.list().size() == 0) {
				User user = new User();
				user.setLogin(login);
				user.setPassword(password);
				user.setEnabled(1);
				sessionFactory.getCurrentSession().save(user);

				Criteria c = session.createCriteria(User.class);
				c.add(Restrictions.like("login", login));
				sessionFactory.getCurrentSession().createSQLQuery(
						"SELECT id from user WHERE login=" + login);
				User newUser = (User) c.list().get(0);

				Team team = new Team();
				team.setName("local");
				team.setDescription("lokalna druzyna \n uzytkownik: "
						+ newUser.getLogin());
				Serializable nTeam = session.save(team);
				Team newTeam=(Team) session.get(Team.class, nTeam);
			//	Team newTeam = (Team) session.get(Team.class, team);

				UserTeam userTeam = new UserTeam();
				userTeam.setIdUser(newUser);
				userTeam.setIdTeam(newTeam);
				userTeam.setIsAdmin(true);
				session.save(userTeam);

				Details details = new Details();
				details.setEmail("");
				sessionFactory.getCurrentSession().save(details);
				newUser.setIdDetails(details);

				Role role = new Role();
				role.setIdUser(newUser);
				role.setAuthority("ROLE_USER");
				user.setRole(role);
				sessionFactory.getCurrentSession().save(role);

				registerSuccess = true;
			}
		}

		return registerSuccess;
	}

	public Details getDetails(int idUser) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				idUser);
		return user.getIdDetails();
	}

	public void updateDetails(Details details) {
		sessionFactory.getCurrentSession().update(details);
	}

}