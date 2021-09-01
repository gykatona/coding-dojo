package com.dojo.codingdojo.fetchmarketplace;

import com.dojo.codingdojo.fetchmarketplace.reader.MarketplaceFetcher;
import com.dojo.codingdojo.fetchmarketplace.writer.MarketplaceWriter;
import com.dojo.codingdojo.pojo.Marketplace;
import com.dojo.codingdojo.pojo.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FetchMarketplaceStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final MarketplaceFetcher marketplaceFetcher;
    private final MarketplaceWriter marketplaceWriter;

    @Bean
    public Step fetchMarketplaceStep() {
        return stepBuilderFactory
                .get("fetchMarketplaceStep")
                .<List<Marketplace>, Marketplace>chunk(100)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .reader(marketplaceFetcher)
                .writer(marketplaceWriter)
                .build();
    }
}
