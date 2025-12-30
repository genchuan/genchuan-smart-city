package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 放行记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkReleaseRecordRespVO {

    @Schema(description = "主键，唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "28785")
    @ExcelProperty("主键，唯一标识")
    private Long id;

    @Schema(description = "关联缴费记录标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "31998")
    @ExcelProperty("关联缴费记录标识")
    private Long payId;

    @Schema(description = "放行指令唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13115")
    @ExcelProperty("放行指令唯一标识")
    private Long releaseCmdId;

    @Schema(description = "放行状态（未放行，已放行）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("放行状态（未放行，已放行）")
    private String releaseStatus;

    @Schema(description = "实际放行时间")
    @ExcelProperty("实际放行时间")
    private LocalDateTime releaseTime;

    @Schema(description = "闸机唯一标识", example = "839")
    @ExcelProperty("闸机唯一标识")
    private Long gateId;

    @Schema(description = "执行放行操作人员", example = "张三")
    @ExcelProperty("执行放行操作人员")
    private String operatorName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
