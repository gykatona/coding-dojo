package com.dojo.codingdojo.fetchstatus.reader;

import com.dojo.codingdojo.client.DojoClient;
import com.dojo.codingdojo.client.reader.BufferedApiReader;
import com.dojo.codingdojo.pojo.Listing;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusFetcher extends BufferedApiReader<Listing> {
    public StatusFetcher(DojoClient dojoClient) {
        super(dojoClient);
    }

    @Override
    protected List fetchItems() {
        return dojoClient.getListings(key);
    }
}
