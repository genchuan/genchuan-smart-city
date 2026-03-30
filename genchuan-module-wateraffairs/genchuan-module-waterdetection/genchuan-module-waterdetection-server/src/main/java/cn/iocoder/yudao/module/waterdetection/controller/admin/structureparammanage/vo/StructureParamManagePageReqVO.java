package cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 构建筑物参数管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StructureParamManagePageReqVO extends PageParam {

    @Schema(description = "构建筑物名称")
    private String structureName;

    @Schema(description = "类型(沉淀池/滤池/清水池等)")
    private String structureType;

    @Schema(description = "长度(米)")
    private Double length;

    @Schema(description = "宽度(米)")
    private Double width;

    @Schema(description = "深度(米)")
    private Double depth;

    @Schema(description = "有效容积(立方米)")
    private Double effectiveVolume;

    @Schema(description = "建设时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] constructionTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}