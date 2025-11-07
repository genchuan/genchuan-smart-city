package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件信息新增/修改 Request VO")
@Data
public class MonEvtInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11183")
    private Long id;

    @Schema(description = "事件ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "8646")
    @NotEmpty(message = "事件ID，唯一编码，UUID生成不能为空")
    private String monEvtId;

    @Schema(description = "事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)不能为空")
    private String evtCode;

    @Schema(description = "事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)不能为空")
    private String evtName;

    @Schema(description = "事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "3956")
    @NotEmpty(message = "事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)不能为空")
    private String evtCatId;

    @Schema(description = "关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "11375")
    @NotEmpty(message = "关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)不能为空")
    private String relCompId;

    @Schema(description = "关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)不能为空")
    private String relCompName;

    @Schema(description = "事发位置，事件发生位置，如 “XX路与XX路交叉口”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事发位置，事件发生位置，如 “XX路与XX路交叉口”不能为空")
    private String incidentPos;

    @Schema(description = "事发坐标X，经度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事发坐标X，经度，2000国家大地坐标系不能为空")
    private BigDecimal incidentX;

    @Schema(description = "事发坐标Y，纬度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事发坐标Y，纬度，2000国家大地坐标系不能为空")
    private BigDecimal incidentY;

    @Schema(description = "事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6不能为空")
    private String evtLevel;

    @Schema(description = "处置状态，待处置/处置中/已办结/已驳回，事件处置进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "处置状态，待处置/处置中/已办结/已驳回，事件处置进度不能为空")
    private String handleStatus;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}