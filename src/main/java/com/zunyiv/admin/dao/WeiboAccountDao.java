package com.zunyiv.admin.dao;

import com.zunyiv.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 微博账号
 * @author luoshuhong
 * date 2016年12月20日
 */
@Repository
public class WeiboAccountDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	/**
	 *
	 * @param nickName
	 * @param uuid
	 * @param token
     * @return
     */
	public boolean add(String nickName, String uuid, String token) {
		String insertSql = "insert into tb_weibo_acount (nickName,token,uuid) values(?,?,?)";
		return 1 == this.jdbcTemplate.update(insertSql, new Object[]{nickName, token, uuid});
	}


	/**
	 * 更新token
	 * @param uuid
	 * @param token
     * @return
     */
	public boolean updateTokenByUuid(String uuid, String token) {
		String updateSql = "update tb_weibo_acount set token = ? where uuid = ?";
		return 1 == this.jdbcTemplate.update(updateSql, new Object[]{token, uuid});
	}

	/**
	 * 更新token
	 * @param nickName
	 * @param token
	 * @return
	 */
	public boolean updateTokenByNickName(String nickName, String token) {
		String updateSql = "update tb_weibo_acount set token = ? where nickName = ?";
		return 1 == this.jdbcTemplate.update(updateSql, new Object[]{token, nickName});
	}

	/**
	 *
	 * @param nickName
	 * @return
     */
	public String[] queryByNickName(String nickName) {
		String sql = "SELECT id,nickName,token,uuid from tb_weibo_acount where nickName = ? ";
		List<Map<String,Object>> list =  this.jdbcTemplate.queryForList(sql, new Object[]{nickName});
		if (null == list || 0 == list.size()) {
			return null;
		}
		String[] result = new String[2];
		result[0] = list.get(0).get("token").toString();    //token
		result[1] = list.get(0).get("uuid").toString(); 	//uuid
		return result;
	}

}















