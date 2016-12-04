package com.zunyiv.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.zunyiv.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author luoshuhong
 * date 2016年12月2日
 */
@Repository
public class UserDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	private static String QUERY_RESULT_PARAM_ALL = "SELECT id,openid,nickName,realName,phone,professional,birthday,inputTime,role,password,avator from tb_user ";

	/**
	 * 添加用户
	 * @param user 新用户记录
	 * @return
     */
	public boolean addUser(User user) {
		String insertSql = "insert into tb_user (openid,nickName,realName,phone,professional," +
				"birthday,inputTime,role,password,avator) values(?,?,?,?,?,?,?,?,?,?)";
		return 1 == this.jdbcTemplate.update(insertSql, new Object[]{user.getOpenid(), user.getNickName(),
			user.getRealName(), user.getPhone(), user.getProfessional(), user.getBirthday(),
			new Date(), user.getRole(), user.getPassword(), user.getAvator()});
	}


	/**
	 * 微信设置更新用户信息
	 * @param user
	 * @return
     */
	public boolean updateUserFormWX(User user) {
		String updateSql = "update tb_user set realName = ?,birthday = ?, openId = ?,avator = ?, professional = ? where phone = ?";
		return 1 == this.jdbcTemplate.update(updateSql, new Object[]{user.getRealName(), user.getBirthday(),
			user.getOpenid(), user.getAvator(), user.getProfessional(), user.getPhone()});
	}

	/**
	 * 更新密码
	 * @param newpwd 新密码
	 * @param phone 电话
     * @return
     */
	public boolean updateUserPwd(String newpwd, String phone) {
		String updateSql = "update tb_user set password = ? where phone = ?";
		return 1 == this.jdbcTemplate.update(updateSql, new Object[]{newpwd, phone});
	}


	/**
	 * 查询所有用户记录
	 * @return
     */
	public List<User> query() {
		return this.jdbcTemplate.query(QUERY_RESULT_PARAM_ALL, new UserRowMapper());
	}

	/**
	 * 根据手机号查询用户记录
	 * @param phone	手机号
	 * @return User对象  不存在为null
     */
	public User query(String phone) {
		String sql = QUERY_RESULT_PARAM_ALL + " where phone = ? ";
		List<User> list = this.jdbcTemplate.query(sql, new Object[]{phone}, new UserRowMapper());
		if (null != list && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}



	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setOpenid(rs.getString("openId"));
			user.setNickName(rs.getString("nickName"));
			user.setRealName(rs.getString("realName"));
			user.setPhone(rs.getString("phone"));
			user.setBirthday(rs.getDate("birthday").toString());
			user.setInputTime(rs.getDate("inputTime").toString());
			user.setRole(rs.getInt("role"));
			user.setPassword(rs.getString("password"));
			user.setAvator(rs.getString("avator"));
			user.setProfessional(rs.getInt("professional"));
			return user;
		}
	}
}















