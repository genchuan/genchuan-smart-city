package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公厕详情DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PublicToiletDetailDO extends PublicToiletDO {

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 运营状态名称
     */
    private String operationStatusName;

    /**
     * 负责人名称
     */
    private String managerName;
}