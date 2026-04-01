package cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 公司得分新增/修改 Request VO")
@Data
public class ObjectScoreSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25269")
    private Long id;

    @Schema(description = "对象ID (关联eval_object.id)", example = "20118")
    private Long objectId;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "7259")
    private Long systemId;

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "17976")
    private Long userId;

    @Schema(description = "总得分")
    private Long score;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "1")
    private String status;

    @Schema(description = "评价说明")
    private String details;

    @Schema(description = "变更日志")
    private String changeLog;

}