package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Schema(description = "学生管理 - 学生核心指标统计VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoCoreIndexReqVO {

    // ========== 卡片数据 ==========
    @Schema(description = "发起时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime endTime;


}