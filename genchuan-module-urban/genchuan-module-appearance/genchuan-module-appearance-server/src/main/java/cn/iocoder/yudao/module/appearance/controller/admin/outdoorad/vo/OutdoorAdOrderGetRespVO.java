package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 广告工单 Ad Order Get Response VO")
@Data
@ExcelIgnoreUnannotated
public class OutdoorAdOrderGetRespVO {
    @Schema(description = "工单主键ID（UUID）")
    @ExcelProperty("工单主键ID（UUID）")
    private String orderId;

    @Schema(description = "工单编码")
    @ExcelProperty("工单编码")
    private String orderCode;

    @Schema(description = "关联广告ID")
    @ExcelProperty("关联广告ID")
    private String outdoorAdId;

    @Schema(description = "关联广告名称")
    @ExcelProperty("关联广告名称")
    private String adName;

    @Schema(description = "关联广告编码")
    @ExcelProperty("关联广告编码")
    private String adCode;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "问题图片列表")
    @ExcelProperty("问题图片列表")
    private List<OnsiteImageVO> problemImgList;

    @Schema(description = "要求整改完成时间")
    @ExcelProperty("要求整改完成时间")
    private String requireTime;

    @Schema(description = "工单状态")
    @ExcelProperty("工单状态")
    private String orderStatus;

    @Schema(description = "处理人ID")
    @ExcelProperty("处理人ID")
    private String handlerUserId;

    @Schema(description = "处理人真实姓名")
    @ExcelProperty("处理人真实姓名")
    private String handlerName;

    @Schema(description = "处理说明")
    @ExcelProperty("处理说明")
    private String handlingDesc;

    @Schema(description = "整改后图片列表")
    @ExcelProperty("整改后图片列表")
    private List<OnsiteImageVO> handleImgList;

    @Schema(description = "整改完成时间")
    @ExcelProperty("整改完成时间")
    private String completeTime;

    @Schema(description = "驳回原因")
    @ExcelProperty("驳回原因")
    private String rejectReason;

    @Schema(description = "关闭原因")
    @ExcelProperty("关闭原因")
    private String closeReason;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private String createTime;

    @Schema(description = "创建人真实姓名")
    @ExcelProperty("创建人真实姓名")
    private String createByName;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private String updateTime;

    @Schema(description = "更新人真实姓名")
    @ExcelProperty("更新人真实姓名")
    private String updateByName;

    @Schema(description = "区块链存证哈希值")
    @ExcelProperty("区块链存证哈希值")
    private String chainHash;

    @Schema(description = "全生命周期ID")
    @ExcelProperty("全生命周期ID")
    private String lifeCycleId;

    /**
     * 附件文件信息
     */
    @Data
    @Schema(description = "现场图片列表")
    public static class OnsiteImageVO {

        @Schema(description = "文件ID")
        @ExcelProperty("文件ID")
        private String fileId;

        @Schema(description = "文件URL")
        @ExcelProperty("文件URL")
        private String fileUrl;

        @Schema(description = "文件名")
        @ExcelProperty("文件名")
        private String fileName;
    }
}
