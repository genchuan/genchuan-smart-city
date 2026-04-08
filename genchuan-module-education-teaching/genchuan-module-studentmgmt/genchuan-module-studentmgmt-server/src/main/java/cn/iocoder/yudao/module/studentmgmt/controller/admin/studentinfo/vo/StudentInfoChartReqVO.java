package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "学生管理 - 学生信息分布看板请求VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoChartReqVO {

    // ========== 卡片数据 ==========
    @Schema(description = "年级", example = "2024级")
    private String grade;
    @Schema(description = "专业", example = "软件工程")
    private String major;

}