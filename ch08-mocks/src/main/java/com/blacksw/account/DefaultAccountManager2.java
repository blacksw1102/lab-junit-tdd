package com.blacksw.account;

import com.blacksw.configurations.Configuration;
import com.blacksw.configurations.DefaultConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultAccountManager2 implements AccountManager {
    private Log logger;
    private Configuration configuration;

    public DefaultAccountManager2() {
        this(LogFactory.getLog(DefaultAccountManager2.class), new DefaultConfiguration("technical"));
    }

    public DefaultAccountManager2(Log logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    @Override
    public Account findAccountForUser(String userId) {
        this.logger.debug("Getting account for user [" + userId + "]");
        this.configuration.getSQL("FIND_ACCOUNT_FOR_USER");
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        // nothing..
    }
}
