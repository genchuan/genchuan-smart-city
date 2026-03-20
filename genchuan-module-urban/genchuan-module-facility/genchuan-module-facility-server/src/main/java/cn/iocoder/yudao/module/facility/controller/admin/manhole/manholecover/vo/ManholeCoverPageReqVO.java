package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 窨井盖设施分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManholeCoverPageReqVO extends PageParam {

    @Schema(description = "井盖编号（唯一）")
    private String coverNo;

    @Schema(description = "关联道路设施表road_facility的road_id", example = "13196")
    private Long roadId;

    @Schema(description = "井盖类型", example = "2")
    private String coverType;

    @Schema(description = "井盖规格（文本）")
    private String specification;

    @Schema(description = "安装时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] installTime;

    @Schema(description = "关联区域表sys_area的area_code")
    private String areaCode;

    @Schema(description = "使用状态", example = "2")
    private String status;

    @Schema(description = "创建时间（自动生成）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}