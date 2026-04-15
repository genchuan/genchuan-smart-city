package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改工单分页响应 VO")
@Data
public class OutdoorAdOrderPageRespVO {

    @Schema(description = "工单主键ID", example = "m3n4o5p6-q7r8-9012-mnop-345678901234")
    private String id;

    @Schema(description = "工单编码", example = "AD-ORDER-202603-123456")
    private String orderCode;

    @Schema(description = "关联广告ID", example = "k1l2m3n4-o5p6-7890-klmn-123456789012")
    private String adId;

    @Schema(description = "关联广告名称", example = "鼓楼区商业广场电子屏广告")
    private String adName;

    @Schema(description = "关联广告编码", example = "AD-202603-123456")
    private String adCode;

    @Schema(description = "问题描述", example = "电子屏画面局部破损，需更换显示模组")
    private String problemDesc;

    @Schema(description = "要求整改完成时间", example = "2026-03-20 18:00:00")
    private LocalDateTime requireTime;

    @Schema(description = "工单状态", example = "整改中")
    private String orderStatus;

    @Schema(description = "处理人ID", example = "n4o5p6q7-r8s9-0123-nopq-456789012345")
    private String handlerId;

    @Schema(description = "处理人真实姓名", example = "王五")
    private String handlerName;

    @Schema(description = "创建时间", example = "2026-03-10 14:00:00")
    private LocalDateTime createTime;

    @Schema(description = "创建人真实姓名", example = "张三")
    private String createByName;

    @Schema(description = "更新时间", example = "2026-03-15 10:00:00")
    private LocalDateTime updateTime;

    @Schema(description = "区块链存证哈希值", example = "0xabc123...")
    private String chainHash;
}