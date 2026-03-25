package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.commercialstreet;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.commercialstreet.CommercialStreetMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CommercialStreetCodeGenerator {

    @Resource
    private CommercialStreetMapper commercialStreetMapper;

    /**
     * 生成street_id（格式：uuid-street- + 3位序号，如 uuid-street-001）
     */
    public String generateStreetId() {
        // 1. 查询全局最大序号
        Integer maxSeq = commercialStreetMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接street_id
        return String.format("uuid-street-%03d", newSeq);
    }
}