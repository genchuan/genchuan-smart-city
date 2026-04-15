package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 附件文件表 DO
 *
 * @author 亘川智城
 */
@TableName("attach_file")
@KeySequence("attach_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAttachFileDO extends BaseDO {

    /**
     * 主键ID（UUID），同时作为文件ID
     */
    @TableId
    private String id;

    /**
     * 户外广告主键ID，关联 outdoor_ad.id
     */
    private String adId;

    /**
     * 文件访问URL
     */
    private String fileUrl;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型（如 image/jpeg、application/pdf）
     */
    private String fileType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备用字段1
     */
    private String reserve1;

    /**
     * 备用字段2
     */
    private String reserve2;
}