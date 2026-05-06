package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 毕业生离校进度看板 response VO")
@Data
public class LeaveHandleCharRespVO {

    @Schema(description = "总毕业生数")
    private Integer totalGraduate;
    @Schema(description = "待确认学生数")
    private Integer waitConfirm;
    @Schema(description = "待办理学生数")
    private Integer waitHandle;
    @Schema(description = "已离校学生数")
    private Integer finishedLeave;
    @Schema(description = "整体办理完成率")
    private BigDecimal finishRate;

}