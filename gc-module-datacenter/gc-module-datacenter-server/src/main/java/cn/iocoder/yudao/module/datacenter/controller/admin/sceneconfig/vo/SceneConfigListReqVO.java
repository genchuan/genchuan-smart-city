package cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景分类列表 Request VO")
@Data
public class SceneConfigListReqVO {

    @Schema(description = "父级ID")
    private Long pid;

    @Schema(description = "场景名称")
    private String name;

    @Schema(description = "设备配置名称")
    private String deviceConfigName;

    @Schema(description = "资产配置名称")
    private String assetConfigName;

    @Schema(description = "流程配置名称")
    private String flowConfigName;

    @Schema(description = "备注")
    private String info;

    @Schema(description = "备用1")
    private String info1;

    @Schema(description = "备用2")
    private String info2;

    @Schema(description = "备用3")
    private String info3;

    @Schema(description = "备用4")
    private String info4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}