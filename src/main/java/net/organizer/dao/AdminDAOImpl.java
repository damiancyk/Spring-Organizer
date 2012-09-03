package net.organizer.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.organizer.form.Details;
import net.organizer.form.Role;
import net.organizer.form.User;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Details> listUserToSend(Date dateFrom, Date dateTo) {
		Session session = sessionFactory.getCurrentSession();
		String restrictionDateFrom = "";
		String restrictionDateTo = "";

		if (dateFrom != null) {
			restrictionDateFrom = " AND a.dateWhen>=:dateFrom";
			System.out.println("dateFrom: " + dateFrom.toString());
		}

		if (dateTo != null) {
			restrictionDateTo = " AND a.dateWhen<=:dateTo";
			System.out.println("dateTo: " + dateTo.toString());
		}

		Query query = session
				.createQuery("SELECT DISTINCT d FROM Details d, User u, Action a, Team t, UserTeam ut WHERE a.idTeam=t.idTeam AND ut.idTeam=t.idTeam AND ut.idUser=u.idUser AND d.idDetails=u.idDetails"
						+ restrictionDateFrom
						+ restrictionDateTo
						+ " ORDER BY a.dateWhen");

		if (dateFrom != null)
			query.setParameter("dateFrom", dateFrom);
		if (dateTo != null)
			query.setParameter("dateTo", dateTo);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser(Integer firstResult, Integer maxResults) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.addOrder(Order.asc("login"));
		if (firstResult != null)
			criteria.setFirstResult(firstResult);
		if (maxResults != null)
			criteria.setMaxResults(maxResults);

		return criteria.list();
	}

	public boolean deleteUser(Integer idUser) {
		boolean success = false;
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				idUser);

		if (null != user) {
			boolean isAdmin = isAdmin(user.getIdUser());

			if (!isAdmin) {
				Details details = getUserDetails(user.getIdDetails()
						.getIdDetails());
				if (details != null) {
					sessionFactory.getCurrentSession().delete(details);
				}
				sessionFactory.getCurrentSession().delete(user);
				success = true;
			}

		} else {
			success = true;
		}

		return success;
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRoleOfUser(Integer idUser) {
		User user = new User();
		user.setIdUser(idUser);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.like("idUser", user));

		return criteria.list();
	}

	public boolean isAdmin(Integer idUser) {
		boolean isA = false;
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				idUser);

		if (user != null) {
			List<Role> roleList = listRoleOfUser(user.getIdUser());
			for (int i = 0; i < roleList.size(); i++) {
				if (roleList.get(i).getAuthority().compareTo("ROLE_ADMIN") == 0)
					isA = true;
			}
		}

		return isA;
	}

	public Details getUserDetails(Integer idDetails) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Details.class);
		criteria.add(Restrictions.like("idDetails", idDetails));

		return (Details) criteria.uniqueResult();
	}

}
