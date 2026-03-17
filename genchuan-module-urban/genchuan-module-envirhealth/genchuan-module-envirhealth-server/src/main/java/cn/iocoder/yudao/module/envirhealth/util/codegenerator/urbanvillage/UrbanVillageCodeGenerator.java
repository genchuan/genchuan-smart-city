package cn.iocoder.yudao.module.envirhealth.util.codegenerator.urbanvillage;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage.UrbanVillageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/16 13:53
 */
@Component
public class UrbanVillageCodeGenerator {

    @Resource
    private UrbanVillageMapper urbanVillageMapper;

    /**
     * 生成village_id（格式：uuid-village- + 3位序号，如 uuid-village-001）
     */
    public String generateVillageId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = urbanVillageMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接village_id
        return String.format("uuid-village-%03d", newSeq);
    }

}