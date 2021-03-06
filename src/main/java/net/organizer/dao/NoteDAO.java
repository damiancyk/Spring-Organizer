package net.organizer.dao;

import java.util.List;

import net.organizer.form.Note;
import net.organizer.form.User;

public interface NoteDAO {
	
	public void addNote(Note note);

	public List<Note> listNote(User idUser, String order, Integer firstResult,
			Integer maxResults);

	public Note oneNote(int id);

	public void updateNote(Note note);

	public void removeNote(Integer id);
	
}
