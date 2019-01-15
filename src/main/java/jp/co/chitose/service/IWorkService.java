package jp.co.chitose.service;

import jp.co.chitose.classes.Work;

import java.time.LocalDate;
import java.util.List;

public interface IWorkService {

    public void registerWork(Integer accountId, Work work);

    public void updateWork(Integer workId, Work work);

    public List<Work> getDailyWorks(Integer accountId, LocalDate localDate);

    public int getWorkCount(Integer accountId);
}
