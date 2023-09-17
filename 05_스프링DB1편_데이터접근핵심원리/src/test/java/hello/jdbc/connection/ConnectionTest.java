package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {
    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, classes={}", con1, con1.getClass());
        log.info("connection={}, classes={}", con2, con2.getClass());
    }

    // 스프링에서 제공하는 DriverManager
    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 커넥션 풀링. 히카리를 사용하여
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);

    }
    private void useDataSource(DataSource dataSource) throws SQLException {
        // 위 위의 driverManager는 매 연결마다, url, username, pw를 입력해줬는데
        // DataSource는 drivermanager와 달리, 처음 세팅할 때만 정보를 설정하고, 그 후에 연결해서 쓸 때는
        // 그저 getConnection만 해주면 된다. -> Connection이라는 행위의 추상화, 설정과 사용의 분리
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, classes={}", con1, con1.getClass());
        log.info("connection={}, classes={}", con2, con2.getClass());
    }
}
