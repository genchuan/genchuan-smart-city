package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.accessrecord;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 通行记录 DO
 *
 * @author 亘川智城
 */
@TableName("access_record")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessRecordDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [人脸信息ID] 关联人脸信息表主键
     */
    private Long faceId;
    /**
     * [人员姓名] 通行人员姓名
     */
    private String userName;
    /**
     * [通行区域] 通行区域
     */
    private String accessArea;
    /**
     * [通行时间] 通行发生时间
     */
    private LocalDateTime accessTime;
    /**
     * [验证方式] 验证方式
     */
    private String verifyType;
    /**
     * [通行状态] 通行状态
     */
    private String accessStatus;
    /**
     * [抓拍照片] 通行抓拍照片存储路径
     */
    private String snapImg;
    /**
     * [核查结果] 异常通行核查结论
     */
    private String checkResult;
    /**
     * [告警状态] 异常通行告警内容
     */
    private String alarmStatus;
    /**
     * [处置结果] 异常通行处置结论
     */
    private String handleResult;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}
