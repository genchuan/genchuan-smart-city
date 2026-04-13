package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 校企合作分页 Request VO")
@Data
public class CoopEnterprisePageReqVO extends PageParam {

    @Schema(description = "企业名称", example = "张三")
    private String enterpriseName;

    @Schema(description = "企业类型：国企/民企/外企", example = "1")
    private String enterpriseType;

    @Schema(description = "负责系部", example = "22973")
    private Long deptId;

    @Schema(description = "联系人")
    private String contactUser;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "合作开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] coopStartTime;

    @Schema(description = "合作结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] coopEndTime;

    @Schema(description = "状态：合作中/已结束", example = "2")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}