package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 指标权重管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalIdxWeightRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "权重ID")
    @ExcelProperty("权重ID")
    private String idxWeightId;

    @Schema(description = "所属指标体系ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属指标体系ID")
    private String idxSystemId;

    @Schema(description = "所属指标体系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属指标体系名称")
    private String idxSystemName;

    @Schema(description = "权重类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权重类型")
    private String weightType;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联ID")
    private String relateId;

    @Schema(description = "关联名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联名称")
    private String relateName;

    @Schema(description = "权重值(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权重值(%)")
    private BigDecimal weightValue;

    @Schema(description = "权重说明")
    @ExcelProperty("权重说明")
    private String weightDesc;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    @ExcelProperty("更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    @ExcelProperty("更新时间(业务)")
    private LocalDateTime updateTimeBiz;

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