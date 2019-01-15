package jp.co.chitose.service;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;
import jp.co.chitose.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerAccount(Account account) {
        String accountName = account.getName();
        String password = account.getPassword();
        Role role = account.getRole();
        accountRepository.insertAccount(accountName, password, role.getString());
        int id = accountRepository.selectAccountId(accountName);
        String mailAddress = account.getMailAddress();
        String realName = account.getRealName();
        String realNameRuby = account.getRealNameRuby();
        accountRepository.insertAccountInfo(id, mailAddress, realName, realNameRuby);
    }

    @Override
    public boolean authAccount(String accountName, String password) {
        return accountRepository.exists(accountName, password);
    }

    @Override
    public Integer getAccountId(String accountName) {
        Integer id = accountRepository.selectAccountId(accountName);
        return id;
    }

    @Override
    public Role getAccountRole(Integer accountId) {
        String role = accountRepository.selectRole(accountId);
        if (role.equals(Role.ADMIN.getString())) {
            return Role.ADMIN;
        } else if (role.equals(Role.STUFF.getString())) {
            return Role.STUFF;
        } else if (role.equals(Role.TEACHER.getString())) {
            return Role.TEACHER;
        }
        return null;
    }

    @Override
    public Account getAccount(Integer accountId) {
        String accountName = accountRepository.selectAccountName(accountId);
        Role role = getAccountRole(accountId);
        String mailAddress = accountRepository.selectMailAddress(accountId);
        String realName = accountRepository.selectRealName(accountId);
        String realNameRuby = accountRepository.selectRealNameRuby(accountId);
        Account account = new Account();
        account.setId(accountId);
        account.setName(accountName);
        account.setRole(role);
        account.setMailAddress(mailAddress);
        account.setRealName(realName);
        account.setRealNameRuby(realNameRuby);
        return account;
    }

    @Override
    public List<Integer> getIds(Role role) {
        List<Integer> ids = accountRepository.selectAccountIdList(role.getString());
        return ids;
    }
}
