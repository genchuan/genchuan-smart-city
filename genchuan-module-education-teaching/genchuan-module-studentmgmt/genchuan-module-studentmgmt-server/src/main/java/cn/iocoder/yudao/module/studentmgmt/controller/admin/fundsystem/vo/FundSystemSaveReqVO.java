package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资助系统新增/修改 Request VO")
@Data
public class FundSystemSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15012")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30366")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "资助类型：助学金/勤工俭学/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资助类型：助学金/勤工俭学/其他不能为空")
    private String fundType;

    @Schema(description = "申请金额")
    private BigDecimal applyAmount;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待审核/已汇总", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/已汇总不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}