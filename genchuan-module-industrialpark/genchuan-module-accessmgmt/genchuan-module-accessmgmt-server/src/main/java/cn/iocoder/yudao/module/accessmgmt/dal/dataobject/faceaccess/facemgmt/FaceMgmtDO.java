package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.facemgmt;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 人脸信息 DO
 *
 * @author 亘川智城
 */
@TableName("face_mgmt")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaceMgmtDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [人员姓名] 人员姓名
     */
    private String userName;
    /**
     * [手机号] 手机号
     */
    private String phone;
    /**
     * [所属企业] 所属企业
     */
    private String company;
    /**
     * [通行区域] 通行区域
     */
    private String accessArea;
    /**
     * [权限有效期] 权限有效期
     */
    private LocalDateTime authValidity;
    /**
     * [权限状态] 如:已授权/未授权/已过期
     */
    private String authStatus;
    /**
     * [通行次数] 通行次数
     */
    private Integer accessCount;
    /**
     * [最后通行时间] 最后通行时间
     */
    private LocalDateTime lastAccessTime;
    /**
     * [验证准确率] 验证准确率
     */
    private BigDecimal verifyAccuracy;
    /**
     * [操作人账号] 操作人账号，关联芋道用户表
     */
    private String handleUser;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}