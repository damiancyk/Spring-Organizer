package net.organizer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.organizer.dao.ActionDAO;
import net.organizer.form.Action;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDAO actionDAO;

	@Transactional
	public List<Action> listActionByIdTeam(int idTeam, Short typeAction) {
		return actionDAO.listActionByIdTeam(idTeam, typeAction);
	}

	@Transactional
	public List<Action> listActionByIdUser(Integer idUser, Short typeAction,
			Date dateFrom, Date dateTo) {
		return actionDAO.listActionByIdUser(idUser, typeAction, dateFrom,
				dateTo);
	}

	@Transactional
	public void addAction(Action action) {
		actionDAO.addAction(action);
	}

	@Transactional
	public Action oneAction(int idAction) {
		return actionDAO.oneAction(idAction);
	}

	@Transactional
	public int oneIdTeamByIdAction(int idAction) {
		return actionDAO.oneIdTeamByIdAction(idAction);
	}

	@Transactional
	public void deleteAction(int idAction) {
		actionDAO.deleteAction(idAction);
	}

}
