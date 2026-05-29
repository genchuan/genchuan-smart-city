package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 套牌管控分页 Request VO")
@Data
public class FakePlateControlPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "识别时间，时间范围", example = "2026-04-22 00:00:00,2026-04-22 23:59:59")
    private LocalDateTime[] identifyTime;

    @Schema(description = "匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene")
    private String matchScene;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status", example = "1")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "16619")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "处置人ID，关联system_user用户表", example = "16042")
    private Long handleUserId;

    @Schema(description = "处置人名称，支持模糊查询")
    private String checkUserName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "处置时间，时间范围")
    private LocalDateTime[] handleTime;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "处理类型：核查 / 忽略")
    private String handleType;

    @Schema(description = "忽略理由", example = "不对")
    private String ignoreReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间，时间范围")
    private LocalDateTime[] createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间，时间范围")
    private LocalDateTime[] updateTime;

}