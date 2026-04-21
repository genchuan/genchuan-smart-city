package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 救援信息 DO
 *
 * 数据库表：rescue_info
 *
 * @author carservice
 */
@TableName("rescue_info")
@KeySequence("rescue_info_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RescueInfoDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 用户 ID，关联芋道用户表 system_user
     */
    private Long userId;
    /**
     * 救援位置，记录救援地址或经纬度信息（格式："经度,纬度"）
     */
    private String location;
    /**
     * 救援位置汉字地址（供列表页展示，前端地图 SDK 选点时回传）
     */
    private String locationName;
    /**
     * 救援类型：道路救援/充电故障救援/停车故障救援
     * 关联字典 rescue_info_rescue_type
     */
    private String rescueType;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 救援状态：待派发/待认领/处理中/已完成
     * 关联字典 rescue_info_status
     */
    private String status;
    /**
     * 救援人员 ID，关联芋道用户表 system_user
     */
    private Long rescueUserId;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 处理时长（秒）
     */
    private Integer handleDuration;
    /**
     * 评价得分，1-5 分
     */
    private Integer score;
    /**
     * 归档状态：未归档/已归档
     * 关联字典 rescue_info_archive_status
     */
    private String archiveStatus;
    /**
     * 派发备注
     */
    private String dispatchRemark;
    /**
     * 转派理由
     */
    private String transferReason;
    /**
     * 救援进度
     */
    private String progress;
    /**
     * 现场照片，存储地址
     */
    private String photo;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;

}
