package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆录入分页 Request VO")
@Data
public class CarInputPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", example = "7352")
    private Long spaceId;

    @Schema(description = "录入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inputTime;

    @Schema(description = "录入时间，时间戳格式")
    private String[] inputTimeNew;

    @Schema(description = "审核状态：待审核/已通过/已驳回，关联字典car_input_status", example = "1")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "15735")
    private Long areaId;

    @Schema(description = "录入人ID，关联芋道用户表system_user", example = "2919")
    private Long inputUserId;

    @Schema(description = "审核人ID，关联芋道用户表system_user", example = "18416")
    private Long auditUserId;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "备注", example = "你说的对")
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