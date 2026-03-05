package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 投诉记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkComplaintRespVO {

    @Schema(description = "[主键ID] 投诉记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "5135")
    @ExcelProperty("[主键ID] 投诉记录唯一标识")
    private Long id;

    @Schema(description = "[投诉编号] 投诉唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[投诉编号] 投诉唯一编号")
    private String complaintNo;

    @Schema(description = "[投诉人ID] 投诉人唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "18844")
    @ExcelProperty("[投诉人ID] 投诉人唯一标识")
    private Long complainantId;

    @Schema(description = "[投诉人电话] 投诉人联系电话")
    @ExcelProperty("[投诉人电话] 投诉人联系电话")
    private String complainantPhone;

    @Schema(description = "[投诉类型] 如：服务投诉/设备故障/收费争议/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[投诉类型] 如：服务投诉/设备故障/收费争议/其他")
    private String complaintType;

    @Schema(description = "[关联资产ID] 关联资产唯一标识", example = "10058")
    @ExcelProperty("[关联资产ID] 关联资产唯一标识")
    private Long relatedAssetId;

    @Schema(description = "[关联订单ID] 关联订单唯一标识", example = "15468")
    @ExcelProperty("[关联订单ID] 关联订单唯一标识")
    private Long relatedOrderId;

    @Schema(description = "[投诉内容] 投诉具体内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[投诉内容] 投诉具体内容")
    private String complaintContent;

    @Schema(description = "[投诉时间] 用户发起投诉的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[投诉时间] 用户发起投诉的时间")
    private LocalDateTime complaintTime;

    @Schema(description = "[处理状态] 如：待处理/处理中/已办结/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[处理状态] 如：待处理/处理中/已办结/已驳回")
    private String status;

    @Schema(description = "[处理内容] 投诉处理结果及说明")
    @ExcelProperty("[处理内容] 投诉处理结果及说明")
    private String processContent;

    @Schema(description = "[处理人] 投诉处理人唯一标识")
    @ExcelProperty("[处理人] 投诉处理人唯一标识")
    private Long processBy;

    @Schema(description = "[处理时间] 投诉处理完成时间")
    @ExcelProperty("[处理时间] 投诉处理完成时间")
    private LocalDateTime processTime;

    @Schema(description = "[满意度] 如：非常满意/满意/一般/不满意/非常不满意")
    @ExcelProperty("[满意度] 如：非常满意/满意/一般/不满意/非常不满意")
    private String satisfaction;

    @Schema(description = "[反馈时间] 用户反馈时间")
    @ExcelProperty("[反馈时间] 用户反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 投诉相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 投诉相关备注说明")
    private String remark;

}
