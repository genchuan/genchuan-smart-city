package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 户外广告详情响应 VO")
@Data
public class OutdoorAdGetRespVO {
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
    @Schema(description = "实际尺寸", example = "8*4")
    private String actualSize;
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
    @Schema(description = "审批意见", example = "广告内容需修改")
    private String approvalOpinion;
    @Schema(description = "审批人真实姓名", example = "张三")
    private String approvalBy;
    @Schema(description = "审批时间", example = "2026-03-10 09:00:00")
    private LocalDateTime approvalTime;
    @Schema(description = "数据状态", example = "1")
    private Integer dataStatus;
    @Schema(description = "归档版本号", example = "2.0.0")
    private String archiveVersion;
    @Schema(description = "区块链存证哈希值", example = "0xabc...")
    private String chainHash;
    @Schema(description = "全生命周期ID", example = "LC-2026-AD-123456")
    private String lifeCycleId;
    @Schema(description = "附件文件列表")
    private List<AttachFile> attachFileList;
    @Schema(description = "创建时间", example = "2026-03-01 10:00:00")
    private LocalDateTime createTime;
    @Schema(description = "创建人真实姓名", example = "李四")
    private String createByName;
    @Schema(description = "更新时间", example = "2026-03-10 09:00:00")
    private LocalDateTime updateTime;
    @Schema(description = "更新人真实姓名", example = "张三")
    private String updateByName;

    @Data
    public static class AttachFile {
        @Schema(description = "文件ID", example = "01ed6e6a-ab2d-45b3-b11d-7c39f49da8e5")
        private String fileId;
        @Schema(description = "文件URL", example = "www.test5.com")
        private String fileUrl;
        @Schema(description = "文件名", example = "name5")
        private String fileName;
        @Schema(description = "文件类型", example = "type")
        private String fileType;
    }
}