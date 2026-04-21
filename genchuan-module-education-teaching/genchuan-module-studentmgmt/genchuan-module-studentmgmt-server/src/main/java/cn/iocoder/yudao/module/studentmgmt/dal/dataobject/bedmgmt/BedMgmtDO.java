package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 床位管理 DO
 *
 * @author 芋道源码
 */
@TableName("bed_mgmt")
@KeySequence("bed_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BedMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 楼栋
     */
    private String building;
    /**
     * 楼层
     */
    private Integer floor;
    /**
     * 房间号
     */
    private String roomNum;
    /**
     * 床位号
     */
    private String bedNum;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 分配时间
     */
    private LocalDateTime assignTime;
    /**
     * 调整时间
     */
    private LocalDateTime adjustTime;
    /**
     * 状态：未分配/已分配
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}