package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "学生管理 - 学生信息看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "学生总人数", example = "50")
    private Long totalStudentCount;

    @Schema(description = "在籍学生数", example = "35")
    private Long inSchoolCount;

    @Schema(description = "休学学生数", example = "8")
    private Long suspendCount;

    @Schema(description = "退学学生数", example = "12")
    private Long dropOutCount;

    @Schema(description = "异动学生数", example = "15")
    private Long transferCount;

    @Schema(description = "普通生人数", example = "8")
    private Long normalStudentCount;

    @Schema(description = "特长生人数", example = "12")
    private Long specialStudentCount;

    @Schema(description = "转学生人数", example = "15")
    private Long transferStudentCount;

}