package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 异常离场分页 Request VO")
@Data
public class AbnormalLeavePageReqVO extends PageParam {

    @Schema(description = "车牌，支持模糊查询")
    private String plateNo;

    @Schema(description = "异常类型：逃费离场/道闸故障离场/无牌车离场/其他")
    private String abnormalType;

    @Schema(description = "识别时间，时间戳格式")
    private String[] identifyTime;

    @Schema(description = "处置状态：未处理/处理中/已关闭")
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "处置人ID")
    private Long handleUserId;

    @Schema(description = "备注")
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