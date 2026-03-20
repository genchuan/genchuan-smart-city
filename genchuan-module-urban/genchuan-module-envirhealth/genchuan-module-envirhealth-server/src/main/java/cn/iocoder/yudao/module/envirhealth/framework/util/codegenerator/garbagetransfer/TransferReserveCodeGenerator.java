package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferReserveMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/12 9:42
 */
@Component
public class TransferReserveCodeGenerator {
    @Resource
    private TransferReserveMapper transferReserveMapper;
    /**
     * 生成reserve_id（格式：uuid-reserve- + 3位序号，如 uuid-reserve-001）
     */
    public String generateReserveId() {
        // 1. 查询全局最大序号
        Integer maxSeq = transferReserveMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接reserve_id
        return String.format("uuid-reserve-%03d", newSeq);
    }
}