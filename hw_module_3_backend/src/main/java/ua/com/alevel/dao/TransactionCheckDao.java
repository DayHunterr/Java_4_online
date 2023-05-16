package ua.com.alevel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.alevel.dto.TransactionCheckDTO;

import java.util.List;


@Component

public class TransactionCheckDao{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public TransactionCheckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TransactionCheckDTO> transaction(Long id){
        return jdbcTemplate.query("SELECT * FROM Transactions WHERE account_id = ? ", new Object[]{id}, new TransactionCheckMapper())
                .stream().toList();
    }



}
