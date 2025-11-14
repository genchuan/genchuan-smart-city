package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivasset.DomDivAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivAssetService {

    /**
     * 创建资产分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivAsset(@Valid DomDivAssetSaveReqVO createReqVO);

    /**
     * 更新资产分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivAsset(@Valid DomDivAssetSaveReqVO updateReqVO);

    /**
     * 删除资产分域
     *
     * @param id 编号
     */
    void deleteDomDivAsset(Long id);

    /**
     * 获得资产分域
     *
     * @param id 编号
     * @return 资产分域
     */
    DomDivAssetDO getDomDivAsset(Long id);

    /**
     * 获得资产分域分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分域分页
     */
    PageResult<DomDivAssetDO> getDomDivAssetPage(DomDivAssetPageReqVO pageReqVO);

}