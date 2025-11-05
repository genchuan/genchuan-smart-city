package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测事件数据配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonEvtDataCfgPageReqVO extends PageParam {

    @Schema(description = "配置ID，唯一编码，UUID生成", example = "30661")
    private String monEvtDataId;

    @Schema(description = "事件分类ID，关联事件分类表小类ID，监测事件分类配置表(sys_mon_evt_cat)", example = "16807")
    private String evtCatId;

    @Schema(description = "事件分类名称，与分类ID同步，自动填充，监测事件分类配置表(sys_mon_evt_cat)", example = "芋艿")
    private String evtCatName;

    @Schema(description = "字段名称，属性字段名称，参照GB/T XXXXX.7", example = "张三")
    private String fieldName;

    @Schema(description = "字段代码，蛇形命名法，与数据库字段一致")
    private String fieldCode;

    @Schema(description = "字段类型，char/varchar/int等，字段数据类型", example = "2")
    private String fieldType;

    @Schema(description = "字段长度，18/32/10等，字段长度及精度")
    private String fieldLength;

    @Schema(description = "约束条件，必选/可选，标识字段是否必选", example = "1")
    private String constraintType;

    @Schema(description = "值域范围，字段允许值域，如“一级/二级/三级”")
    private String valueRange;

    @Schema(description = "字段说明，字段填写说明，如“事发位置需含具体路名”")
    private String fieldDesc;

    @Schema(description = "创建人，填写创建人账号，用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，填写更新人账号，用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留字段，用于存储配置额外属性（如“字段校验规则”）")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留字段，用于存储配置额外属性（如“字段校验规则”）")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留通用字段，存储额外配置信息")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留通用字段，存储额外配置信息")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}