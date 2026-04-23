package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;


@Schema(description = "违纪管理 - 预警核心指标统计 请求VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateWarnIndexReqVO {

    // ========== 卡片数据 ==========
//    cycle (string, optional): 统计周期，可选周 / 月 / 学期，默认当前月。
    @Schema(description = "统计周期，可选周 / 月 / 学期，默认当前月", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cycle;


}