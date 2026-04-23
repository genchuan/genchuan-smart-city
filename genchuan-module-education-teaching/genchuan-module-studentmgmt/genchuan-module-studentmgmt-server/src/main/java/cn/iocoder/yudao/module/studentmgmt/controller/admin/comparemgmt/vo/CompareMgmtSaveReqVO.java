package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 评比管理新增/修改 Request VO")
@Data
public class CompareMgmtSaveReqVO {

    @Schema(description = "班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "班级不能为空")
    private String className;

    @Schema(description = "评比周期：周/月/学期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评比周期：周/月/学期不能为空")
    private String cycle;


    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}