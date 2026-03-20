package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.PublicInstitutionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/11 11:39
 */
@Component
public class PublicInstitutionCodeGenerator {

    @Resource
    private PublicInstitutionMapper publicInstitutionMapper;

    /**
     * 生成institution_id（格式：uuid-insit- + 3位序号，如 uuid-insit-001）
     */
    public String generateInstitutionId() {
        // 1. 查询全局最大序号
        Integer maxSeq = publicInstitutionMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接insit_id
        return String.format("uuid-insit-%03d", newSeq);
    }
}