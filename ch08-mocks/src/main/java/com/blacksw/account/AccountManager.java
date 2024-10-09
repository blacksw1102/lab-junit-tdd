package com.blacksw.account;

public interface AccountManager {

    Account findAccountForUser(String userId);

    void updateAccount(Account account);

}
