package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 追缴配置新增/修改 Request VO")
@Data
public class CollectConfigSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "配置编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String configNo;
    @Schema(description = "追缴方式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String collectMethod;
    @Schema(description = "推送模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long templateId;
    @Schema(description = "推送频次（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushFrequency;
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String status;
    @Schema(description = "配置说明", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String remark;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
