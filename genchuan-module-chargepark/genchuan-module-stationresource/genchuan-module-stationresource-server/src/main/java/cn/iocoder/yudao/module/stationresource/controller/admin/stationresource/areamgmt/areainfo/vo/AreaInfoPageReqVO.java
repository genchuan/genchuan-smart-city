package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 片区信息分页 Request VO")
@Data
public class AreaInfoPageReqVO extends PageParam {

    @Schema(description = "[片区id] ",hidden = true)
    private Long id;
    @Schema(description = "[片区编号] 唯一标识片区编号")
    private String areaNo;

    @Schema(description = "[片区名称] 片区名称", example = "芋艿")
    private String name;

//    @Schema(description = "[上级片区ID] 上级片区ID", example = "6415")
//    private Long parentId;

//    @Schema(description = "[省份] 省份")
//    private String province;
//
//    @Schema(description = "[城市] 城市")
//    private String city;

    @Schema(description = "[所属行政区划] 所属行政区划")
    private String district;

//    @Schema(description = "[详细地址] 详细地址")
//    private String address;

//    @Schema(description = "[负责人ID] 关联芋道用户表system_user", example = "18361")
//    private Long leaderId;
//
    @Schema(description = "[负责人名称] ",  example = "张得法")
//    @NotEmpty(message = "[负责人名称] 负责人名称不能为空")
    private String leaderName;

    @Schema(description = "[负责人ID] 关联芋道用户表system_user", example = "18362")
    private Long userId;

    @Schema(description = "[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[关联场站数] 关联场站数", example = "3066")
    private Integer stationCount;

    @Schema(description = "[状态] 如:未生效/已生效/已禁用", example = "1")
    private String status;

    @Schema(description = "[绑定时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bindTime;

    @Schema(description = "[绑定人ID] 关联芋道用户表system_user", example = "18363")
    private Long bindUserId;

    @Schema(description = "[备注] 备注", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
