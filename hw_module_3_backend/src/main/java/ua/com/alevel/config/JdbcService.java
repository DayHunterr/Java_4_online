package ua.com.alevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class JdbcService {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hw_module_3");
        dataSource.setUsername("root");
        dataSource.setPassword("1954");

        return dataSource;
    }
    @Bean
     public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(mysqlDataSource());
    }

}
