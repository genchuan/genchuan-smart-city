package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项大小类关联 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RelMngMatterMajMinRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20704")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "20521")
    @ExcelProperty("关联ID，唯一编码，UUID生成")
    private String mngMatterMajorMinorId;

    @Schema(description = "关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "29534")
    @ExcelProperty("关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String majorId;

    @Schema(description = "关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)")
    private String majorName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "26457")
    @ExcelProperty("关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)")
    private String minorName;

    @Schema(description = "关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联")
    private String relStatus;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}