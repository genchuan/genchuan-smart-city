package cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 企业员工分页 Request VO")
@Data
public class StaffMgmtPageReqVO extends PageParam {

    @Schema(description = "员工姓名")
    private String staffName;

    @Schema(description = "企业ID")
    private Long enterpriseId;

    @Schema(description = "所属部门")
    private String deptName;

    @Schema(description = "岗位")
    private String postName;

    @Schema(description = "权限状态")
    private String authStatus;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "授权人账号")
    private String authUser;

    @Schema(description = "操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}