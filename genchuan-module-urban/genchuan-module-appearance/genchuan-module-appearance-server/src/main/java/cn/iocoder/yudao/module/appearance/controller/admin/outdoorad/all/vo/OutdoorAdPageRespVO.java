package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 户外广告分页 Page Response VO")
@Data
@ExcelIgnoreUnannotated
public class OutdoorAdPageRespVO {
    @Schema(description = "广告主键ID")
    @ExcelProperty("广告主键ID")
    private String outdoorAdId;

    @Schema(description = "广告编码，唯一（格式：AD-[年月]-[6位随机数]）")
    @ExcelProperty("广告编码")
    private String adCode;

    @Schema(description = "广告名称")
    @ExcelProperty("广告名称")
    private String name;

    @Schema(description = "广告类型（立柱/墙面/灯箱/电子屏）")
    @ExcelProperty("广告类型")
    private String type;

    @Schema(description = "广告尺寸（长*宽，单位：米）")
    @ExcelProperty("广告尺寸")
    private String approvedSize;

    @Schema(description = "广告具体位置")
    @ExcelProperty("广告位置")
    private String location;

    @Schema(description = "广告点位经度")
    @ExcelProperty("经度")
    private String lng;

    @Schema(description = "广告点位纬度")
    @ExcelProperty("纬度")
    private String lat;

    @Schema(description = "所属区域编码")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "所属网格编码")
    @ExcelProperty("网格编码")
    private String gridCode;

    @Schema(description = "所属网格名称")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "审批状态（待审批/已审批/已驳回）")
    @ExcelProperty("审批状态")
    private String approvalStatus;

    @Schema(description = "审批人真实姓名")
    @ExcelProperty("审批人")
    private String approvalName;

    @Schema(description = "审批时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("审批时间")
    private String endApprovalTime;

    @Schema(description = "数据状态 (0-未启用，1-已启用，2-已归档)")
    @ExcelProperty("数据状态")
    private Integer dataStatus;

    @Schema(description = "区块链存证哈希值")
    @ExcelProperty("区块链存证哈希")
    private String chainHash;

    @Schema(description = "创建时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private String createTime;

    @Schema(description = "创建人真实姓名")
    @ExcelProperty("创建人")
    private String createName;

    @Schema(description = "更新时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    private String updateTime;

    @Schema(description = "更新人真实姓名")
    @ExcelProperty("更新人")
    private String updateName;
}
