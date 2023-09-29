package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {


    @Test
    void checked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    static class Controller {
        // 에러 처리를 못하고 계속 던지기만 하는 모습
        Service service = new Service();
        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        /**
         * ConnectionException, SQLException 둘 다, 에러가 터지는 로직을 작성
         * 둘 다, 에러를 던져야 한다. 번거로움
         */
        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("연결 오류");
        }
    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("SQL 오류");
        }
    }
}
