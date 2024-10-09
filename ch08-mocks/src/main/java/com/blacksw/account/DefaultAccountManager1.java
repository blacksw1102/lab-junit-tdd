package com.blacksw.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DefaultAccountManager1 implements AccountManager {

    private static final Log logger = LogFactory.getLog(DefaultAccountManager1.class);

    @Override
    public Account findAccountForUser(String userId) {
        logger.debug("Getting account for user [" + userId + "]");
        ResourceBundle bundle = PropertyResourceBundle.getBundle("technical");
        String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        // nothing..
    }
}
