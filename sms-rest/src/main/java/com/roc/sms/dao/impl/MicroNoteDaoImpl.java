package com.roc.sms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.roc.sms.bean.MicroNote;
import com.roc.sms.bean.User;
import com.roc.sms.dao.IMicroNoteDao;

@Repository
public class MicroNoteDaoImpl implements IMicroNoteDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MicroNote> find(Integer id) {
		String sql = null;
		if(id == null) {
			sql = "select * from microblog";
		}else {
			sql = "select * from microblog WHERE id=\"" + id + "\"";
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<MicroNote>(MicroNote.class));

	}

	@Override
	public int add(MicroNote microNote) {
		String sql = "INSERT INTO microblog (message,createTime,selecValue,edit,dialogVisible,color,bgcolor) "
				+ "VALUES (?,?,?,?,?,?,?)";
		int result = jdbcTemplate.update(sql, microNote.getMessage(), microNote.getCreateTime(), 
				microNote.getSelecValue(),microNote.isEdit(),microNote.isDialogVisible(),
				microNote.getColor(), microNote.getBgcolor());
		return result;		
	}

	@Override
	public int update(MicroNote microNote) {
		String sql = "UPDATE microblog SET message=?,createTime=?,selecValue=?,edit=?,dialogVisible=?,"
						+ "color=?,bgcolor=? WHERE id=?";
		int result = jdbcTemplate.update(sql, microNote.getMessage(), microNote.getCreateTime(), 
				microNote.getSelecValue(),microNote.isEdit(),microNote.isDialogVisible(),
				microNote.getColor(), microNote.getBgcolor(),microNote.getId());
		return result;
	}

	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM microblog WHERE id=?";
		int result = jdbcTemplate.update(sql, id);
		return result;
	}

}
