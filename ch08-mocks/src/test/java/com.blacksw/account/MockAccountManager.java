package com.blacksw.account;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager {
    private final Map<String, Account> accounts = new HashMap<>();

    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }

    @Override
    public Account findAccountForUser(String userId) {
        return accounts.get(userId);
    }

    @Override
    public void updateAccount(Account account) {
        // nothing..
    }
}
