package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import lombok.Data;

import java.util.List;

@Data
public class BatchResult<T> {
    // 成功插入的数量
    private int successCount;

    // 失败插入的数量
    private int failureCount;

    // 总插入的数量
    private int totalCount;

    // 失败的数据+错误信息
    private List<FailItem> failList;

    // 失败条目
    @Data
    public static class FailItem {
        private int index;      // 第几条数据（从1开始）
        private String data;    // 数据快照
        private String errorReason;   // 错误原因
    }
}
