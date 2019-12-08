package com.roc.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roc.sms.bean.MicroNote;
import com.roc.sms.dao.IMicroNoteDao;
import com.roc.sms.service.IMicroNoteService;

@Service
public class MicroNoteServiceImpl implements IMicroNoteService{
	@Autowired
	private IMicroNoteDao microNoteDao;
	
	@Override
	public List<MicroNote> find(Integer id) {
		return microNoteDao.find(id);
	}

	@Override
	public int add(MicroNote microNote) {
		return microNoteDao.add(microNote);
	}

	@Override
	public int update(MicroNote microNote) {
		return microNoteDao.update(microNote);
	}

	@Override
	public int delete(Long id) {
		return microNoteDao.delete(id);
	}

}
