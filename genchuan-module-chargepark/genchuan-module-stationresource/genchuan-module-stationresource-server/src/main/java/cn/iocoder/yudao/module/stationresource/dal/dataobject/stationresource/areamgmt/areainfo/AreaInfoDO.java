package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 片区信息 DO
 *
 * @author 亘川智城
 */
@TableName("area_info")
@KeySequence("area_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaInfoDO extends BaseDO {

    /**
     * [主键ID] 主键ID
     */
    @TableId
    private Long id;
    /**
     * [片区编号] 唯一标识片区编号
     */
    private String areaNo;
    /**
     * [片区名称] 片区名称
     */
    private String name;
    /**
     * [上级片区ID] 上级片区ID
     */
    private Long parentId;
    /**
     * [省份] 省份
     */
    private String province;
    /**
     * [城市] 城市
     */
    private String city;
    /**
     * [区县] 区县
     */
    private String district;
    /**
     * [详细地址] 详细地址
     */
    private String address;
    /**
     * [负责人ID] 关联芋道用户表system_user
     */
    private Long leaderId;

    private String leaderName;
    /**
     * [负责人] 关联芋道用户表system_user
     */
    private Long userId;
    /**
     * [联系电话] 联系电话
     */
    private String phone;
    /**
     * [关联场站数] 关联场站数
     */
    private Integer stationCount;
    /**
     * [绑定时间]
     */
    private LocalDateTime bindTime;
    /**
     * [绑定人ID] 关联芋道用户表system_user
     */
    private Long bindUserId;
    /**
     * [状态] 如:未生效/已生效/已禁用
     */
    private String status;
    /**
     * [备注] 备注
     */
    private String remark;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;
}
