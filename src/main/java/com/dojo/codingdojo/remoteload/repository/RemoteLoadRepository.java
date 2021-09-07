package com.dojo.codingdojo.remoteload.repository;

import lombok.RequiredArgsConstructor;
import org.postgresql.copy.CopyManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;

@Repository
@RequiredArgsConstructor
public class RemoteLoadRepository {
    @Value("${remote.file}")
    private String fileName;
    private final CopyManager copyManager;

    public long loadDumpIntoTable() throws Exception {
        return copyManager.copyIn("COPY people_to_csv (last_name, first_name, age) FROM STDIN", new FileReader(fileName));
    }
}
