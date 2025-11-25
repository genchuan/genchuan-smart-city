package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo;

import lombok.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件信息管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngCompPageReqVO extends PageParam {

    @Schema(description = "部件ID，唯一编码，UUID生成", example = "15847")
    private String mngCompId;

    @Schema(description = "部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一")
    private String compCode;

    @Schema(description = "部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)", example = "芋艿")
    private String compName;

    @Schema(description = "关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)", example = "15559")
    private String minorId;

    @Schema(description = "关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)", example = "王五")
    private String minorName;

    @Schema(description = "主管部门代码，主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)", example = "赵六")
    private String deptName;

    @Schema(description = "关联单元网格ID，关联网格信息表(biz_grid_info)", example = "6907")
    private String gridId;

    @Schema(description = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)", example = "张三")
    private String gridName;

    @Schema(description = "部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)", example = "2")
    private String compStatus;

    @Schema(description = "部件普查日期，格式YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] initDate;

    @Schema(description = "状态/权属变更时更新日期，格式YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] changeDate;

    @Schema(description = "数据来源，如实测/人工上报/普查，关联数据来源字典表(sys_dict_data_source)")
    private String dataSource;

    @Schema(description = "录入人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "系统生成，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“部件材质”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“部件材质”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“安装时间”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“安装时间”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}