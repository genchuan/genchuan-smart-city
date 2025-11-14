package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应用场景分域分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomDivAppScenePageReqVO extends PageParam {

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "分域编码")
    private String domCode;

    @Schema(description = "应用场景大类ID")
    private String appSceneMajorId;

    @Schema(description = "应用场景大类名称")
    private String appSceneMajorName;

    @Schema(description = "应用场景小类ID")
    private String appSceneMinorId;

    @Schema(description = "应用场景小类名称")
    private String appSceneMinorName;

    @Schema(description = "关联点位数量(个)")
    private Integer relPtCount;

    @Schema(description = "关联设备数量(个)")
    private Integer relDeviceCount;

    @Schema(description = "关联事件数量(件)")
    private Integer relEvtCount;

    @Schema(description = "场景负责人ID")
    private Long sceneMngrId;

    @Schema(description = "场景负责人姓名")
    private String sceneMngrName;

    @Schema(description = "主管部门代码")
    private String deptCode;

    @Schema(description = "主管部门名称")
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