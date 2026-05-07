package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 校企合作资源看板 Request VO")
@Data
public class CoopEnterpriseChartReqVO {
    @Schema(description = "系部筛选", example = "17018")
    private Long deptId;

    @Schema(description = "统计时间范围")
    private LocalDateTime[] timeRange;

}