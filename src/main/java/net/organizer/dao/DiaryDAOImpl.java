package net.organizer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.organizer.form.Diary;
import net.organizer.form.User;

@Repository
public class DiaryDAOImpl implements DiaryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addDiary(Diary diary) {
		Session session=sessionFactory.getCurrentSession();
		session.save(diary);
	}

	@SuppressWarnings("unchecked")
	public List<Diary> listDiaryMonth(User user, int year, int month) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Diary.class);

		criteria.add(Restrictions.like("idUser", user));
		criteria.add(Restrictions.like("year", year));
		criteria.add(Restrictions.like("month", month));

		criteria.addOrder(Order.desc("day"));

		return criteria.list();
	}

	public Diary oneDiary(User user, int year, int month, int day) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Diary.class);
		criteria.add(Restrictions.like("idUser", user));
		criteria.add(Restrictions.like("year", year));
		criteria.add(Restrictions.like("month", month));
		criteria.add(Restrictions.like("day", day));
		if (criteria.list().size() > 0)
			return (Diary) criteria.list().get(0);
		else
			return null;
	}

	public void updateDiary(Diary diary) {
		sessionFactory.getCurrentSession().saveOrUpdate(diary);
	}

	public void removeDiary(Integer id) {
		Diary diary = (Diary) sessionFactory.getCurrentSession().load(
				Diary.class, id);
		if (null != diary) {
			sessionFactory.getCurrentSession().delete(diary);
		}
	}

}
