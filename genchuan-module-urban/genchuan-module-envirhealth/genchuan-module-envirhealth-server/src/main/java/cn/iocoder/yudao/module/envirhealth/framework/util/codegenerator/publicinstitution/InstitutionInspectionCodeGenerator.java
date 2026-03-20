package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.InstitutionInspectionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/13 17:25
 */
@Component
public class InstitutionInspectionCodeGenerator {

    @Resource
    private InstitutionInspectionMapper institutionInspectionMapper;

    /**
     * 生成inspection_id（格式：uuid-inspection- + 3位序号，如 uuid-inspection-001）
     */
    public String generateInspectionId() {
        // 1. 查询全局最大序号
        Integer maxSeq = institutionInspectionMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接inspection_id
        return String.format("uuid-inspection-%03d", newSeq);
    }
}