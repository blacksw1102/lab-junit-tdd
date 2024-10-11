package com.blacksw.extension;

import com.blacksw.jdbc.PassengerExistsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

// JUnit 5에서 테스트 도중 발생한 Exception에 대해서 작업을 수행하기 위한 핸들러.
public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {
    // 로깅 API를 통해서 로거 인스턴스 생성.
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        // 발생 Exception이 PassengerExistsException 일 경우, 메세지 내용을 로깅한다.
        if (throwable instanceof PassengerExistsException) {
            logger.severe("Passenger exists: " + throwable.getMessage());
        }
        throw throwable;
    }
}
