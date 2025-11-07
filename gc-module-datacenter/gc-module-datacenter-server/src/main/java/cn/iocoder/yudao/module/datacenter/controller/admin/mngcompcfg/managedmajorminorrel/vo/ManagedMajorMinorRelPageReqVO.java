package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件大类小类关联分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManagedMajorMinorRelPageReqVO extends PageParam {

    @Schema(description = "关联ID，唯一编码，采用UUID", example = "2827")
    private String relId;

    @Schema(description = "关联的大类ID", example = "26751")
    private String majorId;

    @Schema(description = "关联的小类ID", example = "25666")
    private String minorId;

    @Schema(description = "关联状态：0-无效/1-有效", example = "1")
    private String relStatus;

    @Schema(description = "关联时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] relTime;

    @Schema(description = "解除关联时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] unrelTime;

    @Schema(description = "操作人ID")
    private String operateUser;

    @Schema(description = "备注信息", example = "你说的对")
    private String remark;

    @Schema(description = "扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    private String extField3;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}