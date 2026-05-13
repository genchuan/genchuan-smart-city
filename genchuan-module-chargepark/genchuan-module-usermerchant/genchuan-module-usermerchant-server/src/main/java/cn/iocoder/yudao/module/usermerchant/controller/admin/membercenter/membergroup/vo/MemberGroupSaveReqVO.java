package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员分组新增/修改 Request VO")
@Data
public class MemberGroupSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "14955")
    private Long id;

    @Schema(description = "分组名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "分组名称不能为空")
    private String name;

    @Schema(description = "分组描述", example = "你说的对")
    private String description;

    @Schema(description = "分组规则（JSON）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分组规则（JSON）不能为空")
    private String rule;

    @Schema(description = "状态：0-未生效，1-已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态：0-未生效，1-已生效不能为空")
    private Integer status;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}