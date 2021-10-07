package com.dojo.codingdojo.remotedump.partitioner;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class MultiFileWriterPartitioner implements Partitioner {

    private static final String PARTITION_KEY = "partition";

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>(gridSize);
        ExecutionContext context = new ExecutionContext();

        context.putString("opFileName", "output/remote_persons.csv");
        map.put(PARTITION_KEY , context);

        for (int i = 1; i <= 3; i++) {
            context = new ExecutionContext();
            context.putString("opFileName", "output/remote_persons" + i + ".csv");
            map.put(PARTITION_KEY + i, context);
        }
        return map;
    }
}
