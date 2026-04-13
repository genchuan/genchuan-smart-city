package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 床位管理新增/修改 Request VO")
@Data
public class BedMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15953")
    private Long id;

    @Schema(description = "楼栋", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "楼栋不能为空")
    private String building;

    @Schema(description = "楼层", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "楼层不能为空")
    private Integer floor;

    @Schema(description = "房间号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "房间号不能为空")
    private String roomNum;

    @Schema(description = "床位号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "床位号不能为空")
    private String bedNum;

    @Schema(description = "学生 ID", example = "28082")
    private Long studentId;

    @Schema(description = "分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "调整时间")
    private LocalDateTime adjustTime;

    @Schema(description = "状态：未分配/已分配", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：未分配/已分配不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}