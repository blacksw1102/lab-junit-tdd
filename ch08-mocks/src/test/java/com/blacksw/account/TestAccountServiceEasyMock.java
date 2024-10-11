package com.blacksw.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountServiceEasyMock {
    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        // 계좌 생성
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        // 모의 객체 행동 설정
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);
        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        // 모의 객체 준비
        replay(mockAccountManager);

        // 의존성 주입
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        // 송금
        accountService.transfer("1", "2", 50);

        // 결과 검증
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterEach
    public void tearDown() {
        verify(mockAccountManager);
    }

}
