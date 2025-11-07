package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件扩展配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonCompExtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22882")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "扩展配置ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "15638")
    @ExcelProperty("扩展配置ID，唯一编码，UUID生成")
    private String extId;

    @Schema(description = "部件ID，关联监测部件信息表ID；监测部件信息表(biz_mon_comp_info)", example = "7669")
    @ExcelProperty("部件ID，关联监测部件信息表ID；监测部件信息表(biz_mon_comp_info)")
    private String compId;

    @Schema(description = "扩展键")
    @ExcelProperty("扩展键")
    private String extKey;

    @Schema(description = "扩展值")
    @ExcelProperty("扩展值")
    private String extValue;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}