package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 违规等级字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IllegalLevelDictRespVO {

    @Schema(description = "[主键ID] 违规等级唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11586")
    @ExcelProperty("[主键ID] 违规等级唯一标识")
    private Long id;

    @Schema(description = "[违规等级编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[违规等级编码] 唯一编码")
    private String levelCode;

    @Schema(description = "[违规等级名称] 如：一般/较重/严重", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[违规等级名称] 如：一般/较重/严重")
    private String levelName;

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
