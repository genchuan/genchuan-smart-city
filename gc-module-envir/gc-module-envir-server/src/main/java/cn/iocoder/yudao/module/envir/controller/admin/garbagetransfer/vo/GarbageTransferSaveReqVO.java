package cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 垃圾转运站新增/修改 Request VO")
@Data
public class GarbageTransferSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5132")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "16951")
    private String garbageTransferId;

    @Schema(description = "转运站名称", example = "芋艿")
    private String name;

    @Schema(description = "转运站位置（含经纬度）")
    private String location;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "核心设备（关联sys_equipment.sys_equipment_id，多个用逗号分隔）")
    private String equipmentIds;

    @Schema(description = "环境监测阈值")
    private String environmentThreshold;

    @Schema(description = "实时环境监测数据（含温度/湿度/异味浓度）")
    private String environmentData;

    @Schema(description = "转运去向（关联处理单位表ID）")
    private String transferDestination;

    @Schema(description = "负责人（关联sys_user.id）", example = "20650")
    private String managerId;

    @Schema(description = "运营状态（关联sys_operation_status.sys_operation_status_id）", example = "24012")
    private String operationStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "日转运量")
    private BigDecimal dailyTransferVolume;

    @Schema(description = "设备正常运行率")
    private BigDecimal equipmentRate;

    @Schema(description = "环境达标率")
    private BigDecimal environmentRate;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}