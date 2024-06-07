package com.fatima.postservice;



import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    // @Autowired
    // private JwtTokenStore jwtTokenStore;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwt = AuthTokenFilter.t;
        if (jwt != null) {
            requestTemplate.header("Authorization", "Bearer " + jwt);
        }
    }
}