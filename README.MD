# 环境

Java EE 7 (including JAX-RS and Web Socket ) and plain HTML5 and JavaScript

# 步骤

1. Implement the REST service to handle json payloads in HTTP Post requests
2. Implement the WebSocket endpoint
3. Interact from REST service endpoint with WebSocket (endpoint)
4. Implement the HTML and JavaScript web application to present the live status for the movie theater based on the web socket messages

# 测试

浏览器访问：`http://localhost:8080/learn-websocket/websocket-client.html`

## 通过 restful service 进行访问
```
curl -i -X POST -H "Content-Type:application/json" -d '{"room":"2","occupation":"12"}'  http://localhost:8080/learn-websocket/rest/cinemaevent
```

## 通过 Servlet 进行访问

```
// servlet 中模拟了 json 数据
curl -i -X POST -H "Content-Type:application/json" -d '{"room":"2","occupation":"12"}'  http://localhost:8080/learn-websocket/servlet
```