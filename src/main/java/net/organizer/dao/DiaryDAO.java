package net.organizer.dao;

import java.util.List;

import net.organizer.form.Diary;
import net.organizer.form.User;

public interface DiaryDAO {
	
	public void addDiary(Diary diary);
	
	public List<Diary> listDiaryMonth(User idUser, int year, int month);

	public Diary oneDiary(User user, int year, int month, int day);

	public void updateDiary(Diary diary);

	public void removeDiary(Integer id);
	
}



