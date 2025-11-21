package cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.crossdomexp;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 跨域经验交流 DO
 *
 * @author 亘川智城
 */
@TableName("biz_cross_dom_exp")
@KeySequence("biz_cross_dom_exp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrossDomExpDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 经验ID，唯一编码，UUID生成
     */
    private String crossDomExpId;
    /**
     * 经验标题，如“上海一网统管平台设备联动经验”
     */
    private String expTitle;
    /**
     * 经验领域代码，取值如01=设备联动，02=数据融合，03=事件处置，经验领域字典表（sys_dict_exp_field）
     */
    private String expFieldCode;
    /**
     * 经验领域名称，与经验领域代码同步，经验领域字典表（sys_dict_exp_field）
     */
    private String expFieldName;
    /**
     * 来源城市，如“上海、深圳”，行政区划表（sys_area）
     */
    private String sourceCity;
    /**
     * 经验详细内容（支持富文本，如“1.设备联动：统一物联网设备协议；2.数据融合：建立跨部门数据中台”）
     */
    private String expDetail;
    /**
     * 适用场景，经验适用场景（如“设备数量超10万套的大型城市”）
     */
    private String applicableScenario;
    /**
     * 附件ID，关联经验附件ID（如方案文档、演示视频），文件存储表（sys_file_storage）
     */
    private String attachId;
    /**
     * 附件路径，附件存储路径，文件存储表（sys_file_storage）
     */
    private String attachPath;
    /**
     * 发布状态：已发布/草稿，标识经验是否公开
     */
    private String publishStatus;
    /**
     * 浏览次数，用户浏览经验的次数，默认0
     */
    private Integer viewCount;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“经验等级：优秀/良好”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“发布渠道：行业会议/官方推荐”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“关联政策：GB/T 30428.1”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“引用次数：5次”
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}