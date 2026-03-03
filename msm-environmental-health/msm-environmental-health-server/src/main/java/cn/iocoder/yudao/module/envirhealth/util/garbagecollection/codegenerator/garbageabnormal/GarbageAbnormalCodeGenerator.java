package cn.iocoder.yudao.module.envirhealth.util.garbagecollection.codegenerator.garbageabnormal;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/2 10:07
 */
@Component
public class GarbageAbnormalCodeGenerator {

    @Resource
    private GarbageAbnormalMapper garbageAbnormalMapper;

    /**
     * 生成abnormal_id（格式：uuid-abnormal- + 3位序号，如 uuid-abnormal-001）
     */
    public String generateAbnormalId() {
        // 1. 查询全局最大序号
        Integer maxSeq = garbageAbnormalMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接collection_id
        return String.format("uuid-abnormal-%03d", newSeq);
    }
}