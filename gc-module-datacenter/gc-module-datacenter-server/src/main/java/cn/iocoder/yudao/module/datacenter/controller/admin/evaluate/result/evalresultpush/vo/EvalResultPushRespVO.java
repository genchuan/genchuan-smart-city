package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果关联推送 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalResultPushRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "推送ID")
    @ExcelProperty("推送ID")
    private String resultPushId;

    @Schema(description = "推送编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送编号")
    private String resultPushCode;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联存档ID")
    private String resultArchId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "推送目标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送目标")
    private String pushTarget;

    @Schema(description = "推送方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送方式")
    private String pushMethod;

    @Schema(description = "推送地址")
    @ExcelProperty("推送地址")
    private String pushAddress;

    @Schema(description = "推送内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送内容")
    private String pushContent;

    @Schema(description = "推送状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("推送状态")
    private String pushStatus;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

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