package net.organizer.service;

import java.util.Date;
import java.util.List;

import net.organizer.form.Action;

public interface ActionService {

	public List<Action> listActionByIdTeam(int idTeam, Short typeAction);
	
	public List<Action> listActionByIdUser(Integer idUser, Short typeAction, Date dateFrom,
			Date dateTo);
	
	public void addAction(Action action);
	
	public Action oneAction(int idAction);
	
	public int oneIdTeamByIdAction(int idAction);
	
	public void deleteAction(int idAction);
	
}
