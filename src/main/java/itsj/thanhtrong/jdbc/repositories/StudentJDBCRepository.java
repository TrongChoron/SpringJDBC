package itsj.thanhtrong.jdbc.repositories;

import itsj.thanhtrong.jdbc.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 7/4/2022
 * Time     : 13:32
 * Filename : StudentJDBCRepository
 */
@Repository
public class StudentJDBCRepository {
    JdbcTemplate jdbcTemplate ;

    public StudentJDBCRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setAddress(rs.getString("address"));
            return student;
        }
    }

    public List<Student> findAll() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }

    public Optional<Student> findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from student where id=?", new Object[]{
                        id
                },
                new BeanPropertyRowMapper<Student>(Student.class)));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from student where id=?", new Object[]{
                id
        });
    }

    public int insert(Student student) {
        return jdbcTemplate.update("insert into student (id, name, email, address) " + "values(?, ?, ?, ?)",
                new Object[]{
                        student.getId(), student.getName(), student.getEmail(), student.getAddress()
                });
    }

    public int update(Student student) {
        return jdbcTemplate.update("update student " + " set name = ?, email = ?, address = ? " + " where id = ?",
                new Object[]{
                        student.getName(), student.getEmail(), student.getAddress(), student.getId()
                });
    }
}
