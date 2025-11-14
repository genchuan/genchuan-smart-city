package cn.iocoder.yudao.module.datacenter.service.typicalscenariocase;

import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.typicalscenariocase.TypicalScenarioCaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 典型场景案例库 Service 接口
 *
 * @author 亘川智城
 */
public interface TypicalScenarioCaseService {

    /**
     * 创建典型场景案例库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTypicalScenarioCase(@Valid TypicalScenarioCaseSaveReqVO createReqVO);

    /**
     * 更新典型场景案例库
     *
     * @param updateReqVO 更新信息
     */
    void updateTypicalScenarioCase(@Valid TypicalScenarioCaseSaveReqVO updateReqVO);

    /**
     * 删除典型场景案例库
     *
     * @param id 编号
     */
    void deleteTypicalScenarioCase(Long id);

    /**
     * 获得典型场景案例库
     *
     * @param id 编号
     * @return 典型场景案例库
     */
    TypicalScenarioCaseDO getTypicalScenarioCase(Long id);

    /**
     * 获得典型场景案例库分页
     *
     * @param pageReqVO 分页查询
     * @return 典型场景案例库分页
     */
    PageResult<TypicalScenarioCaseDO> getTypicalScenarioCasePage(TypicalScenarioCasePageReqVO pageReqVO);

}