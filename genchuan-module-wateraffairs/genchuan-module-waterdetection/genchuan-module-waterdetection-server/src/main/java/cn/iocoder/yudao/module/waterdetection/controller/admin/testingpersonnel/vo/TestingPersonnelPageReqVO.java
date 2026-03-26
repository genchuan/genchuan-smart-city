package cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测人员信息管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestingPersonnelPageReqVO extends PageParam {

    @Schema(description = "人员编号")
    private String staffNo;

    @Schema(description = "姓名")
    private String staffName;

    @Schema(description = "职称")
    private String position;

    @Schema(description = "资格证书编号")
    private String certificateNo;

    @Schema(description = "培训记录")
    private String trainingRecord;

    @Schema(description = "所属机构编号")
    private String agencyCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}