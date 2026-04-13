package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宣传管理新增/修改 Request VO")
@Data
public class PromoteMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18073")
    private Long id;

    @Schema(description = "宣传任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "宣传任务名称不能为空")
    private String taskName;

    @Schema(description = "宣传站点", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "宣传站点不能为空")
    private String site;

    @Schema(description = "宣传人数")
    private Integer promoteNum;

    @Schema(description = "意向学生数")
    private Integer intentNum;

    @Schema(description = "执行人")
    private String executeUser;

    @Schema(description = "执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "状态：未执行/已执行", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：未执行/已执行不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}