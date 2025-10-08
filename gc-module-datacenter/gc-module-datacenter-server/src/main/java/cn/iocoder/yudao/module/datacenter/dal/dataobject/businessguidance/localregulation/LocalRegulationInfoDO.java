package cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.localregulation;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 地方法规信息 DO
 *
 * @author Gyh
 */
@TableName("gc_local_regulation_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalRegulationInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 法规ID，唯一编码，采用UUID
     */
    private String regulationId;

    /**
     * 法规编号，如"XX省人民政府令〔2025〕XX号"
     */
    private String regulationNo;

    /**
     * 法规完整名称
     */
    private String regulationName;

    /**
     * 法规类型，政府规章/地方性法规/规范性文件
     */
    private String regulationType;

    /**
     * 所属行政区划代码，符合GB/T 2260
     */
    private String adminCode;

    /**
     * 所属行政区划名称
     */
    private String adminName;

    /**
     * 发布单位，如"XX省人民政府""XX市人大常委会"
     */
    private String issueDept;

    /**
     * 发布时间
     */
    private LocalDateTime issueTime;

    /**
     * 生效时间
     */
    private LocalDate effectiveTime;

    /**
     * 失效时间，永久有效则为空
     */
    private LocalDate expireTime;

    /**
     * 修订依据，如"根据《XX国家政策》修订"
     */
    private String revisionBasis;

    /**
     * 关联依据的国家政策ID
     */
    private String relatedNationalPolicyId;

    /**
     * PDF格式原文存储路径
     */
    private String regulationFilePath;

    /**
     * 法规核心内容摘要
     */
    private String regulationAbstract;

    /**
     * 法规状态，未生效/有效/已修订/已废止
     */
    private String regulationStatus;

    /**
     * 上传管理员账号
     */
    private String uploadUserId;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 修改管理员账号
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 扩展分类字段，预留用于自定义标签
     */
    private String extendCategory;
}