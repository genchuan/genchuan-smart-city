package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项大类新增/修改 Request VO")
@Data
public class BizMngMatterMajorSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20053")
    private Long id;

    @Schema(description = "管理事项大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20451")
    @NotEmpty(message = "管理事项大类ID不能为空")
    private String mngMatterMajorId;

    @Schema(description = "大类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "大类代码不能为空")
    private String matterMajorCode;

    @Schema(description = "大类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "大类名称不能为空")
    private String matterMajorName;

    @Schema(description = "大类说明")
    private String matterMajorDesc;

    @Schema(description = "排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}