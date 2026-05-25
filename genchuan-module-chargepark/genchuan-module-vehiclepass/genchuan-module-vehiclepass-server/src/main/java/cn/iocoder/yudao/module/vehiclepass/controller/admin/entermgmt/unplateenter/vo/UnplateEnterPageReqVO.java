package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 无牌入场分页 Request VO")
@Data
public class UnplateEnterPageReqVO extends PageParam {

    @Schema(description = "车辆类型：小型车/中型车/大型车/其他，关联字典unplate_enter_car_type", example = "2")
    private String carType;

    @Schema(description = "车辆颜色")
    private String carColor;

    @Schema(description = "联系电话")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "登记时间")
    private LocalDateTime[] registerTime;

    @Schema(description = "审核状态：待审核/已通过/已驳回，关联字典unplate_enter_status", example = "2")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "28427")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "审核人ID，关联system_user用户表", example = "23198")
    private Long auditUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间")
    private LocalDateTime[] auditTime;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "备注", example = "随便")
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
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime[] updateTime;

}