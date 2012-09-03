package net.organizer.dao;

import java.util.List;
import net.organizer.form.Note;
import net.organizer.form.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDAOImpl implements NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addNote(Note note) {
		sessionFactory.getCurrentSession().save(note);
	}

	@SuppressWarnings("unchecked")
	public List<Note> listNote(User user, String order, Integer firstResult,
			Integer maxResults) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Note.class);
		String s[] = order.split(",");
		for (int i = 0; i < s.length; i++) {
			criteria.addOrder(Order.desc(s[i]));
		}

		criteria.add(Restrictions.like("idUser", user));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	public Note oneNote(int id) {
		return (Note) sessionFactory.getCurrentSession().get(Note.class, id);
	}

	public void updateNote(Note note) {
		sessionFactory.getCurrentSession().merge(note);
	}

	public void removeNote(Integer id) {
		Note note = (Note) sessionFactory.getCurrentSession().load(Note.class,
				id);
		if (null != note) {
			sessionFactory.getCurrentSession().delete(note);
		}
	}
}