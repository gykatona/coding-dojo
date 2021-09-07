package com.dojo.codingdojo.client.reader;

import com.dojo.codingdojo.client.DojoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@Slf4j
public abstract class BufferedApiReader<T> implements ItemReader<T> {

    private final Queue<T> items = new ConcurrentLinkedQueue<>();
    private long total;
    private long processedCount;
    @Value("${spring.feign.key}")
    protected String key;
    protected final DojoClient dojoClient;

    @Override
    public T read() {
        initMarketplaces();

        processedCount++;
        return items.poll();
    }

    private void initMarketplaces() {
        if (items.isEmpty() && (total > processedCount || total == 0)) {
            items.addAll(fetchItems());
            total = items.size();
            log.info("Fetched {} items.", total);
        }
    }

    protected abstract List<T> fetchItems();

}
