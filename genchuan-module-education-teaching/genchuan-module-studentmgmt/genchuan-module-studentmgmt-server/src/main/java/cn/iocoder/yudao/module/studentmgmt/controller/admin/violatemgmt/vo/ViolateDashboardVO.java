package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;


import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "学生管理 - 学生违纪看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolateDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "本期违纪总记录数", example = "12")
    private Long totalCount;

    @Schema(description = "待审批记录数", example = "15")
    private Long pendingCount;

    @Schema(description = "已预警记录数", example = "8")
    private Long warnCount;

    @Schema(description = "高风险学生数（违纪次数≥3 次）", example = "3")
    private Long highRiskStudentCount;

    @Schema(description = "各违纪类型的记录数统计,key为违纪类型编码，value 为数量。", example = "15")
    private JSONObject violateTypeCount;

}