package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价主体管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalSubjectPageReqVO extends PageParam {

    @Schema(description = "评价主体ID")
    private String evalSubjectId;

    @Schema(description = "评价主体名称")
    private String evalSubjectName;

    @Schema(description = "主体编码")
    private String subjectCode;

    @Schema(description = "主体类型")
    private String subjectType;

    @Schema(description = "所属部门ID")
    private String deptId;

    @Schema(description = "所属部门名称")
    private String deptName;

    @Schema(description = "成员IDs")
    private String memberIds;

    @Schema(description = "成员姓名")
    private String memberNames;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "主体状态")
    private String subjectStatus;

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