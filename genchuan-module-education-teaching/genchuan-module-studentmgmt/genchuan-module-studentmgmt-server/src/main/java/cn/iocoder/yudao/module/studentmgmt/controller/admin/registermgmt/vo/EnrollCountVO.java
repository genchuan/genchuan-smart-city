package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 报名管理确认 response VO")
@Data
public class EnrollCountVO {

    @Schema(description = "专业名称")
    private String major;
    @Schema(description = "报名人数")
    private Integer applyCount;
    @Schema(description = "录取人数")
    private Integer admitCount;
}