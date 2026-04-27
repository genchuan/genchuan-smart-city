package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍评比汇总 Request VO")
@Data
public class DormCompareSummaryReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2")
    private Long[] ids;

    @Schema(description = "汇总时间，默认当前时间，格式时间戳。")
    private LocalDateTime sumTime;

}