package com.icop.gateway.config;

import com.icop.base.entities.R;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.AbstractDefaultGlobalExceptionProcessor;
import com.icop.base.exception.MyException;
import com.icop.base.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/13
 * @description：
 */
@Configuration
@Slf4j
public class GlobalExceptionProcessor  extends AbstractDefaultGlobalExceptionProcessor  implements ErrorWebExceptionHandler  {

    /**
     * MessageReader
     */
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    /**
     * MessageWriter
     */
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    /**
     * ViewResolvers
     */
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    /**
     * 存储处理异常后的信息
     */
    private ThreadLocal<R> exceptionHandlerResult = new ThreadLocal<>();

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        R r ;
        if (ex instanceof MyException) {
            r = super.myExceptionProcessor((MyException) ex);
        }else if (ex instanceof NotFoundException) {
            r = super.myExceptionProcessor(new MyException(ExceptionCode.NOT_FOUND));
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            r = super.exceptionProcessor(responseStatusException);
        } else {
            r = super.exceptionProcessor(ex);
        }

        //参考AbstractErrorWebExceptionHandler
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        exceptionHandlerResult.set(r);

        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), t -> this.renderErrorResponse(t))
                .route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));

    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        R result ;
        int status = ExceptionCode.INTERNAL_SERVER_ERROR.getCode();
        try{
            result = exceptionHandlerResult.get();
            if(result != null){
                status = result.getCode();
            }
        }finally {
            exceptionHandlerResult.remove();
        }
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private Mono<? extends Void> write(ServerWebExchange exchange,
                                       ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GlobalExceptionProcessor.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GlobalExceptionProcessor.this.viewResolvers;
        }

    }
}
