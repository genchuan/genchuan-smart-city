package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员等级新增/修改 Request VO")
@Data
public class MemberLevelSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5898")
    private Long id;

    @Schema(description = "等级名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "等级名称不能为空")
    private String name;

    @Schema(description = "等级数值（排序用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "等级数值（排序用）不能为空")
    private Integer levelValue;

    @Schema(description = "升级条件（JSON）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "升级条件（JSON）不能为空")
    private String upgradeCondition;

    @Schema(description = "权益内容（JSON）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "权益内容（JSON）不能为空")
    private String benefits;

    @Schema(description = "状态：0-未生效，1-已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态：0-未生效，1-已生效不能为空")
    private Integer status;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

}