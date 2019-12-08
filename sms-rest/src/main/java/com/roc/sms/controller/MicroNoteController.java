package com.roc.sms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.roc.sms.bean.MicroNote;
import com.roc.sms.service.IMicroNoteService;

@RestController
public class MicroNoteController {
	@Autowired
    private IMicroNoteService microNoteService;
	@RequestMapping(value = "/microNote", method = RequestMethod.POST)
	public int add(@RequestBody MicroNote microNote) {		
		return microNoteService.add(microNote);
	}

	@RequestMapping(value = "/microNote", method = RequestMethod.PUT)
	public int update(@RequestBody MicroNote microNote) {
		return microNoteService.update(microNote);
	}

	@RequestMapping(value = "/microNote", method = RequestMethod.DELETE)
	public int delete(@RequestParam(value = "id", required = true) Long microNoteId) {
		return microNoteService.delete(microNoteId);
	}

	@RequestMapping(value = "/microNote", method = RequestMethod.GET)
	public List<MicroNote> find(@RequestParam(value = "id", required = false) Integer id) {
		List<MicroNote> mic = microNoteService.find(id);
		return mic;
		 
	}
}
