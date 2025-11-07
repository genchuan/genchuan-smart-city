package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基本地点数据统计报表新增/修改 Request VO")
@Data
public class GeocodingStatSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String statId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "区域数据数量")
    private Integer regionCount;

    @Schema(description = "地片与区片数据数量")
    private Integer zoneCount;

    @Schema(description = "街巷数据数量")
    private Integer streetCount;

    @Schema(description = "门牌楼牌数据数量")
    private Integer houseCount;

    @Schema(description = "兴趣点数据数量")
    private Integer poiCount;

    @Schema(description = "新增数据数量")
    private Integer newDataCount;

    @Schema(description = "更新数据数量")
    private Integer updateDataCount;

    @Schema(description = "统计人")
    private String statUser;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

}