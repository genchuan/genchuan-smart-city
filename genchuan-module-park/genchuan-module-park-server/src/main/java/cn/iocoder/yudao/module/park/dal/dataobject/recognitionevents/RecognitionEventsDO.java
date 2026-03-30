package cn.iocoder.yudao.module.park.dal.dataobject.recognitionevents;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 车牌识别事件 DO
 *
 * @author zhucongquan
 */
@TableName("plate_recognition_events")
@KeySequence("plate_recognition_events_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecognitionEventsDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事件类型
     */
    private String type;
    /**
     * 协议模式
     */
    private Integer mode;
    /**
     * 协议版本号
     */
    private String protoVer;
    /**
     * 车牌号码
     */
    private String plateNum;
    /**
     * 车牌底色
     */
    private String plateColor;
    /**
     * 是否真牌
     */
    private Boolean plateVal;
    /**
     * 置信度
     */
    private Integer confidence;
    /**
     * 车辆品牌
     */
    private String carLogo;
    /**
     * 车辆子品牌
     */
    private String carSublogo;
    /**
     * 车辆颜色
     */
    private String carColor;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 识别时间戳
     */
    private Integer startTime;
    /**
     * 车场ID
     */
    private String parkId;
    /**
     * 相机ID
     */
    private String camId;
    /**
     * 相机IP地址
     */
    private String camIp;
    /**
     * 出入口类型
     */
    private String vdcType;
    /**
     * 是否白名单车辆
     */
    private Boolean isWhitelist;
    /**
     * 触发类型
     */
    private String trigerType;
    /**
     * 加密验证是否成功
     */
    private Boolean encryptVerify;
    /**
     * 全景图的BASE64编码
     */
    private String picture;
    /**
     * 车牌特写图的BASE64编码
     */
    private String closeupPic;
    /**
     * 记录创建时间
     */
    private LocalDateTime createdAt;

}
