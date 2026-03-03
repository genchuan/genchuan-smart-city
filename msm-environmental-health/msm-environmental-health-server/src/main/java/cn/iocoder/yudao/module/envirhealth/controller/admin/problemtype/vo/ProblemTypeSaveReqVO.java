package cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 问题类型字典表【通用复用】新增/修改 Request VO")
@Data
public class ProblemTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "159")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "20184")
    private String sysProblemTypeId;

    @Schema(description = "问题类型名称（可选值：污水排放/垃圾堆积/水生植物泛滥/设施损坏/保洁不达标/收运不及时/定位异常/投诉反馈/其他问题）", example = "芋艿")
    private String name;

    @Schema(description = "问题类型编码")
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