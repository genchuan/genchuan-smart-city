package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 泊位查询分页 Request VO")
@Data
public class SpaceQueryPageReqVO extends PageParam {

    @Schema(description = "泊位编号")
    private String spaceNo;

    @Schema(description = "查询时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] queryTime;

    @Schema(description = "查询时间，时间戳格式")
    private String[] queryTimeNew;

    @Schema(description = "查询人ID，关联芋道用户表system_user", example = "17114")
    private Long queryUserId;

    @Schema(description = "片区ID，关联片区表", example = "10828")
    private Long areaId;

    @Schema(description = "泊位状态：空闲/占用，关联字典space_query_space_status", example = "1")
    private String spaceStatus;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者，创建人账号/姓名")
    private String creator;

    @Schema(description = "更新者，更新人账号/姓名")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}