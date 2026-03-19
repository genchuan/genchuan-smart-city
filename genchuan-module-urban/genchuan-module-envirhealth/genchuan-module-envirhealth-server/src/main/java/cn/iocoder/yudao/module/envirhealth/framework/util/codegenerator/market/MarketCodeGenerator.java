package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.market;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.market.MarketMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/16 8:46
 */
@Component
public class MarketCodeGenerator {

    @Resource
    private MarketMapper marketMapper;

    /**
     * 生成market_id（格式：uuid-market- + 3位序号，如 uuid-market-001）
     */
    public String generateMarketId() {
        // 1. 查询全局最大序号
        Integer maxSeq = marketMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接market_id
        return String.format("uuid-market-%03d", newSeq);
    }
}