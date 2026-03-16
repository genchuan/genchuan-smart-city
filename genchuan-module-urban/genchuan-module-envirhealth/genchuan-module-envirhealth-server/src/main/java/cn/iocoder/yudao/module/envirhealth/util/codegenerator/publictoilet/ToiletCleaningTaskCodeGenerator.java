package cn.iocoder.yudao.module.envirhealth.util.codegenerator.publictoilet;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletCleaningTaskMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/5 14:35
 */
@Component
public class ToiletCleaningTaskCodeGenerator {
    @Resource
    private ToiletCleaningTaskMapper toiletCleaningTaskMapper;

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyyMMdd")
    );

    /**
     * 生成task_no（格式：PTCT + 日期 + 3位序号，如 PTCT20260301001）
     */
    public String generateTaskNo() {
        // 1. 获取今日日期（yyyyMMdd）
        String dateStr = DATE_FORMATTER.get().format(new Date());
        // 2. 查询今日最大序号
        Integer maxSeq = toiletCleaningTaskMapper.selectMaxSeqByDate(dateStr);
        // 3. 新序号（默认从1开始）
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 4. 拼接task_no（格式化为3位序号）
        return String.format("PTCT%s%03d", dateStr, newSeq);
    }
}