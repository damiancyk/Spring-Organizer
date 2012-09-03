package net.organizer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.organizer.form.Contact;
import net.organizer.form.User;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Contact> listContact(User idOwner) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Contact.class);
		criteria.add(Restrictions.like("idOwner", idOwner));

		return criteria.list();
	}
	
	public Contact oneContact(int idOwner, int idPosition) {
		User user=new User();
		user.setIdUser(idOwner);
		
		User uPosition=new User();
		uPosition.setIdUser(idPosition);
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Contact.class);
		criteria.add(Restrictions.like("idOwner", user));
		criteria.add(Restrictions.like("idPosition", uPosition));

		return (Contact) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findUser(String login) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("login", login));

		return criteria.list();
	}

	public void addContact(Contact contact) {
		sessionFactory.getCurrentSession().save(contact);
	}

	public void removeContact(Integer idContact) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().load(
				Contact.class, idContact);
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}
	}

	public boolean existContact(User idOwner, User idPosition) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Contact.class);
		criteria.add(Restrictions.like("idOwner", idOwner));
		criteria.add(Restrictions.like("idPosition", idPosition));

		if (criteria.list().isEmpty())
			return false;
		else
			return true;
	}

}
