package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@Schema(description = "管理后台 - 片区信息更新 Request VO")
public class AreaInfoUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "片区编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "AREA-20250401-001")
    @NotEmpty(message = "片区编号不能为空")
    private String areaNo;

    @Schema(description = "片区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰泽核心片区")
    @NotEmpty(message = "片区名称不能为空")
    private String name;

    @Schema(description = "所属行政区划", requiredMode = Schema.RequiredMode.REQUIRED, example = "福建省泉州市丰泽区")
    @NotEmpty(message = "所属行政区划不能为空")
    private String district;

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "负责人ID不能为空")
    private Long userId;

    @Schema(description = "联系电话", example = "13812345678")
    private String phone;

    @Schema(description = "备注", example = "核心城区片区")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
