package cn.iocoder.yudao.module.datacenter.service.operationguide.systemfunctionguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo.SystemFunctionGuideSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.systemfunctionguide.SystemFunctionGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 系统功能指南 Service 接口
 *
 * @author 亘川智城
 */
public interface SystemFunctionGuideService {

    /**
     * 创建系统功能指南
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSystemFunctionGuide(@Valid SystemFunctionGuideSaveReqVO createReqVO);

    /**
     * 更新系统功能指南
     *
     * @param updateReqVO 更新信息
     */
    void updateSystemFunctionGuide(@Valid SystemFunctionGuideSaveReqVO updateReqVO);

    /**
     * 删除系统功能指南
     *
     * @param id 编号
     */
    void deleteSystemFunctionGuide(Long id);

    /**
     * 获得系统功能指南
     *
     * @param id 编号
     * @return 系统功能指南
     */
    SystemFunctionGuideDO getSystemFunctionGuide(Long id);

    /**
     * 获得系统功能指南分页
     *
     * @param pageReqVO 分页查询
     * @return 系统功能指南分页
     */
    PageResult<SystemFunctionGuideDO> getSystemFunctionGuidePage(SystemFunctionGuidePageReqVO pageReqVO);

}