package webserver.handler.custom;

import db.DataBase;
import http.request.Request;
import http.request.headers.Headers;
import http.request.requestline.QueryStrings;
import http.response.headers.ContentType;
import http.response.responseline.HttpStatus;
import http.response.Response;
import http.response.body.ResponseBody;
import model.User;
import utils.FileIoUtils;
import webserver.handler.Handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class UserCreateHandler implements Handler {
    private String url;

    public UserCreateHandler(String url) {
        this.url = url;
    }

    @Override
    public boolean isSameUrl(String url) {
        return this.url.equals(url);
    }

    @Override
    public Response work(Request request) throws IOException, URISyntaxException {
        Map<String, String> queryStrings = QueryStrings.parseQueryStrings(request.getRequestBody().getBody());
        DataBase.addUser(User.of(new QueryStrings(queryStrings)));
        Map<String, String> headers = new HashMap<>();
        headers.put("Location", "/index.html");
        return new Response(HttpStatus.OK, ContentType.HTML, new Headers(headers), new ResponseBody(getBody()));
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    private byte[] getBody() throws IOException, URISyntaxException {
        return FileIoUtils.loadFileFromClasspath("./templates/index.html");
    }
}