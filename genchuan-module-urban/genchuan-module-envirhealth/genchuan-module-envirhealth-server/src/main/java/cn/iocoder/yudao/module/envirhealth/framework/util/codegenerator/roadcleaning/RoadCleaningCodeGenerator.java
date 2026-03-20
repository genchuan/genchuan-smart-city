package cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning.RoadCleaningMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/6 10:35
 */
@Component
public class RoadCleaningCodeGenerator {

    @Resource
    private RoadCleaningMapper roadCleaningMapper;

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyyMMdd")
    );

    /**
     * 生成plan_no（格式：RC + 日期 + 3位序号，如 RC20240601001）
     */
    public String generatePlanNo() {
        // 1. 获取今日日期（yyyyMMdd）
        String dateStr = DATE_FORMATTER.get().format(new Date());
        // 2. 查询今日最大序号
        Integer maxSeq = roadCleaningMapper.selectMaxSeqByDate(dateStr);
        // 3. 新序号（默认从1开始）
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 4. 拼接plan_no（格式化为3位序号）
        return String.format("RC%s%03d", dateStr, newSeq);
    }

    /**
     * 生成cleaning_id（格式：uuid-clean- + 3位序号，如 uuid-clean-001）
     */
    public String generateCleaningId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = roadCleaningMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接cleaning_id
        return String.format("uuid-clean-%03d", newSeq);
    }
}