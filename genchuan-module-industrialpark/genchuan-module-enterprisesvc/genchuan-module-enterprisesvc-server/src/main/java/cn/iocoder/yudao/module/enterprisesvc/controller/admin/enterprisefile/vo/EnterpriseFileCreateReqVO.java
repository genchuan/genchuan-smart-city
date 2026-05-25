package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 企业档案填报 Request VO")
@Data
public class EnterpriseFileCreateReqVO {

    @Schema(description = "企业名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "福建亘川科技有限公司")
    @NotEmpty(message = "企业名称不能为空")
    private String enterpriseName;

    @Schema(description = "统一社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "91350500MA3456789X")
    @NotEmpty(message = "统一社会信用代码不能为空")
    private String creditCode;

    @Schema(description = "注册地址", example = "福建省泉州市德化县 XX 路 XX 号")
    private String registerAddr;

    @Schema(description = "企业类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "科技")
    @NotEmpty(message = "企业类型不能为空")
    private String enterpriseType;

    @Schema(description = "企业规模", requiredMode = Schema.RequiredMode.REQUIRED, example = "小型")
    @NotEmpty(message = "企业规模不能为空")
    private String enterpriseScale;

    @Schema(description = "员工总数", example = "25")
    @Min(value = 0, message = "员工总数不能小于 0")
    private Integer staffCount;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
