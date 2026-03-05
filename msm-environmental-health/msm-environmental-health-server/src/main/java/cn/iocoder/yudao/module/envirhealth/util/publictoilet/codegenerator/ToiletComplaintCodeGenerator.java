package cn.iocoder.yudao.module.envirhealth.util.publictoilet.codegenerator;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletComplaintMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/4 15:32
 */
@Component
public class ToiletComplaintCodeGenerator {
    @Resource
    private ToiletComplaintMapper toiletComplaintMapper;

    /**
     * 生成complaint_id（格式：uuid-complaint- + 3位序号，如 uuid-complaint-001）
     */
    public String generateComplaintId() {
        // 1. 查询全局最大序号
        Integer maxSeq = toiletComplaintMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接complaint_id
        return String.format("uuid-complaint-%03d", newSeq);
    }
}