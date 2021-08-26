package com.dojo.codingdojo.remoteload.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RemoteLoadRepository {
    @Value("${remote.file}")
    private String fileName;
    private final JdbcTemplate jdbcTemplate;

    public void loadDumpIntoTable() {
        jdbcTemplate.execute(generateLoadSql());
    }

    private String generateLoadSql() {
        return new StringBuilder("copy people_to_csv FROM '")
                .append(fileName)
                .append("' WITH (FORMAT csv)")
                .toString();
    }
}
