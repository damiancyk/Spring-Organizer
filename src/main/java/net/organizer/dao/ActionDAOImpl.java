package net.organizer.dao;

import java.util.Date;
import java.util.List;

import net.organizer.form.Action;
import net.organizer.form.Team;
import net.organizer.form.User;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActionDAOImpl implements ActionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Action> listActionByIdTeam(int idTeam, Short typeAction) {
		Session session = sessionFactory.getCurrentSession();
		Team team = new Team();
		team.setIdTeam(idTeam);

		Criteria criteria = session.createCriteria(Action.class).add(
				Restrictions.like("idTeam", team));
		criteria.add(Restrictions.like("typeAction", typeAction));
		criteria.addOrder(Order.asc("dateWhen"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Action> listActionByIdUser(Integer idUser, Short typeAction,
			Date dateFrom, Date dateTo) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setIdUser(idUser);
		String restrictionUser = " a.idTeam=t.idTeam AND t.idTeam=ut.idTeam AND ut.idUser=:idUser";
		String restrictionType = "";
		String restrictionDateFrom = "";
		String restrictionDateTo = "";
		String restrictionPeriodic = " AND a.periodic='no'";

		if (typeAction != null) {
			if (typeAction <= 2) {
				restrictionPeriodic = " AND a.periodic='no'";
			} else if (typeAction == 3) {
				restrictionPeriodic = " AND a.periodic!='no'";
			}
		}

		if (typeAction != null)
			if (typeAction > 0 && typeAction < 3)
				restrictionType = " AND a.typeAction=:typeAction";

		if (dateFrom != null) {
			restrictionDateFrom = " AND a.dateWhen>=:dateFrom";
			System.out.println("dateFrom: " + dateFrom.toString());
		}

		if (dateTo != null) {
			restrictionDateTo = " AND a.dateWhen<=:dateTo";
			System.out.println("dateTo: " + dateTo.toString());
		}

		Query query = session
				.createQuery("SELECT DISTINCT a FROM Action a, Team t, UserTeam ut"
						+ " WHERE"
						+ restrictionUser
						+ restrictionType
						+ restrictionDateFrom
						+ restrictionDateTo
						+ restrictionPeriodic + " ORDER BY a.dateWhen");

		if (typeAction != null)
			if (typeAction > 0 && typeAction < 3)
				query.setParameter("typeAction", typeAction);
		if (dateFrom != null)
			query.setParameter("dateFrom", dateFrom);
		if (dateTo != null)
			query.setParameter("dateTo", dateTo);

		query.setParameter("idUser", user);

		return query.list();
	}

	public void addAction(Action action) {
		sessionFactory.getCurrentSession().saveOrUpdate(action);
	}

	public Action oneAction(int idAction) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Action.class);
		criteria.add(Restrictions.like("idAction", idAction));

		return (Action) criteria.uniqueResult();
	}

	public int oneIdTeamByIdAction(int idAction) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Action.class);
		criteria.add(Restrictions.like("idAction", idAction));

		Action action = (Action) criteria.uniqueResult();

		if (action != null) {
			return action.getIdTeam().getIdTeam();
		} else {
			return 0;
		}

	}

	public void deleteAction(int idAction) {
		Action action = oneAction(idAction);

		sessionFactory.getCurrentSession().delete(action);
	}

}
