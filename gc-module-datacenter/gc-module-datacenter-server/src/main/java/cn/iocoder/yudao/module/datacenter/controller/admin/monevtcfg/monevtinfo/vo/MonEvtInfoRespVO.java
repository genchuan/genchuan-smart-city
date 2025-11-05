package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测事件信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonEvtInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11183")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事件ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "8646")
    @ExcelProperty("事件ID，唯一编码，UUID生成")
    private String monEvtId;

    @Schema(description = "事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)")
    private String evtCode;

    @Schema(description = "事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)")
    private String evtName;

    @Schema(description = "事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)", requiredMode = Schema.RequiredMode.REQUIRED, example = "3956")
    @ExcelProperty("事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)")
    private String evtCatId;

    @Schema(description = "关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "11375")
    @ExcelProperty("关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)")
    private String relCompId;

    @Schema(description = "关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)")
    private String relCompName;

    @Schema(description = "事发位置，事件发生位置，如 “XX路与XX路交叉口”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发位置，事件发生位置，如 “XX路与XX路交叉口”")
    private String incidentPos;

    @Schema(description = "事发坐标X，经度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标X，经度，2000国家大地坐标系")
    private BigDecimal incidentX;

    @Schema(description = "事发坐标Y，纬度，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标Y，纬度，2000国家大地坐标系")
    private BigDecimal incidentY;

    @Schema(description = "事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6")
    private String evtLevel;

    @Schema(description = "处置状态，待处置/处置中/已办结/已驳回，事件处置进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处置状态，待处置/处置中/已办结/已驳回，事件处置进度")
    private String handleStatus;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}