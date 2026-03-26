package cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用户基础信息登记分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBasicInfoPageReqVO extends PageParam {

    @Schema(description = "用户编号")
    private String userCode;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "身份证号")
    private String idCardNo;

    @Schema(description = "家庭住址")
    private String address;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "开户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] openDate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}