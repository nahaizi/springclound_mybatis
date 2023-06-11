package com.lili.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.model.RestResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lili.util.ApiResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Order(-1)
@Component
public class CustomWebExceptionHandler implements WebExceptionHandler {
    private static final Set<String> DISCONNECTED_CLIENT_EXCEPTIONS;
    //排除部份系统级的异常
    static {
        Set<String> exceptions = new HashSet<>();
        exceptions.add("AbortedException");
        exceptions.add("ClientAbortException");
        exceptions.add("EOFException");
        exceptions.add("EofException");
        DISCONNECTED_CLIENT_EXCEPTIONS = Collections.unmodifiableSet(exceptions);
    }

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted() || isDisconnectedClientError(ex)) {
            return Mono.error(ex);
        }
        ServerHttpRequest request = exchange.getRequest();
        String rawQuery = request.getURI().getRawQuery();
        String query = StringUtils.hasText(rawQuery) ? "?" + rawQuery : "";
        String path = request.getPath() + query ;
        String message ;
        HttpStatus status = determineStatus(ex);
        if (status == null){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // 通过状态码自定义异常信息
        if (status.value() >= 400 && status.value() < 500){
            message = "路由服务不可达或禁止访问！";
        }else {
            message = "路由服务异常！";
        }
        message += " path：" + path;
        log.info("message ================> " + message);
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
//        message = new String(message.getBytes("GBK"),"UTF-8");
        String jsonMsg = mapper.writeValueAsString(new ApiResult(1, message, false, message,new Date()));
        //工具类输出json字符串
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(status);
        DataBufferFactory bufferFactory = serverHttpResponse.bufferFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        DataBuffer wrap = bufferFactory.wrap(objectMapper.writeValueAsBytes(new RestResult<>(408, jsonMsg)));
        return serverHttpResponse.writeWith(Mono.fromSupplier(() -> wrap));

    }

    @Nullable
    protected HttpStatus determineStatus(Throwable ex) {
        if (ex instanceof ResponseStatusException) {
            return ((ResponseStatusException) ex).getStatus();
        }
        return null;
    }

    private boolean isDisconnectedClientError(Throwable ex) {
        return DISCONNECTED_CLIENT_EXCEPTIONS.contains(ex.getClass().getSimpleName())
                || isDisconnectedClientErrorMessage(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
    }

    private boolean isDisconnectedClientErrorMessage(String message) {
        message = (message != null) ? message.toLowerCase() : "";
        return (message.contains("broken pipe") || message.contains("connection reset by peer"));
    }
}
