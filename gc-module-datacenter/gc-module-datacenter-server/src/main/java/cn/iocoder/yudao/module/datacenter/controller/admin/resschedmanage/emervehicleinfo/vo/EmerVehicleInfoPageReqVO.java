package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 救援车辆信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerVehicleInfoPageReqVO extends PageParam {

    @Schema(description = "车辆ID，唯一编码，UUID", example = "31493")
    private String vehicleId;

    @Schema(description = "车辆编码，系统自动生成")
    private String vehicleCode;

    @Schema(description = "车辆名称，标准名称", example = "赵六")
    private String vehicleName;

    @Schema(description = "分类ID，关联救援车辆分类配置表", example = "6899")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", example = "李四")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "李四")
    private String applyRegionName;

    @Schema(description = "车辆型号，如“XX型救援车”")
    private String vehicleModel;

    @Schema(description = "车辆数量，该类型车辆的总数量", example = "13755")
    private Integer vehicleCount;

    @Schema(description = "可用数量，可投入救援的车辆数量", example = "8699")
    private Integer availableVehicleCount;

    @Schema(description = "车辆状态，1（正常）/0（异常），默认1", example = "1")
    private String vehicleStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}