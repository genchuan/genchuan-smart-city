package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理部件图示符号库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizMngCompSymbolLibRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26947")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "符号库ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "13064")
    @ExcelProperty("符号库ID，唯一编码，UUID生成")
    private String symbolLibId;

    @Schema(description = "符号名称", example = "王五")
    @ExcelProperty("符号名称")
    private String symbolName;

    @Schema(description = "符号路径")
    @ExcelProperty("符号路径")
    private String symbolPath;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}