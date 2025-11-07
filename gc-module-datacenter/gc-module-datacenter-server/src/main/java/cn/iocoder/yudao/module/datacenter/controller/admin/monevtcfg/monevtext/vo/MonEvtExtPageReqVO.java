package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 扩展监测事件配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonEvtExtPageReqVO extends PageParam {

    @Schema(description = "扩展ID，唯一编码，UUID生成", example = "13694")
    private String monEvtExtId;

    @Schema(description = "扩展小类代码，从080倒排")
    private String extMinorCode;

    @Schema(description = "扩展小类名称，如“智能充电桩过载事件”", example = "李四")
    private String extMinorName;

    @Schema(description = "所属中类ID，关联事件分类表中类ID；监测事件分类配置表(sys_mon_evt_cat)", example = "13359")
    private String parentMidId;

    @Schema(description = "所属中类名称，与中类ID同步，自动填充；监测事件分类配置表(sys_mon_evt_cat)", example = "王五")
    private String parentMidName;

    @Schema(description = "关联部件分类ID，关联部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)", example = "4664")
    private String relCompCatId;

    @Schema(description = "关联部件分类名称，与部件分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)", example = "王五")
    private String relCompCatName;

    @Schema(description = "扩展说明，描述扩展小类用途，如“用于记录充电桩过载事件”")
    private String extDesc;

    @Schema(description = "申请原因，说明扩展必要性，如“现有分类无法覆盖充电桩过载场景”", example = "不好")
    private String applyReason;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}