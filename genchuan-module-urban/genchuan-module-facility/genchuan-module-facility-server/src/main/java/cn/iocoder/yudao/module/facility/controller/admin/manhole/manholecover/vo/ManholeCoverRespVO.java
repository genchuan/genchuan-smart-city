package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖设施 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManholeCoverRespVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21008")
    @ExcelProperty("自增主键")
    private Long id;

    @Schema(description = "井盖编号（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("井盖编号（唯一）")
    private String coverNo;

    @Schema(description = "关联道路设施表road_facility的road_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13196")
    @ExcelProperty("关联道路设施表road_facility的road_id")
    private Long roadId;

    @Schema(description = "井盖类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("井盖类型")
    private String coverType;

    @Schema(description = "井盖规格（文本）")
    @ExcelProperty("井盖规格（文本）")
    private String specification;

    @Schema(description = "安装时间")
    @ExcelProperty("安装时间")
    private LocalDate installTime;

    @Schema(description = "关联区域表sys_area的area_code")
    @ExcelProperty("关联区域表sys_area的area_code")
    private String areaCode;

    @Schema(description = "使用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("使用状态")
    private String status;

    @Schema(description = "创建时间（自动生成）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间（自动生成）")
    private LocalDateTime createTime;

}