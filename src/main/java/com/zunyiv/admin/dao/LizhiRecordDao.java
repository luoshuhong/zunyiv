package com.zunyiv.admin.dao;

import com.zunyiv.admin.model.LizhiRecord;
import com.zunyiv.admin.model.User;
import com.zunyiv.admin.model.WeiboRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2017/5/13.
 */
@Repository
public class LizhiRecordDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param isPush
     * @return
     */
    public List<LizhiRecord> query(int isPush) {
        String sqlectSql;
        List<LizhiRecord> list = new ArrayList<>();
        if (-1 != isPush) {
            sqlectSql = " select id, releaseTime, title, web_url, data_url, author, isPush from tb_lizhi_record where isPush = ? order by releaseTime desc";
            list = this.jdbcTemplate.query(sqlectSql, new Object[]{isPush}, new LizhiRecordRowMapper());
        } else {
            sqlectSql = " select id, releaseTime, title, web_url, data_url, author, isPush from tb_lizhi_record order by releaseTime desc";
            list = this.jdbcTemplate.query(sqlectSql, new LizhiRecordRowMapper());
        }

        if (null == list || 0 == list.size()) {
            return null;
        }
        return list;
    }

    /**
     * 更新状态
     * @param id
     * @return
     */
    public boolean push(int id) {
        String updateSql = "update tb_lizhi_record set isPush = 1 where id = ? ";
        return 1 == this.jdbcTemplate.update(updateSql, new Object[]{id});
    }

    public boolean add(LizhiRecord record) {
        String insertSql = "insert into tb_lizhi_record(releaseTime, title, web_url, data_url, author) " +
                "values (?, ?, ?, ?, ?)";
        return 1 == this.jdbcTemplate.update(insertSql, new Object[]{record.getReleaseTime(), record.getTitle(),
        record.webUrl, record.getDataUrl(), record.getAuthor()});
    }

    /**
     * 查询是否存在
     * @param webUrl
     * @return
     */
    public LizhiRecord queryByWebUrl(String webUrl) {
        String sqlectSql = " select id, releaseTime, title, web_url, data_url, author, isPush from tb_lizhi_record where web_url = ?";
        List<LizhiRecord> list = this.jdbcTemplate.query(sqlectSql, new Object[]{webUrl}, new LizhiRecordRowMapper());
        if (null == list || 0 == list.size()) {
            return null;
        }
        return list.get(0);
    }


    class LizhiRecordRowMapper implements RowMapper<LizhiRecord> {
        @Override
        public LizhiRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            LizhiRecord record = new LizhiRecord();
            record.setId(rs.getInt("id"));
            record.setReleaseTime(rs.getDate("releaseTime"));
            record.setTitle(rs.getString("title"));
            record.setWebUrl(rs.getString("web_url"));
            record.setDataUrl(rs.getString("data_url"));
            record.setAuthor(rs.getString("author"));
            record.setIsPush(rs.getInt("isPush"));
            return record;
        }
    }
}
