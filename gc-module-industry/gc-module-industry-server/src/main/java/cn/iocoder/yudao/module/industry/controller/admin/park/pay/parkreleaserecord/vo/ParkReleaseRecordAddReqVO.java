package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 放行记录新增/修改 Request VO")
@Data
public class ParkReleaseRecordAddReqVO {
    @Schema(description = "关联缴费记录标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
    @NotNull(message = "关联缴费记录标识不能为空")
    private Long payId;

//    @Schema(description = "放行指令唯一标识（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13115")
//    @NotEmpty(message = "放行指令唯一标识（UUID）不能为空")
    //直接用1
    @Schema(hidden = true)
    private Long releaseCmdId;

    @Schema(description = "放行状态（未放行，已放行）", requiredMode = Schema.RequiredMode.REQUIRED, example = "已放行")
    @NotEmpty(message = "放行状态（未放行，已放行）不能为空")
    private String releaseStatus;

//    @Schema(description = "实际放行时间")
    //后端直接用当前时间
    @Schema(hidden = true)
    private LocalDateTime releaseTime;

    @Schema(description = "闸机唯一标识", example = "9")
    @NotNull(message = "闸机唯一标识不能为空")
    private Long gateId;

    @Schema(description = "执行放行操作人员", example = "张三")
    @NotBlank(message = "执行放行操作人员不能为空")
    private String operatorName;

//    @Schema(description = "分类扩展字段1")
//    private String extCat1;
//
//    @Schema(description = "分类扩展字段2")
//    private String extCat2;
//
//    @Schema(description = "通用扩展字段1")
//    private String extCommon1;
//
//    @Schema(description = "通用扩展字段2")
//    private String extCommon2;

}
