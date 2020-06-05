package webserver;

import http.request.Request;
import http.request.RequestUtils;
import http.response.Response;
import http.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.IOUtils;

import java.io.*;
import java.net.Socket;

public class HandlerMapper implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(HandlerMapper.class);

    private Socket connection;

    public HandlerMapper(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            RequestUtils requestUtils = new RequestUtils(new BufferedReader(new InputStreamReader(in, "UTF-8")));
            Request request = requestUtils.getRequest();

            Handler handler = Handlers.findHandler(request);
            Response response = handler.work(request);

            IOUtils.writeData(response, out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
