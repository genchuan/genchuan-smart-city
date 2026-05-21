package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 用户精简信息 Response VO")
@Data
public class UserSimpleRespVO {

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "用户昵称", example = "张三")
    private String nickname;
}
