package cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 黑白名单新增/修改 Request VO")
@Data
public class BlackWhiteListSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "2347")
    private Long id;

    @Schema(description = "[名单类型] 如:黑名单/白名单", example = "2")
    private String listType;

    @Schema(description = "[目标类型] 如:用户/车辆", example = "2")
    private String targetType;

    @Schema(description = "[目标ID] 用户ID/车牌号码", example = "1105")
    private String targetId;

    @Schema(description = "[列入原因]", example = "不喜欢")
    private String listReason;

    @Schema(description = "[生效时间]")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 永久有效为NULL")
    private LocalDateTime expireTime;

    @Schema(description = "[状态] 如:生效中/已失效/已删除", example = "2")
    private String status;

    @Schema(description = "[备注]", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
