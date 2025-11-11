package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格分域分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomDivGridPageReqVO extends PageParam {

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "分域编码")
    private String domCode;

    @Schema(description = "网格类型")
    private String gridType;

    @Schema(description = "网格ID")
    private String gridId;

    @Schema(description = "网格编码")
    private String gridCode;

    @Schema(description = "网格名称")
    private String gridName;

    @Schema(description = "所属行政区域代码")
    private String adminCode;

    @Schema(description = "所属行政区域名称")
    private String adminName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "网格员ID")
    private Long gridUserId;

    @Schema(description = "网格员姓名")
    private String gridUserName;

    @Schema(description = "分域状态")
    private Boolean domStatus;

    @Schema(description = "创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}