package ua.com.alevel.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.com.alevel.dto.TransactionCheckDTO;
import ua.com.alevel.entity.CategoryType;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionCheckMapper implements RowMapper<TransactionCheckDTO> {

    @Override
    public TransactionCheckDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionCheckDTO transactionCheckDTO = new TransactionCheckDTO();
        transactionCheckDTO.setId(rs.getLong("id"));
        transactionCheckDTO.setCreated(rs.getTimestamp("created"));
        transactionCheckDTO.setAmount(rs.getInt("amount"));
        transactionCheckDTO.setType(CategoryType.valueOf(rs.getString("category")));
        transactionCheckDTO.setDescription(rs.getString("description"));

        return transactionCheckDTO;
    }
}
