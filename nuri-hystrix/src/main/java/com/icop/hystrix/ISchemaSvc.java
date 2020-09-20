package com.icop.hystrix;

import com.icop.hystrix.impl.SchemaSvcImpl;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */

@FeignClient(value = "icop-schema", fallback = SchemaSvcImpl.class)
public interface ISchemaSvc {


}
