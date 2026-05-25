package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 结束停车分页 Request VO")
@Data
public class EndParkPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", example = "9244")
    private Long spaceId;

    @Schema(description = "结束时间，时间范围", example = "[2026-04-09 00:00:00, 2026-04-09 23:59:59]")
    private LocalDateTime[] endTime;

    @Schema(description = "缴费状态：待支付/已支付/已取消，关联字典end_park_status", example = "2")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "5559")
    private Long areaId;

    @Schema(description = "操作人ID，关联芋道用户表system_user", example = "8424")
    private Long operatorId;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "备注", example = "随便")
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