package com.jzo2o.orders.manager.service.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jzo2o.api.foundations.ServeApi;
import com.jzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author huajieyu
 * @Date 2026/3/6 17:20
 * @Version 1.0
 * @Description TODO
 */
@Component
@Slf4j
public class FoundationsClient {

    @Resource
    private ServeApi serveApi;

    @SentinelResource(value = "getServeDetail", fallback = "detailFallback", blockHandler = "detailBlockHandler")
    public ServeAggregationResDTO getDetail(Long id) {
        log.info("FoundationsClient.getDetail:{}", id);
        ServeAggregationResDTO resDTO = serveApi.findById(id);
        return resDTO;
    }

    public ServeAggregationResDTO detailFallback(Long id, Throwable throwable) {
        log.error("FoundationsClient.detailFallback:{}, throwable:", id, throwable);
        return null;
    }

    public ServeAggregationResDTO detailBlockHandler(Long id, BlockException blockException) {
        log.error("FoundationsClient.detailBlockHandler:{}, blockException:", id, blockException);
        return null;
    }
}
