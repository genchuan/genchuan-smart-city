package cn.iocoder.yudao.module.waterdetection.service.watersupplyagreement;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersupplyagreement.WaterSupplyAgreementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 供水协议管理 Service 接口
 *
 * @author zcq
 */
public interface WaterSupplyAgreementService {

    /**
     * 创建供水协议管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterSupplyAgreement(@Valid WaterSupplyAgreementSaveReqVO createReqVO);

    /**
     * 更新供水协议管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterSupplyAgreement(@Valid WaterSupplyAgreementSaveReqVO updateReqVO);

    /**
     * 删除供水协议管理
     *
     * @param id 编号
     */
    void deleteWaterSupplyAgreement(Long id);

    /**
     * 获得供水协议管理
     *
     * @param id 编号
     * @return 供水协议管理
     */
    WaterSupplyAgreementDO getWaterSupplyAgreement(Long id);

    /**
     * 获得供水协议管理分页
     *
     * @param pageReqVO 分页查询
     * @return 供水协议管理分页
     */
    PageResult<WaterSupplyAgreementDO> getWaterSupplyAgreementPage(WaterSupplyAgreementPageReqVO pageReqVO);

}