package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项大类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngMatterMajorPageReqVO extends PageParam {

    @Schema(description = "管理事项大类ID", example = "20451")
    private String mngMatterMajorId;

    @Schema(description = "大类代码")
    private String matterMajorCode;

    @Schema(description = "大类名称", example = "芋艿")
    private String matterMajorName;

    @Schema(description = "大类说明")
    private String matterMajorDesc;

    @Schema(description = "排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态", example = "1")
    private String enableStatus;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}