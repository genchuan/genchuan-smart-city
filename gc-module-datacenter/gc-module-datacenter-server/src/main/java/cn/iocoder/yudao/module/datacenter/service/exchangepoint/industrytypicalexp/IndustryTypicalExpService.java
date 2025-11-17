package cn.iocoder.yudao.module.datacenter.service.exchangepoint.industrytypicalexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.industrytypicalexp.IndustryTypicalExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 行业典型经验 Service 接口
 *
 * @author 亘川智城
 */
public interface IndustryTypicalExpService {

    /**
     * 创建行业典型经验
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndustryTypicalExp(@Valid IndustryTypicalExpSaveReqVO createReqVO);

    /**
     * 更新行业典型经验
     *
     * @param updateReqVO 更新信息
     */
    void updateIndustryTypicalExp(@Valid IndustryTypicalExpSaveReqVO updateReqVO);

    /**
     * 删除行业典型经验
     *
     * @param id 编号
     */
    void deleteIndustryTypicalExp(Long id);

    /**
     * 获得行业典型经验
     *
     * @param id 编号
     * @return 行业典型经验
     */
    IndustryTypicalExpDO getIndustryTypicalExp(Long id);

    /**
     * 获得行业典型经验分页
     *
     * @param pageReqVO 分页查询
     * @return 行业典型经验分页
     */
    PageResult<IndustryTypicalExpDO> getIndustryTypicalExpPage(IndustryTypicalExpPageReqVO pageReqVO);

}