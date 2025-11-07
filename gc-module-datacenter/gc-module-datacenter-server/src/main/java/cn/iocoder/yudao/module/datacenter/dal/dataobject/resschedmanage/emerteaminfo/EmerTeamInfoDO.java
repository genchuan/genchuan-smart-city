package cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteaminfo;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 救援队伍信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_team_info")
@KeySequence("biz_emer_team_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerTeamInfoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 队伍ID，唯一编码，UUID
     */
    private String teamId;
    /**
     * 队伍编码，系统自动生成
     */
    private String teamCode;
    /**
     * 队伍名称，标准名称
     */
    private String teamName;
    /**
     * 分类ID，关联救援队伍分类配置表
     */
    private String catId;
    /**
     * 分类名称，与分类ID同步
     */
    private String catName;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;
    /**
     * 队伍人数，队伍的总人数
     */
    private Integer teamMemberCount;
    /**
     * 可用人数，可参与救援的队员数量
     */
    private Integer availableMemberCount;
    /**
     * 队伍状态，1（正常）/0（异常），默认1
     */
    private String teamStatus;
    /**
     * 创建人，创建人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，更新人账号，关联用户信息表(sys_user)
     */
    private String updateUser;

}