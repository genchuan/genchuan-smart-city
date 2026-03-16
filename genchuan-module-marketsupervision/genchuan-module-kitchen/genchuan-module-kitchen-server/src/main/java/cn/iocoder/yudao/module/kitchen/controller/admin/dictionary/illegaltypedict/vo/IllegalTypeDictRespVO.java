package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 违规类型字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IllegalTypeDictRespVO {

    @Schema(description = "[主键ID] 违规类型唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2210")
    @ExcelProperty("[主键ID] 违规类型唯一标识")
    private Long id;

    @Schema(description = "[违规类型编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[违规类型编码] 唯一编码")
    private String typeCode;

    @Schema(description = "[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范")
    private String typeName;

    @Schema(description = "[排序序号] 数值越小越靠前")
    @ExcelProperty("[排序序号] 数值越小越靠前")
    private Integer sort;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}
