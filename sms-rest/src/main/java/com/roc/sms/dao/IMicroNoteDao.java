package com.roc.sms.dao;

import java.util.List;
import com.roc.sms.bean.MicroNote;

public interface IMicroNoteDao {
	List<MicroNote> find(Integer id);

    int add(MicroNote microNote);

    int update(MicroNote microNote);

    int delete(Long id);
}
