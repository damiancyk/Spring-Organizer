package net.organizer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.organizer.dao.NoteDAO;
import net.organizer.form.Note;
import net.organizer.form.User;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDAO noteDAO;

	@Transactional
	public void addNote(Note note) {
		noteDAO.addNote(note);
	}

	@Transactional
	public List<Note> listNote(User idUser, String order, Integer firstResult,
			Integer maxResults) {
		return noteDAO.listNote(idUser, order, firstResult, maxResults);
	}

	@Transactional
	public Note oneNote(int id) {
		return noteDAO.oneNote(id);
	}

	@Transactional
	public void updateNote(Note note) {
		noteDAO.updateNote(note);
	}

	@Transactional
	public void removeNote(Integer id) {
		noteDAO.removeNote(id);
	}
}