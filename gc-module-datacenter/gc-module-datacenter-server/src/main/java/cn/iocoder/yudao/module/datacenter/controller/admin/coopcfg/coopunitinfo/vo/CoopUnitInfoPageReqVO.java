package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 联动单位信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoopUnitInfoPageReqVO extends PageParam {

    @Schema(description = "单位ID，唯一编码，UUID生成", example = "16083")
    private String unitId;

    @Schema(description = "单位编码，系统自动生成，格式“UNIT+年份（4位）+6位流水号”")
    private String unitCode;

    @Schema(description = "单位名称，如“XX市消防救援支队XX中队”", example = "张三")
    private String unitName;

    @Schema(description = "单位类型，政府部门/企业/社会组织", example = "2")
    private String unitType;

    @Schema(description = "行政区划代码，关联行政区划表")
    private String regionCode;

    @Schema(description = "行政区划名称，与区域代码同步", example = "王五")
    private String regionName;

    @Schema(description = "负责人姓名", example = "张三")
    private String leaderName;

    @Schema(description = "负责人联系方式，加密存储")
    private String leaderContact;

    @Schema(description = "单位详细地址")
    private String contactAddress;

    @Schema(description = "联动范围，如“火灾救援、建筑物坍塌救援”")
    private String coopScope;

    @Schema(description = "单位状态，启用/禁用，默认启用", example = "2")
    private String unitStatus;

    @Schema(description = "创建人账号，关联用户信息表")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人账号，关联用户信息表")
    private String updateUser;

}