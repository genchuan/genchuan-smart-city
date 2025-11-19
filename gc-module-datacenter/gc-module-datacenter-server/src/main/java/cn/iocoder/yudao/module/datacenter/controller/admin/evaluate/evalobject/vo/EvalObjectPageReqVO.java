package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价对象管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalObjectPageReqVO extends PageParam {

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "对象编码")
    private String objectCode;

    @Schema(description = "所属行政区划代码")
    private String regionCode;

    @Schema(description = "所属行政区划名称")
    private String regionName;

    @Schema(description = "关联网格ID")
    private String gridId;

    @Schema(description = "关联网格名称")
    private String gridName;

    @Schema(description = "关联部门ID")
    private String deptId;

    @Schema(description = "关联部门名称")
    private String deptName;

    @Schema(description = "负责人ID")
    private String leaderId;

    @Schema(description = "负责人姓名")
    private String leaderName;

    @Schema(description = "负责人联系方式")
    private String leaderContact;

    @Schema(description = "对象状态")
    private String objectStatus;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

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