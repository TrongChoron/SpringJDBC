package itsj.thanhtrong.jdbc;

import itsj.thanhtrong.jdbc.entities.Student;
import itsj.thanhtrong.jdbc.repositories.StudentJDBCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentJDBCRepository studentJDBCRepository;

    @Override
    public void run(String... args) throws Exception {
        H2jdbcCreateDemo.runDB();

        logger.info("Inserting -> {}", studentJDBCRepository.insert(new Student(1000L, "Trong",  "ngoc@gmail.com","36 Le Thi Hong")));
        logger.info("Inserting -> {}", studentJDBCRepository.insert(new Student(1002L, "Thanh",  "longpdh@gmail.com","144 Phan Van Tri")));
        logger.info("Inserting -> {}", studentJDBCRepository.insert(new Student(1003L, "Huy", "thu@gmail.com","146 Le Duc Tho")));

        logger.info("Employee id 10011 -> {}", studentJDBCRepository.findById(1000L));

        logger.info("Update 10011 -> {}", studentJDBCRepository.update(new Student(1000L, "Ngoc", "Nguyen Bao", "baongoc@gmail.com")));

        studentJDBCRepository.deleteById(10013L);

        logger.info("All users -> {}", studentJDBCRepository.findAll());
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

}
