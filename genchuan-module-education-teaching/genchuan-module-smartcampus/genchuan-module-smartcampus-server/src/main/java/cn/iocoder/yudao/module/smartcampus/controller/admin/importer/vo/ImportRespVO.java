package cn.iocoder.yudao.module.smartcampus.controller.admin.importer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用批量导入结果响应 VO
 * 可用于所有实体的Excel导入功能
 */
@Data
@Schema(description = "通用 - 批量导入结果 Response VO")
@NoArgsConstructor
@AllArgsConstructor
public class ImportRespVO<T> {

    @Schema(description = "成功导入的数据列表")
    private List<T> successList;

    @Schema(description = "失败的数据列表，包含错误信息")
    private List<ImportErrorItem> errorList;

    @Schema(description = "Excel字段信息（调试用）")
    private List<Map<String, String>> excelFieldInfo;

    @Schema(description = "实体类字段信息（调试用）")
    private Map<String, String> entityFieldInfo;

    @Schema(description = "成功数量")
    private Integer successCount;

    @Schema(description = "失败数量")
    private Integer errorCount;

    @Schema(description = "总记录数")
    private Integer totalCount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImportErrorItem {
        @Schema(description = "行号")
        private Integer rowIndex;

        @Schema(description = "错误信息")
        private String errorMessage;

        @Schema(description = "原始数据")
        private Map<String, Object> rawData;
    }

    // 添加便捷的构造方法
    public static <T> ImportRespVO<T> success(List<T> successList) {
        return new ImportRespVO<>(
                successList,
                new ArrayList<>(),
                null,
                null,
                successList.size(),
                0,
                successList.size()
        );
    }

    public static <T> ImportRespVO<T> empty() {
        return new ImportRespVO<>(
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                null,
                0,
                0,
                0
        );
    }
}