package com.blacksw.account;

import com.blacksw.confiigurations.MockConfiguration;
import org.junit.jupiter.api.Test;

public class TestDefaultAccountManager {

    @Test
    public void testFindAccountByUser() {
        MockLog logger = new MockLog();
        MockConfiguration configuration = new MockConfiguration();
        configuration.setSQL("SELECT * [...]");
        DefaultAccountManager2 accountManager = new DefaultAccountManager2(logger, configuration);

        Account account = accountManager.findAccountForUser("1234");

    }
}
