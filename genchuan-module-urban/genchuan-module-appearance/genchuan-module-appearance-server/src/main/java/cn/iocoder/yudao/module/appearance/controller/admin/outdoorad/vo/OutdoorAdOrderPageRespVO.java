package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 广告整改工单分页 Ad Order Page Response VO")
@Data
@ExcelIgnoreUnannotated
public class OutdoorAdOrderPageRespVO {
    @Schema(description = "工单主键ID（UUID）")
    @ExcelProperty("工单主键ID")
    private String orderId;

    @Schema(description = "工单编码，唯一（格式：AD-ORDER-[年月]-[6位随机数]）")
    @ExcelProperty("工单编码")
    private String orderCode;

    @Schema(description = "关联广告ID")//uuid,关联
    @ExcelProperty("关联广告ID")
    private String outdoorAdId;

    @Schema(description = "关联广告名称")//关联
    @ExcelProperty("关联广告名称")
    private String adName;

    @Schema(description = "关联广告编码")//关联
    @ExcelProperty("关联广告编码")
    private String adCode;

    @Schema(description = "问题描述（如尺寸超规/位置违规/画面破损）")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "要求整改完成时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("要求整改完成时间")
    private String requireTime;

    @Schema(description = "工单状态（待整改/整改中/已完成/已驳回/已关闭）")//关联
    @ExcelProperty("工单状态")
    private String orderStatus;

    @Schema(description = "处理人ID")//uuid,关联
    @ExcelProperty("处理人ID")
    private String handlerUserId;

    @Schema(description = "处理人真实姓名")//关联
    @ExcelProperty("处理人真实姓名")
    private String handlerName;

    @Schema(description = "创建时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private String createTime;

    @Schema(description = "创建人真实姓名")//关联
    @ExcelProperty("创建人真实姓名")
    private String createByName;

    @Schema(description = "更新时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    private String updateTime;

    @Schema(description = "区块链存证哈希值")
    @ExcelProperty("区块链存证哈希值")
    private String chainHash;
}
