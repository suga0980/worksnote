package jp.co.chitose.classes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Work implements Serializable {

    private Integer workId;
    private Integer accountId;
    private String title;
    private LocalDate localDate;
    private String workType;
    private String moreWorkType;
    private List<File> files;
    private String memo;

    public Work(Integer accountId, String title, LocalDate localDate, String workType, String moreWorkType, String memo) {
        this.accountId = accountId;
        this.title = title;
        this.localDate = localDate;
        this.workType = workType;
        this.moreWorkType = moreWorkType;
        // this.files = files;
        this.memo = memo;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getMoreWorkType() {
        return moreWorkType;
    }

    public void setMoreWorkType(String moreWorkType) {
        this.moreWorkType = moreWorkType;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
