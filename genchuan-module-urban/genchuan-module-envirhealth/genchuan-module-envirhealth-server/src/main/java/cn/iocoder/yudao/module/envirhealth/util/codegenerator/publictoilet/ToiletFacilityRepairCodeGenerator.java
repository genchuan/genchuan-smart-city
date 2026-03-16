package cn.iocoder.yudao.module.envirhealth.util.codegenerator.publictoilet;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletFacilityRepairMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/4 15:41
 */
@Component
public class ToiletFacilityRepairCodeGenerator {
    @Resource
    private ToiletFacilityRepairMapper toiletComplaintFacilityMapper;

    /**
     * 生成repair_id（格式：uuid-repair- + 3位序号，如 uuid-repair-001）
     */
    public String generateRepairId() {
        // 1. 查询全局最大序号
        Integer maxSeq = toiletComplaintFacilityMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接repair_id
        return String.format("uuid-repair-%03d", newSeq);
    }
}