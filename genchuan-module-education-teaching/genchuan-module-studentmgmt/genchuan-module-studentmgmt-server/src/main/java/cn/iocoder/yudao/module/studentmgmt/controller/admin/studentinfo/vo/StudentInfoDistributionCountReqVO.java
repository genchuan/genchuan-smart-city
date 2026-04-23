package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 学生信息查询学生信息分布看板的核心统计数据 Request VO")
@Data
public class StudentInfoDistributionCountReqVO {

    @Schema(description = "统计维度，支持 grade/major/class", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计维度不能为空")
    private String dimension;

}