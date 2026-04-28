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

    // ===================== 新增字段 START =====================
//    @Schema(description = "省份", requiredMode = Schema.RequiredMode.REQUIRED, example = "福建省")
//    @NotEmpty(message = "省份不能为空")
//    private String province;
//
//    @Schema(description = "城市", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州市")
//    @NotEmpty(message = "城市不能为空")
//    private String city;
    // ===================== 新增字段 END =====================

    @Schema(description = "区县", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰泽区")
    @NotEmpty(message = "区县不能为空")
    private String district;

    // ===================== 新增字段 START =====================
//    @Schema(description = "详细地址", example = "福建省泉州市丰泽区滨海街100号")
//    private String address;
    // ===================== 新增字段 END =====================

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "负责人ID不能为空")
    private Long userId;

    @Schema(description = "联系电话", example = "13812345678")
    private String phone;

    // ===================== 新增字段 START =====================
    @Schema(description = "备注", example = "核心城区充电片区")
    private String remark;
    // ===================== 新增字段 END =====================

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
