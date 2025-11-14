package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项分域分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomDivMngMatterPageReqVO extends PageParam {

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "分域编码")
    private String domCode;

    @Schema(description = "管理事项大类ID")
    private String matterMajorId;

    @Schema(description = "管理事项大类名称")
    private String matterMajorName;

    @Schema(description = "管理事项小类ID")
    private String matterMinorId;

    @Schema(description = "管理事项小类名称")
    private String matterMinorName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联事件数量(件)")
    private Integer relEvtCount;

    @Schema(description = "处置部门代码")
    private String deptCode;

    @Schema(description = "处置部门名称")
    private String deptName;

    @Schema(description = "分域状态")
    private Boolean domStatus;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

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