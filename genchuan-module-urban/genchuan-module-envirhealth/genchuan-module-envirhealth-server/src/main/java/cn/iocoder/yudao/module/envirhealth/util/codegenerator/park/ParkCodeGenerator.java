package cn.iocoder.yudao.module.envirhealth.util.codegenerator.park;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.park.ParkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/16 8:46
 */
@Component
public class ParkCodeGenerator {

    @Resource
    private ParkMapper parkMapper;

    /**
     * 生成park_id（格式：uuid-park- + 3位序号，如 uuid-park-001）
     */
    public String generateParkId() {
        // 1. 查询全局最大序号
        Integer maxSeq = parkMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接park_id
        return String.format("uuid-park-%03d", newSeq);
    }

}