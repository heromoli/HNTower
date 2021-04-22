package com.nokia.common.advice;

import com.nokia.utils.RData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.nokia.common.annotation.IgnoreResponse;

@RestControllerAdvice
public class CommonResponseData implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否过滤
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //过滤掉 指定返回值的class、 METHOD
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponse.class))
            return false;
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        RData response = RData.ok();
        if (null == o) {
            return response;
        } else if (o instanceof RData) {
            response = (RData) o;
        } else {
            response.put("data", o);
        }

        return response;
    }
}
