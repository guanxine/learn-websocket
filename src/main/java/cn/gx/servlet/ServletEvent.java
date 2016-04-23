package cn.gx.servlet;

import cn.gx.util.WebSocketUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * servlet 方式
 * Created by guanxine on 16-4-23.
 */
@WebServlet(name = "ServletEvent",value = "/servlet")
public class ServletEvent extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 模拟一个 json 字符串
        String jsonStr="{\n" +
                "    \"room\":\"2\",\n" +
                "    \"occupation\":\"12\"\n" +
                "}";
        WebSocketUtil.sendMessage2WebSocket("cinemaSocket/1",jsonStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
