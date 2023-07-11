package nom.youcanwell.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nom.youcanwell.order.dto.OrderDto;
import nom.youcanwell.order.repository.OrderRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    static final String cid = "TC0ONETIME";
    static final String authorization = "KakaoAK f37199016ddec4ca0e507abe39881bc4";
    String readyUrl = "https://kapi.kakao.com/v1/payment/ready";
    String approveUrl = "https://kapi.kakao.com/v1/payment/approve";
    static final String successUrl = "http://localhost:8080/order/success";
    static final String cancelUrl = "http://localhost:8080/order/cancel";
    static final String failUrl = "http://localhost:8080/order/fail";

    private final OrderRepository orderRepository;

    public OrderDto.OrderReadyResponse startKakaoPay() {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "orderNum_1"); // 주문번호
        parameters.add("partner_user_id", "memberId_1"); // 맴버아이디
        parameters.add("item_name", "youcanwell"); //상품명
        parameters.add("quantity", "1"); // 상품수량
        parameters.add("total_amount", "10000"); //결재 총액
        parameters.add("vat_amount", "200"); //상품 비과세 금액
        parameters.add("tax_free_amount", "0"); // 상품 부가세 금액
        parameters.add("approval_url", successUrl);
        parameters.add("cancel_url", cancelUrl);
        parameters.add("fail_url", failUrl);

        log.info("주문한 맴버아이디:" + parameters.get("partner_user_id"));
        // 보낼 파라미터와 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에게 보낼 URL
        RestTemplate template = new RestTemplate();

        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        OrderDto.OrderReadyResponse ready = template.postForObject(readyUrl, requestEntity, OrderDto.OrderReadyResponse.class);
        log.info("결재승인 응답객체: " + ready);

        return ready;
    }

    public OrderDto.ApproveResponse approveKakaoPay(String pgtoken, String tid) {

        //카카오톡에서 요청하는 기본 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", tid);
        parameters.add("partner_order_id", "orderNum_1"); //주문번호 수정예정
        parameters.add("partner_user_id", "memberId_1");
        parameters.add("pg_token", pgtoken);

        log.info("결제승인 요청을 인증하는 토큰: " + pgtoken);
        log.info("결재고유 번호: " + tid);
        //log.info("주문정보: " + order);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        OrderDto.ApproveResponse approveResponse = restTemplate.postForObject(approveUrl, requestEntity, OrderDto.ApproveResponse.class);

        return approveResponse;


    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
