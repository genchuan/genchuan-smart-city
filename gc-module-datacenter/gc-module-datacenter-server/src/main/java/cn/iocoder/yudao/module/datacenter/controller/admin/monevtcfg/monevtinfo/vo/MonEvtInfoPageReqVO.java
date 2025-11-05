package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonEvtInfoPageReqVO extends PageParam {

    @Schema(description = "事件ID，唯一编码，UUID生成", example = "8646")
    private String monEvtId;

    @Schema(description = "事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)")
    private String evtCode;

    @Schema(description = "事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)", example = "芋艿")
    private String evtName;

    @Schema(description = "事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)", example = "3956")
    private String evtCatId;

    @Schema(description = "关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)", example = "11375")
    private String relCompId;

    @Schema(description = "关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)", example = "李四")
    private String relCompName;

    @Schema(description = "事发位置，事件发生位置，如 “XX路与XX路交叉口”")
    private String incidentPos;

    @Schema(description = "事发坐标X，经度，2000国家大地坐标系")
    private BigDecimal incidentX;

    @Schema(description = "事发坐标Y，纬度，2000国家大地坐标系")
    private BigDecimal incidentY;

    @Schema(description = "事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6")
    private String evtLevel;

    @Schema(description = "处置状态，待处置/处置中/已办结/已驳回，事件处置进度", example = "1")
    private String handleStatus;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}