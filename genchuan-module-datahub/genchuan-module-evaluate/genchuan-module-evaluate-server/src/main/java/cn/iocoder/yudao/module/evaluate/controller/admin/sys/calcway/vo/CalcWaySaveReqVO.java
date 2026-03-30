package cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 计算方式字典新增/修改 Request VO")
@Data
public class CalcWaySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22247")
    private Long id;

    @Schema(description = "方式ID（UUID）", example = "7635")
    private String wayId;

    @Schema(description = "方式名称", example = "王五")
    private String name;

    @Schema(description = "方式编码")
    private String code;

    @Schema(description = "方式描述")
    private String desc;

    @Schema(description = "业务创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}