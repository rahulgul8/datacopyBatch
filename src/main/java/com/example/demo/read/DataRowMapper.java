package com.example.demo.read;

import com.example.demo.read.dto.ReadDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DataRowMapper implements RowMapper<ReadDTO> {

    @Override
    public ReadDTO mapRow(final ResultSet rs, final int rowNum) {
        ReadDTO student = new ReadDTO();
        try {
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
