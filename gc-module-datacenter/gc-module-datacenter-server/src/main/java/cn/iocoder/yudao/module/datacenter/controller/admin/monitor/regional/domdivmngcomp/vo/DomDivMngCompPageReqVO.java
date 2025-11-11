package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件分域分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomDivMngCompPageReqVO extends PageParam {

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "分域编码")
    private String domCode;

    @Schema(description = "管理部件大类ID")
    private String compMajorId;

    @Schema(description = "管理部件大类名称")
    private String compMajorName;

    @Schema(description = "管理部件小类ID")
    private String compMinorId;

    @Schema(description = "管理部件小类名称")
    private String compMinorName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "主管部门代码")
    private String deptCode;

    @Schema(description = "主管部门名称")
    private String deptName;

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