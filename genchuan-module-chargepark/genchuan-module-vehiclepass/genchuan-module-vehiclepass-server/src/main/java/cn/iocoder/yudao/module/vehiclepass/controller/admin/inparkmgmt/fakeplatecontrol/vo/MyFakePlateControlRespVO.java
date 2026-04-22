package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "套牌管控 分页回显 VO")
public class MyFakePlateControlRespVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "车牌", example = "闽C12345")
    private String plateNo;

    @Schema(description = "识别时间（时间戳）", example = "1775011986")
    private String identifyTime;

    @Schema(description = "匹配场景", example = "同牌多停")
    private String matchScene;

    @Schema(description = "处置状态", example = "未处理")
    private String status;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

    @Schema(description = "场站名称", example = "XX停车场")
    private String stationName; // 关联显示名称

    @Schema(description = "处置人ID", example = "1")
    private Long handleUserId;

    @Schema(description = "处置人姓名", example = "管理员")
    private String handleUserName; // 关联显示名称

    @Schema(description = "处置时间（时间戳）", example = "1775011986")
    private String handleTime;

    @Schema(description = "处置进度", example = "")
    private String handleProgress;

    @Schema(description = "处理类型：核查 / 忽略", example = "核查")
    private String handleType;

    @Schema(description = "忽略理由", example = "")
    private String ignoreReason;

    @Schema(description = "备注", example = "")
    private String remark;

    @Schema(description = "备用字段1", example = "")
    private String reserve1;

    @Schema(description = "备用字段2", example = "")
    private String reserve2;

    @Schema(description = "创建者", example = "admin")
    private String creator;

    @Schema(description = "更新者", example = "admin")
    private String updater;

    @Schema(description = "创建时间（时间戳）", example = "1775011986")
    private String createTime;

    @Schema(description = "更新时间（时间戳）", example = "1775011986")
    private String updateTime;
}