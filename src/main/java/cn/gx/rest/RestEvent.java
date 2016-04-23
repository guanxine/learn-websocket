package cn.gx.rest;
import cn.gx.websocket.client.SocketClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * Rest 方式
 * 1. Implement the REST service to handle json payloads in HTTP Post requests
 */
@Path("cinemaevent")
public class RestEvent {

    private SocketClient client;

    private final String webSocketAddress = "ws://localhost:8080/cinemaSocket";


    public RestEvent() {
    }

    private void initializeWebSocket() throws URISyntaxException {
//ws://localhost:7101/CinemaMonitor/cinemaSocket/
        System.out.println("REST service: open websocket client at " + webSocketAddress);
        client = new SocketClient(new URI(webSocketAddress + "/0"));
    }

    private void sendMessageOverSocket(String message) {
        if (client == null) {
            try {

                initializeWebSocket();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        client.sendMessage(message);

    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String postMovieEvent(@Context Request request, String json) {
        System.out.println("received event:" + json);
        sendMessageOverSocket(json);
        return "event received " + json;
    }

    @GET
    @Produces("text/plain")
    public String getMovieEvent(@Context Request request) {
        return "nothing to report from getMovieEvent";
    }
}