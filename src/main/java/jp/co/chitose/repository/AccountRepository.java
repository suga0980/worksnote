package jp.co.chitose.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean exists(String accountName, String password) {
        String sql = "SELECT TRUE FROM account WHERE account_name = ? and account_password = ?";
        List<Boolean> bools = jdbc.query(sql, new SingleColumnRowMapper(Boolean.class), new Object[]{accountName, password});
        return !bools.isEmpty();
    }

    @Override
    public int insertAccount(String accountName, String password, String role) {
        String sql = "INSERT INTO account(account_name, account_password, account_role) VALUES (?, ?, ?)";
        int n = jdbc.update(sql, accountName, password, role);
        return n;
    }

    @Override
    public int insertAccountInfo(Integer accountId, String mailAddress, String realName, String realNameRuby) {
        String sql = "INSERT INTO account_info(account_id, mail_address, real_name, real_name_ruby) VALUES (?, ?, ?, ?)";
        int n = jdbc.update(sql, accountId, mailAddress, realName, realNameRuby);
        return n;
    }

    @Override
    public Integer selectAccountId(String accountName) {
        String sql = "SELECT account_id FROM account WHERE account_name = ?";
        List<Integer> ids = jdbc.query(sql, new SingleColumnRowMapper(Integer.class), accountName);
        return ids.get(0);
    }

    @Override
    public List<Integer> selectAccountIdList(String role) {
        String sql = "SELECT account_id FROM account WHERE account_role = ?";
        List<Integer> ids = jdbc.query(sql, new SingleColumnRowMapper(Integer.class), role);
        return ids;
    }

    @Override
    public String selectAccountName(Integer accountId) {
        String sql = "SELECT account_name FROM account WHERE account_id = ?";
        List<String> names = jdbc.query(sql, new SingleColumnRowMapper(String.class), accountId);
        return names.get(0);
    }

    @Override
    public String selectRole(Integer accountId) {
        String sql = "SELECT account_role FROM account WHERE account_id = ?";
        List<String> roles = jdbc.query(sql, new SingleColumnRowMapper(String.class), accountId);
        return roles.get(0);
    }

    @Override
    public String selectMailAddress(Integer accountId) {
        String sql = "SELECT mail_address FROM account_info WHERE account_id = ?";
        List<String> mailAddresses = jdbc.query(sql, new SingleColumnRowMapper(String.class), accountId);
        return mailAddresses.get(0);
    }

    @Override
    public String selectRealName(Integer accountId) {
        String sql = "SELECT real_name FROM account_info WHERE account_id = ?";
        List<String> names = jdbc.query(sql, new SingleColumnRowMapper(String.class), accountId);
        return names.get(0);
    }

    @Override
    public String selectRealNameRuby(Integer accountId) {
        String sql = "SELECT real_name_ruby FROM account_info WHERE account_id = ?";
        List<String> rubies = jdbc.query(sql, new SingleColumnRowMapper(String.class), accountId);
        return rubies.get(0);
    }
}
