package cn.iocoder.yudao.module.datacenter.service.policylegislation.localregulation;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.localregulation.LocalRegulationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 地方法规信息 Service 接口
 *
 * @author 亘川智城
 */
public interface LocalRegulationService {

    /**
     * 创建地方法规信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLocalRegulation(@Valid LocalRegulationSaveReqVO createReqVO);

    /**
     * 更新地方法规信息
     *
     * @param updateReqVO 更新信息
     */
    void updateLocalRegulation(@Valid LocalRegulationSaveReqVO updateReqVO);

    /**
     * 删除地方法规信息
     *
     * @param id 编号
     */
    void deleteLocalRegulation(Long id);

    /**
     * 获得地方法规信息
     *
     * @param id 编号
     * @return 地方法规信息
     */
    LocalRegulationDO getLocalRegulation(Long id);

    /**
     * 获得地方法规信息分页
     *
     * @param pageReqVO 分页查询
     * @return 地方法规信息分页
     */
    PageResult<LocalRegulationDO> getLocalRegulationPage(LocalRegulationPageReqVO pageReqVO);

}