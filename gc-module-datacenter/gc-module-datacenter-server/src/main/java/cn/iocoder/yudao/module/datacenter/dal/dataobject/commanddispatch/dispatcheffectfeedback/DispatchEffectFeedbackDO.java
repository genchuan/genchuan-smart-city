package cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatcheffectfeedback;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 调度效果反馈 DO
 *
 * @author 亘川智城
 */
@TableName("biz_dispatch_effect_feedback")
@KeySequence("biz_dispatch_effect_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchEffectFeedbackDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 反馈ID，唯一编码，UUID生成
     */
    private String feedbackId;
    /**
     * 关联指令ID，关联调度指令表
     */
    private String commandId;
    /**
     * 指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）
     */
    private String commandNo;
    /**
     * 执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）
     */
    private String execUnitId;
    /**
     * 执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）
     */
    private String execUnitName;
    /**
     * 执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”
     */
    private String effectDesc;
    /**
     * 反馈附件路径，执行现场照片/视频路径，多个用逗号分隔
     */
    private String feedbackAttachPath;
    /**
     * 反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）
     */
    private String feedbackUserId;
    /**
     * 反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）
     */
    private String feedbackUserName;
    /**
     * 反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime feedbackTime;
    /**
     * 评估结果，有效/部分有效/无效，管理员评估结果
     */
    private String evalResult;
    /**
     * 评估说明，评估理由，如“救援及时，转移人员到位，效果有效”
     */
    private String evalDesc;
    /**
     * 评估人ID，管理员账号，评估后必填，关联用户信息表（sys_user）
     */
    private String evalUserId;
    /**
     * 评估时间，格式yyyy-MM-dd HH:mm:ss，评估后必填
     */
    private LocalDateTime evalTime;
    /**
     * 分类扩展字段1，预留，如“效果评分（1-5分）”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“效果评分（1-5分）”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储评估报告路径
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储评估报告路径
     */
    private String extCommon2;

}