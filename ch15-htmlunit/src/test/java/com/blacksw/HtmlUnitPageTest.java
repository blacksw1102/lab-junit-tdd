package com.blacksw;

import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HtmlUnitPageTest extends ManagedWebClient {
    @Test
    public void homePage() throws IOException {
        // 페이지를 읽어온다.
        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

        // 페이지의 타이틀이 일치하는지 검증한다.
        assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

        // 페이지를 xml 형식으로 불러온다음 특정 태그가 포함되어 있는지를 검증한다.
        String pageAsXml = page.asXml();
        assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));

        // 페이지를 단순 텍스트로 불러온다음 특정 문구가 포함되어 있는지를 검증한다.
        String pageAsText = page.asText();
        assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
    }

    @Test
    public void testClassNav() throws IOException {
        // 페이지를 읽어온다.
        HtmlPage mainPage = webClient.getPage("http://htmlunit.sourceforge.net/apidocs/index.html");

        // 페이지에 특정 name의 Frame을 가져온다.
        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosedPage();

        // 프레임 내 존재하는 첫 번째 li태그를 가져온다.
        HtmlListItem htmlListItem = (HtmlListItem) packagePage.getElementsByTagName("li").item(0);

        // 태그내 텍스트 값이 일치하는지를 검증한다.
        assertEquals("AboutURLConnection", htmlListItem.getTextContent());
    }

}
