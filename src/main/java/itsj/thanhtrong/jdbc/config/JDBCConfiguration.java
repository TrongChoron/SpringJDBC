package itsj.thanhtrong.jdbc.config;

import itsj.thanhtrong.jdbc.repositories.StudentJDBCRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 7/4/2022
 * Time     : 13:55
 * Filename : Configuration
 */
@Configuration
public class JDBCConfiguration {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        //dataSource.setPassword("P@ssw0rd");

        return dataSource;
    }


    @Bean
    public StudentJDBCRepository getContactDAO() {
        return new StudentJDBCRepository(getDataSource());
    }
}
