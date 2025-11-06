package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 救援设备信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerDeviceInfoPageReqVO extends PageParam {

    @Schema(description = "设备ID，唯一编码，UUID", example = "26573")
    private String deviceId;

    @Schema(description = "设备编码，系统自动生成")
    private String deviceCode;

    @Schema(description = "设备名称，标准名称", example = "李四")
    private String deviceName;

    @Schema(description = "分类ID，关联救援设备分类配置表", example = "24923")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", example = "王五")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "李四")
    private String applyRegionName;

    @Schema(description = "设备型号，如“XX型救援设备”")
    private String deviceModel;

    @Schema(description = "设备数量，该类型设备的总数量", example = "614")
    private Integer deviceCount;

    @Schema(description = "可用数量，可投入救援的设备数量", example = "22219")
    private Integer availableDeviceCount;

    @Schema(description = "设备状态，1（正常）/0（异常），默认1", example = "1")
    private String deviceStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}