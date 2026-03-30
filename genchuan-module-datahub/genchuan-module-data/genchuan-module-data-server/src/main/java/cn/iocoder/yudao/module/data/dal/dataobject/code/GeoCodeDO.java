package cn.iocoder.yudao.module.data.dal.dataobject.code;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 地理编码 DO
 *
 * @author zhucongquan
 */
@TableName("geo_code")
@KeySequence("geo_code_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 地理编码
     */
    private String code;
    /**
     * 地点名称
     */
    private String locationName;
    /**
     * 关联行政区划代码
     */
    private String areaCode;
    /**
     * 关联图层类型ID
     */
    private String layerTypeId;
    /**
     * 北斗网格码（6-8级）
     */
    private String beidouGridCode;
    /**
     * 经度（2000国家大地坐标系，精度6位小数）
     */
    private BigDecimal longitude;
    /**
     * 纬度（2000国家大地坐标系，精度6位小数）
     */
    private BigDecimal latitude;
    /**
     * 行政区划代码
     */
    private String adminCode;
    /**
     * 15位标识码（6位行政码+3位街道码+1位图层码+5位顺序码）
     */
    private String uniqueCode;
    /**
     * 关联状态ID
     */
    private String statusId;
    /**
     * 关联检查结果ID
     */
    private String checkResultId;
    /**
     * 编码规则启用状态（布尔值）
     */
    private Boolean ruleEnableFlag;
    /**
     * 编码规则审核状态ID
     */
    private String ruleAuditStatusId;
    /**
     * 父级地理编码ID
     */
    private String parentGeoCodeId;
    /**
     * 变更日志（JSON格式）
     */
    private String changeLog;
    /**
     * 坐标校验标识（布尔值）
     */
    private Boolean coordVerifyFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;
    /**
     * 通用扩展字段5
     */
    private String extCommon5;
    /**
     * 通用扩展字段6
     */
    private String extCommon6;

}