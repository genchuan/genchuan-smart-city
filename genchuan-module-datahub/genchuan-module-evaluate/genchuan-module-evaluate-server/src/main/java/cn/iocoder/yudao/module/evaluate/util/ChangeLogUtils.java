package cn.iocoder.yudao.module.evaluate.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
/**
 * 变更日志工具类
 * 用于统一生成和追加带时间戳、操作人的变更日志
 */
public class ChangeLogUtils {
    // ================= 私有工具方法 =================

    /**
     * 核心追加方法
     * @param oldLog 数据库现有的日志
     * @param prefix 操作前缀，如 【新增】、【停用】
     * @param content 具体内容
     * @return 拼接后的完整日志
     */
    public static String appendLog(String oldLog, String prefix, String content) {
        String newLogPiece = buildLog(prefix, content);

        // 如果旧日志为空，直接返回新日志
        if (StrUtil.isEmpty(oldLog)) {
            return newLogPiece;
        }

        // 拼接：新日志 + 换行 + 旧日志
        // 建议使用 "\n" 或 "\n---\n" 来分隔，增加可读性
        return newLogPiece + "\n" + oldLog;
    }

    /**
     * 构建单条日志片段，自动加上时间戳
     */
    public static String buildLog(String prefix, String content) {
        // 格式：2026-03-06 12:00:00 【操作】具体内容
        return StrUtil.format("{} {} {}",
                LocalDateTimeUtil.formatNormal(LocalDateTime.now()), // 时间戳
                prefix,
                content);
    }
}
