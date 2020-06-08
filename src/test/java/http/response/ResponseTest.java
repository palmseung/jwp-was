package http.response;

import http.request.Headers;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static http.response.ContentType.HTML;
import static http.response.HttpStatus.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponseTest {
    @DisplayName("Response에 Cookies 추가")
    @Test
    void addCookies() {
        //given
        Cookies cookies = createCookies();
        Response response = createResponse();

        //when
        response.addCookies(cookies);

        //then
        assertThat(response.getCookies().getSize()).isEqualTo(2);
        assertThat(response.getCookies().toString())
                .isEqualTo(cookies.toString());
    }

    @DisplayName("Response에 Cookie 추가 - 저장된 Cookie가 없을 때 추가하는 경우")
    @Test
    void addCookie() {
        //given
        Cookie cookie = new Cookie("logined=true", "/", true);
        Response response = createResponse();

        //when
        response.addCookie(cookie);

        //then
        assertThat(response.getCookies().getSize()).isEqualTo(1);
        assertThat(response.getCookies().toString()).isEqualTo("logined=true; Path=/; HttpOnly");
    }

    private Response createResponse() {
        return new Response(OK, HTML, new Headers(new HashMap<>()), new ResponseBody(Strings.EMPTY.getBytes()));
    }

    private Cookies createCookies() {
        Cookie cookie = new Cookie("JSESSIONID=abc1234", "/", true);
        Cookie cookie2 = new Cookie("JSESSIONID2=abc12345", "/index", false);
        return new Cookies(Arrays.asList(cookie, cookie2));
    }
}
