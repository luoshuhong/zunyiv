package com.zunyiv.admin.dao;

import com.zunyiv.admin.model.WeiboRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import weibo4j.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author luoshuhong
 * date 2016年12月2日
 */
@Repository
public class WeiboRecordDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	private static String QUERY_RESULT_PARAM = "SELECT id, userId, weiboId, content, url, repostsCount, commentsCount, likeCount, source, retweetedStatus,createDate from tb_weibo_record ";

	/**
	 * 添加微博记录
	 * @param weiStatus
	 * @return
     */
	public boolean add(Status weiStatus) {
		try {
			String insertSql = "insert into tb_weibo_record (weiboId, content, url, repostsCount, commentsCount, " +
					"likeCount,retweetedStatus,createDate,source) values(?,?,?,?,?,?,?,?,?)";
			return 1 == this.jdbcTemplate.update(insertSql, new Object[]{weiStatus.getId(), weiStatus.getText(), "", weiStatus.getRepostsCount(),
					weiStatus.getCommentsCount(), weiStatus.getAttitudesCount(),
					weiStatus.getRetweetedStatus() == null ? 0 : 1, weiStatus.getCreatedAt(), weiStatus.getSource() == null ? "" : weiStatus.getSource().getName()});
		} catch (Exception e) {
			e.printStackTrace();
			return 1 == this.jdbcTemplate.update("insert into tb_weibo_record (weiboId, source) values(?,?)",
					new Object[]{weiStatus.getId(), weiStatus.getSource() == null ? "" : weiStatus.getSource().getName()});
		}
	}

	/**
	 * 更新微博
	 * @param weiStatus
	 * @return
     */
	public boolean update(Status weiStatus) {
		try {
			String updateSql = "update tb_weibo_record set repostsCount=?, commentsCount=?, likeCount=?, updateDate=? where weiboId = ?";
			return 1 == this.jdbcTemplate.update(updateSql, new Object[]{ weiStatus.getRepostsCount(), weiStatus.getCommentsCount(),  weiStatus.getAttitudesCount(), new Date(), weiStatus.getId()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 微信设置更新用户信息
	 * @param user
	 * @return
     */
	public WeiboRecord queryByWeiboId(String weiboId) {
		String sqlectSql = QUERY_RESULT_PARAM + " where weiboId = ?";
		List<WeiboRecord> list = this.jdbcTemplate.query(sqlectSql, new Object[]{weiboId}, new WeiboRecordRowMapper());
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 查询多少天以内的微博
	 * @param day
	 * @return
     */
	public List<WeiboRecord>  queryUpdateWeiRecord(int day) {
		String sql = "select id, userId, weiboId, content, url, repostsCount, commentsCount, likeCount, source, retweetedStatus,createDate " +
				" from tb_weibo_record where date_sub(curdate(), INTERVAL " + day + " DAY) <= date(`createDate`)  order by id desc";
		List<WeiboRecord> list = this.jdbcTemplate.query(sql, new WeiboRecordRowMapper());
		return list;
	}

	/**
	 * 根据微博内容关键字查询
	 * @param keyWord
	 * @return
     */
	public List<WeiboRecord> query(String keyWord) {
		String sql = "select id, userId, weiboId, content, url, repostsCount, commentsCount, likeCount, source, retweetedStatus,createDate " +
				" from tb_weibo_record where content like '%" + keyWord + "%' order by createDate desc";
		List<WeiboRecord> list = this.jdbcTemplate.query(sql, new WeiboRecordRowMapper());
		return list;
	}

	/**
	 * 根据发微博的时间查询
	 * @param sDate
	 * @param eDate
     * @return
     */
	public List<WeiboRecord> query(String sDate, String eDate) {
		String sql = "select id, userId, weiboId, content, url, repostsCount, commentsCount, likeCount, source, retweetedStatus,createDate " +
				" from tb_weibo_record where createDate > ? and createDate < ? order by createDate desc";
		List<WeiboRecord> list = this.jdbcTemplate.query(sql,new Object[]{sDate, eDate}, new WeiboRecordRowMapper());
		return list;
	}

	class WeiboRecordRowMapper implements RowMapper<WeiboRecord> {
		@Override
		public WeiboRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			WeiboRecord weiboRecord = new WeiboRecord();
			weiboRecord.setId(rs.getInt("id"));
			weiboRecord.setWeiboId(rs.getString("weiboId"));
			weiboRecord.setUserId(rs.getInt("userId"));
			weiboRecord.setContent(rs.getString("content"));
			weiboRecord.setUrl(rs.getString("url"));
			weiboRecord.setRepostsCount(rs.getInt("repostsCount"));
			weiboRecord.setCommentsCount(rs.getInt("commentsCount"));
			weiboRecord.setLikeCount(rs.getInt("likeCount"));
			weiboRecord.setSource(rs.getString("source"));
			weiboRecord.setRetweetedStatus(rs.getInt("retweetedStatus"));
			weiboRecord.setCreateDate(rs.getDate("createDate"));
			return weiboRecord;
		}
	}
}















