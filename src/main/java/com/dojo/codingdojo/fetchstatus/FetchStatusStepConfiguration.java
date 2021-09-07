package com.dojo.codingdojo.fetchstatus;

import com.dojo.codingdojo.fetchmarketplace.reader.MarketplaceFetcher;
import com.dojo.codingdojo.fetchmarketplace.writer.MarketplaceWriter;
import com.dojo.codingdojo.fetchstatus.reader.StatusFetcher;
import com.dojo.codingdojo.fetchstatus.writer.StatusWriter;
import com.dojo.codingdojo.pojo.Listing;
import com.dojo.codingdojo.pojo.Marketplace;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FetchStatusStepConfiguration {
    private final StepBuilderFactory stepBuilderFactory;
    private final StatusFetcher statusFetcher;
    private final StatusWriter statusWriter;

    @Bean
    public Step fetchStatusStep() {
        return stepBuilderFactory
                .get("fetchStatusStep")
                .<Listing, Listing>chunk(100)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .reader(statusFetcher)
                .writer(statusWriter)
                .build();
    }
}
