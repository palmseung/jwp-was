package http;

import http.requestline.Path2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Path2Test {
    @DisplayName("ReuqestLine에서 URL/QuerieString 추출")
    @Test
    void getPathAndQueries() {
        //given
        String requestLine = "GET /user/create?name=seung&email=email@gmail.com HTTP/1.1";

        //when
        String url = Path2.getUrl(requestLine);
        Map<String, String> queries = Path2.getQueries(requestLine);

        //then
        assertThat(url).isEqualTo("/user/create");
        assertThat(queries.get("name")).isEqualTo("seung");
        assertThat(queries.get("email")).isEqualTo("email@gmail.com");
    }
}
