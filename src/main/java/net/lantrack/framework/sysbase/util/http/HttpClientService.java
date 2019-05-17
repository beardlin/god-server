package net.lantrack.framework.sysbase.util.http;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: HttpClient 的简单实现
 */
public class HttpClientService {

    public static final Logger log = LoggerFactory.getLogger(HttpClientService.class);

    private ConnectParams connectParams;

    private RestTemplate restTemplate;

    public HttpClientService(ConnectParams connectParams) {
        this.connectParams = connectParams;
        log.debug("connectParams connection: {}", connectParams);
        restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptorsTimeout = new ArrayList<ClientHttpRequestInterceptor>();
        interceptorsTimeout.add(new HeaderRequestInterceptor());
        restTemplate.setInterceptors(interceptorsTimeout);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(15000); //15秒接受到返回值等待
        requestFactory.setConnectTimeout(5000); //5秒连接等待
        restTemplate.setRequestFactory(requestFactory);
    }

    /**
     * HttpClient get 请求
     *
     * @param apiUrl
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String apiUrl, Class<T> clazz) {
        String url ;
        if (StringUtils.isEmpty(connectParams.getUrlBase())){
            url = String.format("%s",  apiUrl);
        }else {
            url=String.format("%s/%s", connectParams.getUrlBase(), apiUrl);
        }

        log.debug(" ==> get: url = {}", url);
        return restTemplate.getForObject(url, clazz);
    }

    /**
     * HttpClient post 请求
     *
     * @param apiUrl
     * @param body
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T post(String apiUrl, Object body, Class<T> clazz) {
        return post(apiUrl, body, clazz, "POST");
    }

    /**
     * HttpClient post 请求
     *
     * @param apiUrl
     * @param body
     * @param clazz
     * @param method
     * @param <T>
     * @return
     */
    public <T> T post(String apiUrl, Object body, Class<T> clazz, String method) {

        log.debug(" ==> post: apiUrl = {},body = {},clazz = {},method = {}", apiUrl, body, clazz, method);

        if (StringUtils.isEmpty(apiUrl) || clazz == null) {
            log.error(" ==> apiUrl or clazz is null");
            return null;
        }

        String jsonBody = new Gson().toJson(body);

        if (body == null) {
            log.info(" ==> post: json body is null");
            jsonBody = "{}";
        }

        String queryParam = "";
        if (apiUrl.lastIndexOf("?") != -1) {
            queryParam = apiUrl.substring(apiUrl.lastIndexOf("?") + 1, apiUrl.length());
            apiUrl = apiUrl.substring(0, apiUrl.lastIndexOf("?"));
        }

        HttpHeaders headers = buildHeaders();

        return post(headers, apiUrl, jsonBody, clazz);

    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers = processHeaders(headers);
        return headers;
    }

    private HttpHeaders processHeaders(HttpHeaders headers) {

        //对header的处理
        if (!StringUtils.isEmpty(connectParams.getContentType())) {
            headers.set("Content-type", connectParams.getContentType());
        } else {
            headers.set("Content-type", "application/json;charset=UTF-8");
        }

        return headers;
    }


    /**
     * HttpClient post 请求
     *
     * @param headers
     * @param apiUrl
     * @param jsonBody
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T post(HttpHeaders headers, String apiUrl, String jsonBody, Class<T> clazz) {

        log.info(" ==> post: apiUrl = {},jsonBody = {}", apiUrl, jsonBody);

        String url ;
        if (StringUtils.isEmpty(connectParams.getUrlBase())){
            url = String.format("%s",  apiUrl);
        }else {
            url=String.format("%s%s", connectParams.getUrlBase(), apiUrl);
        }

        log.debug(" ==> post final url: url = {}", url);

        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);

        return restTemplate.postForObject(url, entity, clazz);

    }

    public <T> ResponseEntity<T> postForEntity(String apiUrl, MultiValueMap<String, String> body, Class<T> clazz) {

        log.info(" ==> post: apiUrl = {},body = {}", apiUrl, body);

        String url ;
        if (StringUtils.isEmpty(connectParams.getUrlBase())){
            url = String.format("%s",  apiUrl);
        }else {
            url=String.format("%s%s", connectParams.getUrlBase(), apiUrl);
        }


        log.debug(" ==> post final url: url = {},body = {}", url, body);

        HttpHeaders headers = new HttpHeaders();
        headers = processHeaders(headers);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(body, headers);

        return restTemplate.postForEntity(url, entity, clazz);

    }

    private static class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            HttpRequest wrapper = new HttpRequestWrapper(request);
            wrapper.getHeaders().set("Accept-charset", "utf-8");
            return execution.execute(wrapper, body);
        }
    }
}
