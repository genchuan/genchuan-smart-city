package cn.iocoder.yudao.module.datacenter.service.policylegislation.industrystd;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.industrystd.IndustryStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 行业规范信息 Service 接口
 *
 * @author 亘川智城
 */
public interface IndustryStdService {

    /**
     * 创建行业规范信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndustryStd(@Valid IndustryStdSaveReqVO createReqVO);

    /**
     * 更新行业规范信息
     *
     * @param updateReqVO 更新信息
     */
    void updateIndustryStd(@Valid IndustryStdSaveReqVO updateReqVO);

    /**
     * 删除行业规范信息
     *
     * @param id 编号
     */
    void deleteIndustryStd(Long id);

    /**
     * 获得行业规范信息
     *
     * @param id 编号
     * @return 行业规范信息
     */
    IndustryStdDO getIndustryStd(Long id);

    /**
     * 获得行业规范信息分页
     *
     * @param pageReqVO 分页查询
     * @return 行业规范信息分页
     */
    PageResult<IndustryStdDO> getIndustryStdPage(IndustryStdPageReqVO pageReqVO);

}