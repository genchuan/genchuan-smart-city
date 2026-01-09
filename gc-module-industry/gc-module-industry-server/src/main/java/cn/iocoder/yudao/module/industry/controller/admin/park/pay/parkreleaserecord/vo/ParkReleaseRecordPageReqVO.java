package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 放行记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkReleaseRecordPageReqVO extends PageParam {

    @Schema(description = "关联缴费记录标识", example = "31998")
    private Long payId;

    @Schema(description = "放行指令唯一标识", example = "13115")
    private Long releaseCmdId;

    @Schema(description = "放行状态（未放行，已放行）", example = "2")
    private String releaseStatus;

    @Schema(description = "实际放行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] releaseTime;

    @Schema(description = "闸机唯一标识", example = "839")
    private Long gateId;

    @Schema(description = "执行放行操作人员", example = "张三")
    private String operatorName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
