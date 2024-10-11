package com.blacksw.extension;

import com.blacksw.jdbc.ConnectionManager;
import com.blacksw.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

public class DatabaseOperationExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private Connection connection;
    private Savepoint savepoint;

    // 모든 테스트를 실행하기 전에 수행하는 메서드다.
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    // 모든 테스트를 실행한 후에 수행하는 메서드다.
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }

    // 각 테스트를 실행하기 전 수행하는 메서드다.
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        // 오토 커밋을 해제해서 테스트 후, 테스트 전으로 롤백할 수 있게 설정한다.
        connection.setAutoCommit(false);
        // 현 트랜잭션 상태를 저장해준다.
        savepoint = connection.setSavepoint("savepoint");
    }


    // 각 테스트를 실행한 후 수행하는 메서드다.
    // 테스트를 종료하고 나면 sql 실행 내용을 원복한다.
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.rollback();
    }
}
