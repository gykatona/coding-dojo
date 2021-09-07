package com.dojo.codingdojo.fetchmarketplace.reader;

import com.dojo.codingdojo.client.DojoClient;
import com.dojo.codingdojo.client.reader.BufferedApiReader;
import com.dojo.codingdojo.pojo.Marketplace;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketplaceFetcher extends BufferedApiReader<Marketplace> {

    public MarketplaceFetcher(DojoClient dojoClient) {
        super(dojoClient);
    }

    @Override
    protected List<Marketplace> fetchItems() {
        return dojoClient.getMarketplaces(key);
    }
}
