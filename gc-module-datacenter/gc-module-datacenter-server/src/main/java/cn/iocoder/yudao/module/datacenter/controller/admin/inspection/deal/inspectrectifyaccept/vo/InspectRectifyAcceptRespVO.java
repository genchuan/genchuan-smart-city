package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检整改结果验收 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectRectifyAcceptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "验收ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收ID")
    private String acceptId;

    @Schema(description = "验收编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收编码")
    private String acceptCode;

    @Schema(description = "关联整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "关联整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题ID")
    private String problemId;

    @Schema(description = "整改结果描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改结果描述")
    private String rectifyResultDesc;

    @Schema(description = "整改后照片URLs", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改后照片URLs")
    private String rectifyAfterPhotoUrls;

    @Schema(description = "整改前照片URLs", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改前照片URLs")
    private String rectifyBeforePhotoUrls;

    @Schema(description = "验收结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收结果")
    private String acceptResult;

    @Schema(description = "验收意见")
    @ExcelProperty("验收意见")
    private String acceptOpinion;

    @Schema(description = "验收人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收人")
    private String acceptUser;

    @Schema(description = "验收时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("验收时间")
    private LocalDateTime acceptTime;

    @Schema(description = "整改提交人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改提交人")
    private String rectifySubmitUser;

    @Schema(description = "整改提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改提交时间")
    private LocalDateTime rectifySubmitTime;

    @Schema(description = "任务状态变更", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务状态变更")
    private String taskStatusChange;

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