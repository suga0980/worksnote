package jp.co.chitose.service;

import jp.co.chitose.classes.Work;
import jp.co.chitose.repository.IWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService implements IWorkService {

    private IWorkRepository workRepository;

    @Autowired
    public WorkService(IWorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public void registerWork(Integer accountId, Work work) {
        String title = work.getTitle();
        Date date = Date.valueOf(work.getLocalDate());
        String workType = work.getWorkType();
        String moreWorkType = work.getMoreWorkType();
        String memo = work.getMemo();
        workRepository.insert(accountId, title, date, workType, moreWorkType, memo);
    }

    @Override
    public void updateWork(Integer workId, Work work) {

    }

    @Override
    public List<Work> getDailyWorks(Integer accountId, LocalDate localDate) {
        String title;
        String workType;
        String moreWorkType;
        String memo;
        Date date = Date.valueOf(localDate);
        List<Integer> ids = workRepository.selectWorkIds(accountId, date);
        List<Work> works = new ArrayList<>(ids.size());
        for (int id : ids) {
            title = workRepository.selectWorkTitle(id);
            workType = workRepository.selectWorkType(id);
            moreWorkType = workRepository.selectMoreWorkType(id);
            memo = workRepository.selectMemo(id);
            Work work = new Work(accountId, title, localDate, workType, moreWorkType, memo);
            works.add(work);
        }
        return works;
    }

    @Override
    public int getWorkCount(Integer accountId) {
        List<Integer> workIds = workRepository.selectAllWorkIds(accountId);
        return workIds.size();
    }
}
