package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件大类配置表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManagedComponentMajorConfigPageReqVO extends PageParam {

    @Schema(description = "大类ID，唯一编码，采用UUID", example = "20918")
    private String majorId;

    @Schema(description = "大类代码，01-99")
    private String majorCode;

    @Schema(description = "大类名称，如市政公用设施/交通设施等", example = "赵六")
    private String majorName;

    @Schema(description = "大类说明，描述包含的管理部件范围")
    private String majorDesc;

    @Schema(description = "排序序号，1-999")
    private Integer sortNum;

    @Schema(description = "创建人ID")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID")
    private String updateUser;

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