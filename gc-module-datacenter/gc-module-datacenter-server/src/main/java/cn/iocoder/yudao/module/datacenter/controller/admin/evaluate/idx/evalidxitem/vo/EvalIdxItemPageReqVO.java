package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标项管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalIdxItemPageReqVO extends PageParam {

    @Schema(description = "指标项ID")
    private String idxItemId;

    @Schema(description = "指标项名称")
    private String idxItemName;

    @Schema(description = "指标项编码")
    private String idxItemCode;

    @Schema(description = "所属指标分类ID")
    private String idxCatId;

    @Schema(description = "所属指标分类名称")
    private String idxCatName;

    @Schema(description = "指标类型")
    private String idxType;

    @Schema(description = "计算方式")
    private String calcMethod;

    @Schema(description = "数据来源表")
    private String dataSourceTable;

    @Schema(description = "数据来源字段")
    private String dataSourceField;

    @Schema(description = "达标阈值")
    private String qualifiedThreshold;

    @Schema(description = "指标描述")
    private String idxDesc;

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