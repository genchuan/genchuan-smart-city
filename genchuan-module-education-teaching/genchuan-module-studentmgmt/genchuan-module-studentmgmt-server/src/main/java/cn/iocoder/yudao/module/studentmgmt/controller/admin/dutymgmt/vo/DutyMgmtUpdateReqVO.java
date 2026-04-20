package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Schema(description = "管理后台 - 值班管理修改 Request VO")
@Data
public class DutyMgmtUpdateReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;

    @Schema(description = "值班日期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-02")
    @NotNull(message = "值班日期不能为空")
    private LocalDate dutyDate;

    @Schema(description = "值班人", requiredMode = Schema.RequiredMode.REQUIRED, example = "小李")
    @NotEmpty(message = "值班人不能为空")
    private String dutyUser;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}