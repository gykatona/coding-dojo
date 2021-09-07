package com.dojo.codingdojo.fetchstatus.writer;

import com.dojo.codingdojo.pojo.Listing;
import com.dojo.codingdojo.repository.PrimaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class StatusWriter implements ItemWriter<Listing> {
    private final PrimaryRepository primaryRepository;

    @Override
    public void write(List<? extends Listing> list) throws Exception {
        list.forEach(primaryRepository::saveListing);
    }
}
