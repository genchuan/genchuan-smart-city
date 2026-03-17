package cn.iocoder.yudao.module.envirhealth.util.codegenerator.river;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.RiverMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/17 14:08
 */
@Component
public class RiverCodeGenerator {

    @Resource
    private RiverMapper riverMapper;

    /**
     * 生成river_id（格式：uuid-river- + 3位序号，如 uuid-river-001）
     */
    public String generateRiverId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = riverMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接river_id
        return String.format("uuid-river-%03d", newSeq);
    }
}