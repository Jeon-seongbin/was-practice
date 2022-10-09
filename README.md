
# 프로젝트 설명
## 계산기 웹 프로그램

1. 클라이언트 에게서 Request받아 처리를 받음

`curl 'localhost:8081/calculator?operand1=10&operator=/&operand2=5'`

2. 스레드 풀을 이용하여 클라이언트에서 받은 Request를 처리
```java
    public void start() throws IOException {
        // 서버에서 소켓을 연다
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port", port);

            // 클라이언트를 대기한다
            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");
            // 클라이언트를 기다린다
            while ((clientSocket = serverSocket.accept()) != null) { // accept
                logger.info("[CustomWebApplicationServer] client connected.");
                
                // 스레드 풀을 이용하여 안정적인 서비스
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
```


```java
    @Override
    public void run() {
        logger.info("[ClientRequestHandler] new client {} was started.", Thread.currentThread().getName());
        try (InputStream in = clientSocket.getInputStream();
             OutputStream out = clientSocket.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);


            HttpRequest httpRequest = new HttpRequest(br);
            boolean isGetRequest = httpRequest.isGetRequest();
            boolean isMatch = httpRequest.matchPath("/calculator");
            if (isGetRequest && isMatch) {
                // 값 추출
                QueryStrings queryStrings = httpRequest.getQueryStrings();
                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculator(operand1, operator, operand2);

                byte[] body = String.valueOf(result).getBytes();
                HttpResponse httpResponse = new HttpResponse(dos);
                httpResponse.response200Header("application/json", body.length);
                httpResponse.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
```
3. HTTP 문법에 맞추어 파싱
```java
    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];
        if (2 == urlPathTokens.length) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }
```
```java
    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        Arrays.stream(queryStringTokens).forEach(obj -> {
            String[] values = obj.split("=");
            if (values.length != 2) {
                throw new IllegalArgumentException("wrong queryString");
            }
            queryStringList.add(new QueryString(values[0], values[1]));
        });
    }

```
4. TDD를 활용한 개발
 - QueryStringsTest.java
 - QueryStringTest.java
 - RequestLineTest.java

