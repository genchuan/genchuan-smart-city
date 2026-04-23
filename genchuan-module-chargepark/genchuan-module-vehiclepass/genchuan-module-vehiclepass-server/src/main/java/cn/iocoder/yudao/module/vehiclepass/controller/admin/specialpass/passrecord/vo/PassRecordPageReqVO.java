package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 放行记录分页 Request VO")
@Data
public class PassRecordPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "放行原因：人工开闸 / 特殊车辆 / 其他，关联字典：pass_record_pass_reason", example = "不好")
    private String passReason;

    @Schema(description = "放行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] passTime;

    @Schema(description = "抓拍图片地址", example = "https://www.iocoder.cn")
    private String imageUrl;

    @Schema(description = "状态：正常记录 / 异常记录，关联字典：pass_record_status", example = "1")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "10614")
    private Long stationId;

    @Schema(description = "操作人ID，关联芋道用户表 system_user", example = "48")
    private Long operatorId;

    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] operatorTime;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}