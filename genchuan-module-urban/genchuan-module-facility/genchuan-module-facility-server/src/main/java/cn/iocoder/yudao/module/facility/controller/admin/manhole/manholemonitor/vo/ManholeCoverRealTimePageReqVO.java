package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import groovy.transform.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖监测数据分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManholeCoverRealTimePageReqVO extends PageParam {

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "所属区域ID", example = "d2e3f4g5-h6i7-8901-defg-123456789abc")
    private String areaId;

    @Schema(description = "井盖状态 (0-正常，1-倾斜，2-位移，3-开启，4-水淹，5-设备离线)", example = "2")
    private Integer coverStatus;

    @Schema(description = "监测数据起始时间，格式yyyy-MM-dd HH:mm:ss")
    private LocalDateTime monitorTimeStart;

    @Schema(description = "监测数据结束时间，格式yyyy-MM-dd HH:mm:ss")
    private LocalDateTime monitorTimeEnd;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    private String tenantId;

}
