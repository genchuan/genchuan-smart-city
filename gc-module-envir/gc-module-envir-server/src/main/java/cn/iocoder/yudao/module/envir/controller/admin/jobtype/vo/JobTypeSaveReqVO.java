package cn.iocoder.yudao.module.envir.controller.admin.jobtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 岗位类型字典新增/修改 Request VO")
@Data
public class JobTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13753")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "22149")
    private String sysJobTypeId;

    @Schema(description = "岗位名称（可选值：清扫工/保洁员/督导员/驾驶员/维修工/管理员/考核员/转运工）", example = "李四")
    private String name;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}