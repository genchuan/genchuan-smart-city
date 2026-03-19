package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.InstitutionProblemMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/13 17:25
 */
@Component
public class InstitutionProblemCodeGenerator {

    @Resource
    private InstitutionProblemMapper institutionProblemMapper;
    /**
     * 生成problem_id（格式：uuid-problem_id- + 3位序号，如 uuid-problem_id-001）
     */
    public String generateProblemId() {
        // 1. 查询全局最大序号
        Integer maxSeq = institutionProblemMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接problem_id_id
        return String.format("uuid-problem-%03d", newSeq);
    }
}