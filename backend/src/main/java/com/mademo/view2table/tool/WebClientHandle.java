package com.mademo.view2table.tool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class WebClientHandle {
    private static final Log log = LogFactory.getLog(WebClientHandle.class);
    private static final WebClient webClient = WebClient.create();

    public static  <T> Flux<T> callApi(String url, Class<T> responseType) {
        return webClient.get().uri(url)
                .retrieve()
                .bodyToFlux(responseType);
    }
}
