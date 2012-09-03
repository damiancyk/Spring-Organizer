package net.organizer.service;

import java.util.List;

import net.organizer.dao.DiaryDAO;
import net.organizer.form.Diary;
import net.organizer.form.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryDAO diaryDAO;

	@Transactional
	public void addDiary(Diary diary) {
		diaryDAO.addDiary(diary);
	}

	@Transactional
	public List<Diary> listDiaryMonth(User idUser, int year, int month) {
		return diaryDAO.listDiaryMonth(idUser, year, month);
	}

	@Transactional
	public Diary oneDiary(User user, int year, int month, int day) {
		return diaryDAO.oneDiary(user, year, month, day);
	}

	@Transactional
	public void updateDiary(Diary diary) {
		diaryDAO.updateDiary(diary);
	}

	@Transactional
	public void removeDiary(Integer id) {
		diaryDAO.removeDiary(id);
	}
}