package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 调度方案分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DispatchPlanPageReqVO extends PageParam {

    @Schema(description = "方案ID，唯一编码，UUID生成", example = "17786")
    private String planId;

    @Schema(description = "方案编号，系统自动生成，格式“DISP+年份（4位）+6位流水号”")
    private String planNo;

    @Schema(description = "关联响应ID，关联预案启动响应表", example = "24068")
    private String respId;

    @Schema(description = "响应编号，与响应ID同步，关联预案启动响应表（biz_plan_launch_resp）")
    private String respNo;

    @Schema(description = "方案名称，如“XX内涝救援资源调度方案”", example = "芋艿")
    private String planName;

    @Schema(description = "调度需求描述，如“需调配5辆救援车、200件救生衣至XX内涝点”")
    private String demandDesc;

    @Schema(description = "资源类型，应急物资/救援队伍/救援车辆/救援设备，标识调度资源类型", example = "2")
    private String resType;

    @Schema(description = "资源ID，关联对应资源表（物资/队伍等），应急物资信息表（biz_emer_mat_info）/救援队伍信息表（biz_emer_team_info）等", example = "22401")
    private String resId;

    @Schema(description = "资源名称，与资源ID同步，对应资源表", example = "张三")
    private String resName;

    @Schema(description = "调度数量，调度资源数量（物资/设备按单位，队伍按人数，车辆按辆）")
    private Integer dispatchQuantity;

    @Schema(description = "调配目的地，如“XX路与XX路交叉口内涝点”")
    private String dispatchDest;

    @Schema(description = "目的地坐标X，度，2000国家大地坐标系经度")
    private BigDecimal destCoordX;

    @Schema(description = "目的地坐标Y，度，2000国家大地坐标系纬度")
    private BigDecimal destCoordY;

    @Schema(description = "负责人ID，调度负责人账号，关联用户信息表（sys_user）", example = "10895")
    private String chargerId;

    @Schema(description = "负责人姓名，与负责人ID同步，关联用户信息表（sys_user）", example = "李四")
    private String chargerName;

    @Schema(description = "要求完成时间，格式yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] requireCompleteTime;

    @Schema(description = "方案状态，待执行/执行中/已完成/已取消，默认待执行", example = "1")
    private String planStatus;

    @Schema(description = "创建人，方案制定人账号，关联用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1，预留，如“调度优先级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“调度优先级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储调度路线规划路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储调度路线规划路径")
    private String extCommon2;

}