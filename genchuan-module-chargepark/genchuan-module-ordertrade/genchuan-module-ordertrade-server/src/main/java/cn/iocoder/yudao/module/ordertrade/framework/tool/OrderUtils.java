package cn.iocoder.yudao.module.ordertrade.framework.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class OrderUtils {
    /**
     * 生成退款订单号：格式 RF + yyyyMMddHHmmss + 6位随机数
     */
    public static String generateRefundNo() {
        // 1. 获取当前时间（精确到秒）
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // 2. 生成6位高效随机数（ThreadLocalRandom 性能优于 Random）
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999);

        // 3. 拼接前缀 RF (Refund)
        return "RF" + timestamp + randomNum;
    }

    public static void main(String[] args) {
        System.out.println(generateRefundNo());
        // 输出示例：RF20260416170855123456
    }
}
