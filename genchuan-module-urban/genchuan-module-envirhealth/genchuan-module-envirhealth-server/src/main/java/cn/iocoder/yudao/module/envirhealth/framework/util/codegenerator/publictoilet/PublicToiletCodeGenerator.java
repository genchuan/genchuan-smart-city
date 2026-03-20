package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publictoilet;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.PublicToiletMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/3 16:34
 */
@Component
public class PublicToiletCodeGenerator {
    @Resource
    private PublicToiletMapper publicToiletMapper;

    /**
     * 生成toilet_id（格式：uuid-toilet- + 3位序号，如 uuid-toilet-001）
     */
    public String generateToiletId() {
        // 1. 查询全局最大序号
        Integer maxSeq = publicToiletMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接toilet_id
        return String.format("uuid-toilet-%03d", newSeq);
    }
}