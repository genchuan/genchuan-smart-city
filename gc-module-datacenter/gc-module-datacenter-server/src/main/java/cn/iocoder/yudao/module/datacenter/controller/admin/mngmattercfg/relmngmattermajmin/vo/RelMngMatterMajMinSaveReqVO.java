package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项大小类关联新增/修改 Request VO")
@Data
public class RelMngMatterMajMinSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20704")
    private Long id;

    @Schema(description = "关联ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "20521")
    @NotEmpty(message = "关联ID，唯一编码，UUID生成不能为空")
    private String mngMatterMajorMinorId;

    @Schema(description = "关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "29534")
    @NotEmpty(message = "关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)不能为空")
    private String majorId;

    @Schema(description = "关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)不能为空")
    private String majorName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "26457")
    @NotEmpty(message = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)不能为空")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)不能为空")
    private String minorName;

    @Schema(description = "关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联不能为空")
    private String relStatus;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}