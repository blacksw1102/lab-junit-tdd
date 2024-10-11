package com.blacksw;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest extends ManagedWebClient {

    @Test
    public void testForm() throws IOException {
        // 페이지 가져옴
        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        // form 가져옴
        HtmlForm form = page.getFormByName("validated_form");
        // input 가져옴
        HtmlTextInput input = form.getInputByName("in_text");
        // input에 value 초기화
        input.setValueAttribute("typing...");
        // submit 버튼 가져옴
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        // 딸깍 => submit 이벤트 호출로 페이지 이동
        HtmlPage resultPage = submitButton.click();
        // 이동한 페이지의 타이틀 검증
        WebAssert.assertTitleEquals(resultPage, "Result");
    }

    @Test
    public void testFormAlert() throws IOException {
        // alert 핸들러 적용
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");

        // 버튼 클릭 결과를 resultPage에 저장한다.
        // 경고창의 제목과, 내용을 검증한다.
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, page.getTitleText());
        WebAssert.assertTextPresent(resultPage, page.asText());

        // 예상 경고 문구를 검증한다.
        List<String> collectedAlerts = alertHandler.getCollectedAlerts();
        List<String> expectedAlerts = Collections.singletonList("Please enter a value.");
        assertEquals(expectedAlerts, collectedAlerts);
    }

    @Test
    public void testFormNoAlert() throws IOException {
        // alert 핸들러 적용
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        // page 읽어옴
        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        
        // input 태그에 value 타이핑
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");
        
        // submit 이벤트로 페이지 이동 => 이동한 페이지의 타이틀 검증
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, "Result");
        
        // 발생한 alert이 없는지 검증
        assertTrue(alertHandler.getCollectedAlerts().isEmpty(), "No alerts expected");
    }

}
