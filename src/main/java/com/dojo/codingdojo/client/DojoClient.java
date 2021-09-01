package com.dojo.codingdojo.client;

import com.dojo.codingdojo.pojo.Listing;
import com.dojo.codingdojo.pojo.Marketplace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${spring.feign.name}", url = "${spring.feign.url}")
public interface DojoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/listingStatus?key=63304c70")
    List<Listing> getListings();

    @RequestMapping(method = RequestMethod.GET, value = "/marketplace?key=63304c70")
    List<Marketplace> getMarketplaces();
}
