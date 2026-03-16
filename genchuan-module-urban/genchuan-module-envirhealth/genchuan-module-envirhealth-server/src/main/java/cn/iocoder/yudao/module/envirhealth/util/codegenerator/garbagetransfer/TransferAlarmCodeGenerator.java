package cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferAlarmMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TransferAlarmCodeGenerator {

    @Resource
    private TransferAlarmMapper transferAlarmMapper;

    /**
     * 生成alarm_id（格式：uuid-alarm- + 3位序号，如 uuid-alarm-001）
     */
    public String generateAlarmId() {
        // 1. 查询全局最大序号
        Integer maxSeq = transferAlarmMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接alarm_id
        return String.format("uuid-alarm-%03d", newSeq);
    }

}