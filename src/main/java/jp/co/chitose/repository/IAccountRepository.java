package jp.co.chitose.repository;

import java.util.List;

public interface IAccountRepository {

    public boolean exists(String accountName, String password);

    public int insertAccount(String accountName, String password, String role);

    public int insertAccountInfo(Integer accountId, String mailAddress, String realName, String realNameRuby);

    public Integer selectAccountId(String accountName);

    public List<Integer> selectAccountIdList(String role);

    public String selectAccountName(Integer accountId);

    public String selectRole(Integer accountId);

    public String selectMailAddress(Integer accountId);

    public String selectRealName(Integer accountId);

    public String selectRealNameRuby(Integer accountId);
}
