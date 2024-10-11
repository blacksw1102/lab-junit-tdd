package com.blacksw.extension;

import com.blacksw.jdbc.ConnectionManager;
import com.blacksw.jdbc.PassengerDao;
import com.blacksw.jdbc.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

// JUnit 5의 테스트 과정에서 PassengerDao 타입의 객체를 자동으로 주입해주는 역할을 수행한다.
public class DataAccessObjectParameterResolver implements ParameterResolver {

    // 현재 타겟 파라미터가 Resolver가 지원하는 파라미터인지를 검증한다.
    // 검증 결과가 true일 시, resolveParameter 메서드를 호출해서 객체를 주입한다.
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter()
                .getType()
                .equals(PassengerDao.class);
    }

    // 객체를 주입한다.
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.getConnection());
    }

}
