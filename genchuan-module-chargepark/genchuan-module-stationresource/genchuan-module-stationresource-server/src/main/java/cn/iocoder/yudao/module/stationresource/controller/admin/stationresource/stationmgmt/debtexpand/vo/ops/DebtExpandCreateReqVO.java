package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import cn.idev.excel.annotation.ExcelIgnore;



@Schema(description = "管理后台 - 联合追缴拓场配置新增 Request VO")
@Data
public class DebtExpandCreateReqVO {

    @Schema(description = "合作场站，关联station_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "合作场站不能为空")
    private Long stationId;

    @Schema(description = "合作类型，关联字典debt_expand_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "社会停车场拓场")
    @NotBlank(message = "合作类型不能为空")
    private String type;

    @Schema(description = "追缴范围，关联字典debt_expand_range", requiredMode = Schema.RequiredMode.REQUIRED, example = "本区域")
    @NotBlank(message = "追缴范围不能为空")
    private String range;

    @Schema(description = "备注", example = "鲤城区域停车场拓场合作")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    @ExcelIgnore
    private String reserve2;

}
