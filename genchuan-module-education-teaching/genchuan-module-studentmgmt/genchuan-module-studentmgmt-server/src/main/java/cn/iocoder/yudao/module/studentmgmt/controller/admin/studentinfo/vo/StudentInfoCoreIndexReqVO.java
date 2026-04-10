package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;


@Schema(description = "学生管理 - 学生核心指标统计VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoCoreIndexReqVO {

    // ========== 卡片数据 ==========
    @Schema(description = "发起时间，前端传参对应的 long 类型时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDateTime startTime;

    @Schema(description = "结束时间，前端传参对应的 long 类型时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDateTime endTime;


}