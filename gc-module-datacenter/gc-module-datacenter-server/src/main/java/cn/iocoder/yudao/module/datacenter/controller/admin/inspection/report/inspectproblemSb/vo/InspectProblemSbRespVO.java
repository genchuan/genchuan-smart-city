package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检问题上报记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectProblemSbRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "问题ID")
    @ExcelProperty("问题ID")
    private String problemId;

    @Schema(description = "问题编码")
    @ExcelProperty("问题编码")
    private String problemCode;

    @Schema(description = "问题名称")
    @ExcelProperty("问题名称")
    private String problemName;

    @Schema(description = "问题类型ID")
    @ExcelProperty("问题类型ID")
    private String matterTypeId;

    @Schema(description = "问题类型名称")
    @ExcelProperty("问题类型名称")
    private String matterTypeName;

    @Schema(description = "关联任务ID")
    @ExcelProperty("关联任务ID")
    private String taskId;

    @Schema(description = "关联任务编码")
    @ExcelProperty("关联任务编码")
    private String taskCode;

    @Schema(description = "上报人员ID")
    @ExcelProperty("上报人员ID")
    private String rptUserId;

    @Schema(description = "上报人员姓名")
    @ExcelProperty("上报人员姓名")
    private String rptUserName;

    @Schema(description = "所属网格ID")
    @ExcelProperty("所属网格ID")
    private String gridId;

    @Schema(description = "所属网格名称")
    @ExcelProperty("所属网格名称")
    private String gridName;

    @Schema(description = "问题位置坐标X")
    @ExcelProperty("问题位置坐标X")
    private BigDecimal problemLocX;

    @Schema(description = "问题位置坐标Y")
    @ExcelProperty("问题位置坐标Y")
    private BigDecimal problemLocY;

    @Schema(description = "问题位置描述")
    @ExcelProperty("问题位置描述")
    private String problemLocDesc;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "问题照片URLs")
    @ExcelProperty("问题照片URLs")
    private String problemPhotoUrls;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime rptTime;

    @Schema(description = "问题状态")
    @ExcelProperty("问题状态")
    private String problemStatus;

    @Schema(description = "驳回原因")
    @ExcelProperty("驳回原因")
    private String rejectReason;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String bizCreateUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime bizCreateTime;

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