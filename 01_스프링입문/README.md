# java_inflearn

## 인프런 김영한님 스프링 입문 강의 코드

### 환경
    - Language: Java
    - Java: 11
    - Packaging: Jar
    - Spring Boot: 2.7.13

### 내용정리

  #### 정적 컨텐츠
  ![정적 컨텐츠](https://github.com/rlaghdtlr012/java_inflearn/assets/79968134/c73f207e-db19-446d-9a8e-5f17c726c5a9)
  
    - 정적 컨텐츠는 컨텐츠 관련 컨트롤러가 이를 처리하지 않고, 내장 톰켓 서버가 해당 컨텐츠를 html 파일로 만들어서 웹 브라우저로 뿌려주는 형태

  #### MVC와 템플릿 엔진
  ![image](https://github.com/rlaghdtlr012/java_inflearn/assets/79968134/2c270cfd-0e97-4877-b2f9-00cee6f0a114)
    
    - 웹 브라우저에서 localhost:8080/hello-mvc로 get요청이 올 경우, 내장 톰켓 서버에서 스프링 부트로 해당 내용을 넘긴다.
    - 스프링은 해당 부분이 helloController에서 특정 메서드에 매핑이 돼 있는 것을 확인 및 호출. model에 name: spring 값을 저장한 후, return 값으로 hello-template을 반환한다.
    - 이것을 viewResolver(알맞은 view를 찾아주고 템플릿 엔진을 연결시켜주는 역할)가 hello-template.html을 찾아서 렌더링을 한 후, 변환된 html 파일을 웹 브라우저에 넘겨준다.

  #### API
  ![api](https://github.com/rlaghdtlr012/java_inflearn/assets/79968134/ca82bd5b-2ca3-491f-bf80-baaaef710408)

    - @ResponseBody를 사용할 경우
    - HTTP Body에 문자 내용을 직접 반환한다.
    - 이 때는 viewResolver 대신 HttpMessageConverter라는 것이 동작을 하는데, 
      - 기본 객체 처리에는 MappingJackson2HttpMessageConverter가 쓰이고,
      - 기본 문자 청리에는 StringConverter가 쓰인다.
    - 이 JSON 객체를 웹 브라우저로 넘겨준다.

  #### 테스트 케이스 작성
    - 테스트 케이스 파일 작성은 /test 경로 하위에 작성을 하되, 파일 이름 마지막에 ~~Test라고 작명하는 것이 관례
    - 테스트 파일 안에 각 테스트케이스들은 순서가 보장되지 않고, 각 테스트 케이스들은 서로 의존 관계를 가질 수도 있다.
    - 허나 테스트 케이스는 각각 독립적으로 실행돼야 함으로 각 테스트 케이스들이 끝나면 데이터를 clear 해줘야 한다.
        
  ```java
  @AfterEach
  public void afterEach() {
      repository.clearStore();
  }
  ```
    
    - 각 테스트 케이스들이 끝나고 실행되는 callback 함수 같은 개념.
    
