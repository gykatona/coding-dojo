package com.dojo.codingdojo.fetchmarketplace.reader;

import com.dojo.codingdojo.client.DojoClient;
import com.dojo.codingdojo.pojo.Marketplace;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketplaceFetcher implements ItemReader<List<Marketplace>> {
    private final DojoClient dojoClient;

    @Override
    public List<Marketplace> read() {
        return dojoClient.getMarketplaces();
    }
}
