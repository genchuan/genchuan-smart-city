package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标分类管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalIdxCatPageReqVO extends PageParam {

    @Schema(description = "指标分类ID")
    private String idxCatId;

    @Schema(description = "指标分类名称")
    private String idxCatName;

    @Schema(description = "分类编码")
    private String catCode;

    @Schema(description = "所属指标体系ID")
    private String idxSystemId;

    @Schema(description = "所属指标体系名称")
    private String idxSystemName;

    @Schema(description = "排序序号")
    private Integer sortNum;

    @Schema(description = "分类描述")
    private String catDesc;

    @Schema(description = "启用状态")
    private String enableStatus;

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