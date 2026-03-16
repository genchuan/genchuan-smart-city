package cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车牌识别事件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecognitionEventsPageReqVO extends PageParam {

    @Schema(description = "事件类型", example = "2")
    private String type;

    @Schema(description = "协议模式")
    private Integer mode;

    @Schema(description = "协议版本号")
    private String protoVer;

    @Schema(description = "车牌号码")
    private String plateNum;

    @Schema(description = "车牌底色")
    private String plateColor;

    @Schema(description = "是否真牌")
    private Boolean plateVal;

    @Schema(description = "置信度")
    private Integer confidence;

    @Schema(description = "车辆品牌")
    private String carLogo;

    @Schema(description = "车辆子品牌")
    private String carSublogo;

    @Schema(description = "车辆颜色")
    private String carColor;

    @Schema(description = "车辆类型", example = "1")
    private String vehicleType;

    @Schema(description = "识别时间戳")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] startTime;

    @Schema(description = "车场ID", example = "29568")
    private String parkId;

    @Schema(description = "相机ID", example = "30851")
    private String camId;

    @Schema(description = "相机IP地址")
    private String camIp;

    @Schema(description = "出入口类型", example = "1")
    private String vdcType;

    @Schema(description = "是否白名单车辆")
    private Boolean isWhitelist;

    @Schema(description = "触发类型", example = "1")
    private String trigerType;

    @Schema(description = "加密验证是否成功")
    private Boolean encryptVerify;

    @Schema(description = "全景图的BASE64编码")
    private String picture;

    @Schema(description = "车牌特写图的BASE64编码")
    private String closeupPic;

    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
