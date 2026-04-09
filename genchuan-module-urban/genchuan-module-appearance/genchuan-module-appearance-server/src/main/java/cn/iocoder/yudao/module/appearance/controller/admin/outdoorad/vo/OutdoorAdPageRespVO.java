package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户外广告分页响应 VO")
@Data
public class OutdoorAdPageRespVO {

    @Schema(description = "主键ID", example = "k1l2m3n4-o5p6-7890-klmn-123456789012")
    private String id;

    @Schema(description = "广告编码", example = "AD-202603-123456")
    private String adCode;

    @Schema(description = "广告名称", example = "鼓楼区商业广场电子屏广告")
    private String adName;

    @Schema(description = "广告类型", example = "电子屏")
    private String adType;

    @Schema(description = "广告尺寸", example = "8*4")
    private String adSize;

    @Schema(description = "广告具体位置", example = "鼓楼区XX路商业广场1号楼外立面")
    private String location;

    @Schema(description = "经度", example = "119.2988")
    private String lng;

    @Schema(description = "纬度", example = "26.0853")
    private String lat;

    @Schema(description = "所属区域编码", example = "350105")
    private String areaCode;

    @Schema(description = "所属区域名称", example = "鼓楼区")
    private String areaName;

    @Schema(description = "所属网格编码", example = "GRID-350105001-001")
    private String gridCode;

    @Schema(description = "所属网格名称", example = "鼓西街道第一网格")
    private String gridName;

    @Schema(description = "审批状态", example = "已审批")
    private String approvalStatus;

    @Schema(description = "审批人真实姓名", example = "张三")
    private String approvalBy;

    @Schema(description = "审批时间", example = "2026-03-10 09:00:00")
    private LocalDateTime approvalTime;

    @Schema(description = "数据状态", example = "1")
    private Integer dataStatus;

    @Schema(description = "区块链存证哈希值", example = "0xabc...")
    private String chainHash;

    @Schema(description = "创建时间", example = "2026-03-01 10:00:00")
    private LocalDateTime createTime;

    @Schema(description = "创建人真实姓名", example = "李四")
    private String createByName;

    @Schema(description = "更新时间", example = "2026-03-10 09:00:00")
    private LocalDateTime updateTime;

    @Schema(description = "更新人真实姓名", example = "张三")
    private String updateByName;
}