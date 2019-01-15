package jp.co.chitose.repository;

import java.sql.Date;
import java.util.List;

public interface IWorkRepository {

    public int insert(Integer accountId, String title, Date date, String workType, String moreWorkType, String memo);

    public Integer selectAccountId(Integer workId);

    public String selectWorkTitle(Integer workId);

    public Date selectWorkDate(Integer workId);

    public String selectWorkType(Integer workId);

    public String selectMoreWorkType(Integer workId);

    public String selectMemo(Integer workId);

    public List<Integer> selectWorkIds(Integer accountId, Date date);

    public List<Integer> selectAllWorkIds(Integer accountId);
}
