package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果系统对接 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalResultSystemDockRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "对接ID")
    @ExcelProperty("对接ID")
    private String resultDockId;

    @Schema(description = "对接编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对接编号")
    private String resultDockCode;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联存档ID")
    private String resultArchId;

    @Schema(description = "外部系统ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("外部系统ID")
    private String externalSystemId;

    @Schema(description = "外部系统名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("外部系统名称")
    private String externalSystemName;

    @Schema(description = "对接方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对接方式")
    private String dockMethod;

    @Schema(description = "对接频率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对接频率")
    private String dockFrequency;

    @Schema(description = "数据映射规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据映射规则ID")
    private String mapRuleId;

    @Schema(description = "对接状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("对接状态")
    private String dockStatus;

    @Schema(description = "对接时间")
    @ExcelProperty("对接时间")
    private LocalDateTime dockTime;

    @Schema(description = "对接数据")
    @ExcelProperty("对接数据")
    private String dockData;

    @Schema(description = "失败原因")
    @ExcelProperty("失败原因")
    private String failReason;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}