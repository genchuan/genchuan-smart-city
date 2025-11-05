package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 监测部件扩展配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonCompExtPageReqVO extends PageParam {

    @Schema(description = "扩展配置ID，唯一编码，UUID生成", example = "15638")
    private String extId;

    @Schema(description = "部件ID，关联监测部件信息表ID；监测部件信息表(biz_mon_comp_info)", example = "7669")
    private String compId;

    @Schema(description = "扩展键")
    private String extKey;

    @Schema(description = "扩展值")
    private String extValue;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}