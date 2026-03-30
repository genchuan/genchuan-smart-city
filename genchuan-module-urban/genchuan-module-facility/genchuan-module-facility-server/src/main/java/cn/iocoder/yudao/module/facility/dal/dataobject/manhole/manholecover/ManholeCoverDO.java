package cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

/**
 * 窨井盖设施 DO
 *
 * @author 亘川智城
 */
@TableName("manhole_cover")
@KeySequence("manhole_cover_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManholeCoverDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 井盖编号（唯一）
     */
    private String coverNo;
    /**
     * 关联道路设施表road_facility的road_id
     */
    private Long roadId;
    /**
     * 井盖类型
     */
    private String coverType;
    /**
     * 井盖规格（文本）
     */
    private String specification;
    /**
     * 安装时间
     */
    private LocalDate installTime;
    /**
     * 关联区域表sys_area的area_code
     */
    private String areaCode;
    /**
     * 使用状态
     */
    private String status;

}