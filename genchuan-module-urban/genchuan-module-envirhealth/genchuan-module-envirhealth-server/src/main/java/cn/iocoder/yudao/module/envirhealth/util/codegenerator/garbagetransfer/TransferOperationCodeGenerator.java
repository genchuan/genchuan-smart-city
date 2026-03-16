package cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferOperationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TransferOperationCodeGenerator {

    @Resource
    private TransferOperationMapper transferOperationMapper;

    /**
     * 生成operation_id（格式：uuid-operation- + 3位序号，如 uuid-operation-001）
     */
    public String generateOperationId() {
        // 1. 查询全局最大序号
        Integer maxSeq = transferOperationMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接operation_id
        return String.format("uuid-operation-%03d", newSeq);
    }
}