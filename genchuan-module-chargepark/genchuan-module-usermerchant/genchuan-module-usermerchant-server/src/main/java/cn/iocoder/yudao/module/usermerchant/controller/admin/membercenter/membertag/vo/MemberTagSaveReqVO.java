package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 会员标签新增/修改 Request VO")
@Data
public class MemberTagSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "23330")
    private Long id;

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "标签名称不能为空")
    private String name;

    @Schema(description = "标签描述", example = "你说的对")
    private String description;

    @Schema(description = "状态：0-禁用，1-正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态：0-禁用，1-正常不能为空")
    private Integer status;

}