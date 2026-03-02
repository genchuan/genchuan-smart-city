package cn.iocoder.yudao.module.envirhealth.util.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageCollectionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 收运计划编号生成工具类
 */
@Component
public class GarbageCollectionCodeGenerator {

    @Resource
    private GarbageCollectionMapper garbageCollectionMapper;

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyyMMdd")
    );

    /**
     * 生成plan_no（格式：GC + 日期 + 3位序号，如 GC20240601001）
     */
    public String generatePlanNo() {
        // 1. 获取今日日期（yyyyMMdd）
        String dateStr = DATE_FORMATTER.get().format(new Date());
        // 2. 查询今日最大序号
        Integer maxSeq = garbageCollectionMapper.selectMaxSeqByDate(dateStr);
        // 3. 新序号（默认从1开始）
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 4. 拼接plan_no（格式化为3位序号）
        return String.format("GC%s%03d", dateStr, newSeq);
    }

    /**
     * 生成collection_id（格式：uuid-collect- + 3位序号，如 uuid-collect-001）
     */
    public String generateCollectionId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = garbageCollectionMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接collection_id
        return String.format("uuid-collect-%03d", newSeq);
    }
}