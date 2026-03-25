package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/11 10:49
 */
@Component
public class GarbageTransferCodeGenerator {

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    /**
     * 生成transfer_id（格式：uuid-transfer- + 3位序号，如 uuid-transfer-001）
     */
    public String generateTransferId() {
        // 1. 查询全局最大序号
        Integer maxSeq = garbageTransferMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接transfer_id
        return String.format("uuid-transfer-%03d", newSeq);
    }
}