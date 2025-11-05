package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;



@Schema(description = "管理后台 - 管理部件图示符号库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngCompSymbolLibPageReqVO extends PageParam {

    @Schema(description = "符号库ID，唯一编码，UUID生成", example = "13064")
    private String symbolLibId;

    @Schema(description = "符号名称", example = "王五")
    private String symbolName;

    @Schema(description = "符号路径")
    private String symbolPath;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}