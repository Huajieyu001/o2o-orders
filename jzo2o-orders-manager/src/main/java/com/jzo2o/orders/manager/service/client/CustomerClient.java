package com.jzo2o.orders.manager.service.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jzo2o.api.customer.AddressBookApi;
import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author huajieyu
 * @Date 2026/3/6 14:13
 * @Version 1.0
 * @Description TODO
 */
@Component
@Slf4j
public class CustomerClient {

    @Resource
    private AddressBookApi addressBookApi;

    @SentinelResource(value = "getAddressBookDetail", fallback = "detailFallback", blockHandler = "detailBlockHandler")
    public AddressBookResDTO getDetail(Long id) {
        log.info("根据id查询地址");
        AddressBookResDTO detail = addressBookApi.detail(id);
        return detail;
    }

    public AddressBookResDTO detailFallback(Long id, Throwable throwable) {
        log.error("非限流、熔断等导致的异常执行的降级方法，id：{}， throwable:", id, throwable);
        return null;
    }

    public AddressBookResDTO detailBlockHandler(Long id, BlockException blockException) {
        log.error("限流、熔断等导致的异常执行的降级方法，id：{}， blockException:", id, blockException);
        return null;
    }
}
