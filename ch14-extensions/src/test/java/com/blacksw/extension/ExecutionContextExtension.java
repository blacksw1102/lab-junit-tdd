package com.blacksw.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

// ExecutionCondition 인터페이스를 구현한 ExecutionContextExtension 클래스
// ExecutionCondition 인터페이스는 일반적으로 특정 조건에 따라 테스트를 실행하고 싶을 때 사용한다
public class ExecutionContextExtension implements ExecutionCondition {

    // 테스트를 실행할 수 있는지 실행 조건을 평가하는 메서드
    // ExtensionContext은 JUnit 5 확장 기능 사용을 위한 컨텍스트 정보를 포함한다
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        Properties properties = new Properties();
        String context = "";

        try {
            // 클래스 로더로 context.properties 파일을 불러온다
            // 클래스 로더는 특정 클래스를 JVM(메모리에) 로드하는 역할을한다. 로드된 클래스는 인스턴스를 만들거나 메서드를 호출할 때 사용된다.
            properties.load(ExecutionContextExtension.class.getClassLoader().getResourceAsStream("context.properties"));
            // context 프로퍼티 값을 꺼내온다
            context = properties.getProperty("context");
            // context 값이 regular, low가 아니면 테스트를 실행하지못한다고 판단하다
            Set<String> validContexts = Set.of("regular", "low");
            if (!validContexts.contains(context)) {
                return ConditionEvaluationResult.disabled("Test disabled outside regular and low contexts");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        // 테스트를 실행가능하다고 판단한다
        return ConditionEvaluationResult.enabled("Test enabled on the " + context + " context");
    }
}
