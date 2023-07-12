package nom.youcanwell.order.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nom.youcanwell.order.dto.OrderDto;
import nom.youcanwell.order.entity.Order;
import nom.youcanwell.order.mapper.OrderMapper;
import nom.youcanwell.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private String tid;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    //결제 준비
    @GetMapping("/ready")
    public @ResponseBody ResponseEntity startContract(@RequestBody OrderDto.OrderPostinfo postinfo) throws IOException {
        log.info("주문정보 ={}", postinfo.getItemName()); log.info("결재 총액 ={}", postinfo.getPrice());
        Order order = orderMapper.postInfoToOrder(postinfo);
        OrderDto.OrderReadyResponse readyForPay = orderService.startKakaoPay(order);
        orderService.saveTid(order,readyForPay.getTid());
        this.tid = readyForPay.getTid();
        return new ResponseEntity(readyForPay, HttpStatus.ACCEPTED);
    }
    //결제 승인
    @GetMapping("/success")
    public ResponseEntity afterQR(@RequestParam String pg_token) {
        OrderDto.ApproveResponse approveResponse = orderService.approveKakaoPay(pg_token, tid);
        log.info("결제 승인 응답결과 = {}", approveResponse);

        return new ResponseEntity<>(approveResponse, HttpStatus.CREATED);
    }

    // 결제 취소
    @GetMapping("/cancel")
    public String payCancel() {
        return "redirect:/";
    }

    //결제 실패
    // 결제 실패시 실행 url
    @GetMapping("/fail")
    public String payFail() {
        return "redirect:/";
    }


}
