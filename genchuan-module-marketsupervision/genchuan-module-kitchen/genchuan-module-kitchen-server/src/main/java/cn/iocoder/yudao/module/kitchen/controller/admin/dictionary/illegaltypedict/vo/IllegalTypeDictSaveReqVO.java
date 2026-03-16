package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 违规类型字典新增/修改 Request VO")
@Data
public class IllegalTypeDictSaveReqVO {

    @Schema(description = "[主键ID] 违规类型唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2210")
    private Long id;

    @Schema(description = "[违规类型编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[违规类型编码] 唯一编码不能为空")
    private String typeCode;

    @Schema(description = "[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范不能为空")
    private String typeName;

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
