package jp.co.chitose.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.ListView;
import java.sql.Date;
import java.util.List;

@Repository
public class WorkRepository implements IWorkRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public WorkRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(Integer accountId, String title, Date date, String workType, String moreWorkType, String memo) {
        String sql = "INSERT INTO work(account_id, work_title, work_date, work_type, more_work_type, work_memo) VALUES (?, ?, ?, ?, ?, ?)";
        int n = jdbc.update(sql, accountId, title, date, workType, moreWorkType, memo);
        return n;
    }

    @Override
    public Integer selectAccountId(Integer workId) {
        String sql = "SELECT account_id FROM work WHERE work_id = ?";
        List<Integer> ids = jdbc.query(sql, new SingleColumnRowMapper(Integer.class), workId);
        return ids.get(0);
    }

    @Override
    public String selectWorkTitle(Integer workId) {
        String sql = "SELECT work_title FROM work WHERE work_id = ?";
        List<String> titles = jdbc.query(sql, new SingleColumnRowMapper(String.class), workId);
        return titles.get(0);
    }

    @Override
    public Date selectWorkDate(Integer workId) {
        String sql = "SELECT work_date FROM work WHERE work_id = ?";
        List<Date> dates = jdbc.query(sql, new SingleColumnRowMapper<>(Date.class), workId);
        return dates.get(0);
    }

    @Override
    public String selectWorkType(Integer workId) {
        String sql = "SELECT work_type FROM work WHERE work_id = ?";
        List<String> workTypes = jdbc.query(sql, new SingleColumnRowMapper(String.class), workId);
        return workTypes.get(0);
    }

    @Override
    public String selectMoreWorkType(Integer workId) {
        String sql = "SELECT more_work_type FROM work WHERE work_id = ?";
        List<String> moreWorkType = jdbc.query(sql, new SingleColumnRowMapper(String.class), workId);
        return moreWorkType.get(0);
    }

    @Override
    public String selectMemo(Integer workId) {
        String sql = "SELECT work_memo FROM work WHERE work_id = ?";
        List<String> memos = jdbc.query(sql, new SingleColumnRowMapper(String.class), workId);
        return memos.get(0);
    }

    @Override
    public List<Integer> selectWorkIds(Integer accountId, Date date) {
        String sql = "SELECT work_id FROM work WHERE account_id = ? and work_date = ?";
        List<Integer> ids = jdbc.query(sql, new SingleColumnRowMapper(Integer.class), new Object[]{accountId, date});
        return ids;
    }

    @Override
    public List<Integer> selectAllWorkIds(Integer accountId) {
        String sql = "SELECT work_id FROM work WHERE account_id = ?";
        List<Integer> ids = jdbc.query(sql, new SingleColumnRowMapper(Integer.class), accountId);
        return ids;
    }
}
