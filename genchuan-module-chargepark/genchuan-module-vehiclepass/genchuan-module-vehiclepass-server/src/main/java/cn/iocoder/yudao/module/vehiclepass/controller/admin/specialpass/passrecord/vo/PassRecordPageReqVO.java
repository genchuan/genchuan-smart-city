package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 放行记录分页 Request VO")
@Data
public class PassRecordPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "放行原因：人工开闸 / 特殊车辆 / 其他", example = "人工开闸")
    private String passReason;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "放行时间，时间范围", example = "2026-04-20 00:00:00,2026-04-23 23:59:59")
    private LocalDateTime[] passTime;

    @Schema(description = "状态：正常记录 / 异常记录", example = "正常记录")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "10614")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "操作人ID，关联芋道用户表 system_user", example = "48")
    private Long operatorId;

    @Schema(description = "抓拍图片地址")
    private String imageUrl;

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

}