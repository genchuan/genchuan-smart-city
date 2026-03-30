package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 违规类型字典新增/修改 Request VO")
@Data
public class IllegalTypeDictSaveReqVO {

    @Schema(description = "[主键ID] 违规类型唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "16832")
    private Long id;

    @Schema(description = "[分类编码]对应AI场景告警字典的告警类型编码(alertType不唯一)")
    private String typeCategory;

    @Schema(description = "[违规类型唯一编码] 对应AI场景告警字典的算法编码（aiAbilityCode唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[违规类型唯一编码] 对应AI场景告警字典的算法编码（aiAbilityCode唯一）不能为空")
    private String typeCode;

    @Schema(description = "[违规类型名称] 对应AI场景告警字典的场景名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[违规类型名称] 对应AI场景告警字典的场景名称不能为空")
    private String typeName;

    @Schema(description = "[违法行为说明]补充type_name说明", example = "随便")
    private String illegalBehaviorDescription;

    @Schema(description = "[告警设备说明]对应AI场景告警字典的告警设备说明", example = "你猜")
    private String alarmDeviceDescription;

    @Schema(description = "[排序序号] 数值越小越靠前")
    private Integer sort;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
