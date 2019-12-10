package com.roc.sms.service;

import java.util.List;

import com.roc.sms.bean.MicroNote;

public interface IMicroNoteService {
	List<MicroNote> find(Integer userId);

    int add(MicroNote microNote);

    int update(MicroNote microNote);

    int delete(Long id);
}
