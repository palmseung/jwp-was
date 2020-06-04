package http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PathTest {
    @DisplayName("Path 생성 - No QueryStrings")
    @Test
    void createPath() {
        //given
        String strPath = "/users";

        //when
        Path path = new Path(strPath);

        //then
        assertThat(path).isEqualTo(new Path(strPath));
    }

    @DisplayName("Path 생성 - With QueryStrings")
    @Test
    void createPathWithQueryStrings(){
        //given
        String strPath = "/users?userId=javajigi&password=password&name=JaeSung";

        //when
        Path path = new Path(strPath);

        //then
        assertThat(path.getPath()).isEqualTo("/users");
        assertThat(path.getQueryStrings().get("userId")).isEqualTo("javajigi");
        assertThat(path.getQueryStrings().get("password")).isEqualTo("password");
        assertThat(path.getQueryStrings().get("name")).isEqualTo("JaeSung");
    }
}