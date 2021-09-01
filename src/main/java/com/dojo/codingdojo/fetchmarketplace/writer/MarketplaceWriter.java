package com.dojo.codingdojo.fetchmarketplace.writer;

import com.dojo.codingdojo.pojo.Marketplace;
import com.dojo.codingdojo.repository.PrimaryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketplaceWriter implements ItemWriter<Marketplace> {
    private final PrimaryRepository primaryRepository;

    @Override
    public void write(List<? extends Marketplace> list) throws Exception {
        list.forEach(primaryRepository::saveMarketplace);
    }
}
