package com.dojo.codingdojo.client;

import com.dojo.codingdojo.pojo.Listing;
import com.dojo.codingdojo.pojo.Marketplace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "dojo", url = "${spring.feign.url}")
public interface DojoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/listingStatus")
    List<Listing> getListings(@RequestParam String key);

    @RequestMapping(method = RequestMethod.GET, value = "/marketplace")
    List<Marketplace> getMarketplaces(@RequestParam String key);
}
