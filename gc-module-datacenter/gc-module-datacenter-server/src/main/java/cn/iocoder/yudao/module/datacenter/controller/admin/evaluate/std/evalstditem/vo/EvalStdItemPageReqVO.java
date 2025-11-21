package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 标准项管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalStdItemPageReqVO extends PageParam {

    @Schema(description = "标准项ID")
    private String stdItemId;

    @Schema(description = "标准项名称")
    private String stdItemName;

    @Schema(description = "标准项编码")
    private String stdItemCode;

    @Schema(description = "所属标准分类ID")
    private String stdCatId;

    @Schema(description = "所属标准分类名称")
    private String stdCatName;

    @Schema(description = "标准等级")
    private String stdLevel;

    @Schema(description = "分数下限")
    private BigDecimal scoreMin;

    @Schema(description = "分数上限")
    private BigDecimal scoreMax;

    @Schema(description = "标准描述")
    private String stdDesc;

    @Schema(description = "排序序号")
    private Integer sortNum;

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