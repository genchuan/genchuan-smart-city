package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "学生管理 - 学生违纪看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateCountDashboardVO {

    // ========== 卡片数据 ==========

    @Schema(description = "各班级违纪次数统计", example = "")
    private List classCountList;
    @Schema(description = "各违纪类型分布统计", example = "")
    private List typeCountList;

}