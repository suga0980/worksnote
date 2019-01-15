package jp.co.chitose.service;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;

import java.util.List;

public interface IAccountService {

    public void registerAccount(Account account);

    public boolean authAccount(String accountName, String password);

    public Integer getAccountId(String accountName);

    public Role getAccountRole(Integer accountId);

    public Account getAccount(Integer accountId);

    public List<Integer> getIds(Role role);
}
