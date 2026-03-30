package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferMaintenanceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TransferMaintenanceCodeGenerator {

    @Resource
    private TransferMaintenanceMapper transferMaintenanceMapper;

    /**
     * 生成maintain_id（格式：uuid-maintain- + 3位序号，如 uuid-maintain-001）
     */
    public String generateMaintainId() {
        // 1. 查询全局最大序号
        Integer maxSeq = transferMaintenanceMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接maintain_id
        return String.format("uuid-maintain-%03d", newSeq);
    }

}