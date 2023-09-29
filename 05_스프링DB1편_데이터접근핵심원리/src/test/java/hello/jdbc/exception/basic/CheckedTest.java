package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    // 예외를 던지는 경우에 대한 테스트 케이스
    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    /**
     * Exception을 상속 받은 예외는 체크 예외가 된다.
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     *  Check 예외는
     *  예외를 잡아서 처리하거나, 던지거나 둘 중 하나를 필수로 선택해야 한다.
     */
    static class Service {
        Repository repository = new Repository();
        /**
         *  예외를 잡아서 처리하는 코드
         */
        // 예외를 잡는 코드
        public void callCatch() {
            try {
                repository.call(); // call을 호출할 때, 오류가 발생하는데 이거를 잡거나 던져야 한다. 이 케이스에서는 잡기로 결정
            } catch (MyCheckedException e) {
                // 예외 처리 로직. 예외를 잡아서 로그를 남겨주기
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }
        // 체크 예외를 잡지 않고 던지는 코드
        // 체크 예외는 예외를 잡지 않고 밖으로 던지려면 반드시 throws 예외를 메서드에 선언해야 한다.
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException { // 체크 예외이기 때문에 에러를 무조건 잡거나 던져야 하는데,
            // 이 코드에서는 MyCheckedException 에러를 던졌다, 던지는 거면 반드시 선언해줘야 한다. 선언 안해주면 컴파일이 안 됨.
            throw new MyCheckedException("ex");
        }
    }
}
