package cn.iocoder.yudao.module.smartcity.dal.dataobject.basicillegal;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违建基本信息 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_basic_illegal")
@KeySequence("smartcity_basic_illegal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicIllegalDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 违建编号
     */
    private String buildingNumber;
    /**
     * 违建地址
     */
    private String buildingAddress;
    /**
     * 违建面积
     */
    private String constructionArea;
    /**
     * 违建结构类型
     */
    private String typesStructures;
    /**
     * 违建层数
     */
    private String numberFloors;
    /**
     * 违建用途
     */
    private String unauthorizedBuildings;
    /**
     * 违建发现时间
     */
    private LocalDateTime discoveryTime;
    /**
     * 涉及业主信息
     */
    private String involvingInformation;
    /**
     * 违建现状
     */
    private String currentSituation;
    /**
     * 所属区域
     */
    private String belongingRegion;
    /**
     * 周边环境描述
     */
    private String surroundingDescription;

}