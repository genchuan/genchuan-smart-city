package cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 桥梁安全新增/修改 Request VO")
@Data
public class BridgeSafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8638")
    private Long id;

    @Schema(description = "桥梁名称", example = "王五")
    private String bridgeName;

    @Schema(description = "桥梁编号")
    private String bridgeNumber;

    @Schema(description = "桥梁位置")
    private String bridgeLocation;

    @Schema(description = "桥梁类型", example = "1")
    private String bridgeType;

    @Schema(description = "建造时间")
    private LocalDateTime constructionTime;

    @Schema(description = "设计荷载")
    private String designLoad;

    @Schema(description = "结构部件状况")
    private String conditionStructuralComponents;

    @Schema(description = "基础沉降数据")
    private String basicSettlementData;

    @Schema(description = "索力")
    private String cableForce;

}