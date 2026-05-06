package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员配置新增/修改 Request VO")
@Data
public class MemberConfigSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19999")
    private Long id;

    @Schema(description = "配置类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "配置类型不能为空")
    private String configType;

    @Schema(description = "权益内容（JSON或文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "权益内容（JSON或文本）不能为空")
    private String content;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "状态：0-未生效，1-已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态：0-未生效，1-已生效不能为空")
    private Integer status;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

}