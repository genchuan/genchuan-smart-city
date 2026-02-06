package cn.iocoder.yudao.module.park.controller.admin.payment.alipay;


import cn.iocoder.yudao.module.park.service.payment.alipay.AlipayPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "支付宝后台 - 支付宝")
@Validated
@RestController
@RequestMapping("/park/pay")
public class PayController {

    private final AlipayPayService alipayPayService;

    public PayController(AlipayPayService alipayPayService) {
        this.alipayPayService = alipayPayService;
    }

    @GetMapping("/alipay")
    @Operation(summary = "支付宝支付")
//    @PreAuthorize("@ss.hasPermission('park:pay:alipay')")
    public void pay(HttpServletResponse response) throws Exception {
        System.out.println("cscscscs");
//        System.out.println(response);
        String form = alipayPayService.createPagePay(
                "ORDER_20250203",
                "停车费",
                "10.00"
        );
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);
    }
}
