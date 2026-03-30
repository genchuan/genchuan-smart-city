package cn.iocoder.yudao.module.park.service.payment.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Service;

@Service
public class AlipayPayService {

    /**
     * 生成支付宝支付页面（PC）
     */
    public String createPagePay(String orderNo, String subject, String totalAmount) throws Exception {

        AlipayTradePagePayResponse response =
                Factory.Payment.Page()
                        .pay(subject, orderNo, totalAmount, "");

        return response.getBody(); // 返回给前端，直接渲染
    }
}
