package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopflowcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopflowcfg.CoopFlowCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 联动流程配置 Service 接口
 *
 * @author 亘川智城
 */
public interface CoopFlowCfgService {

    /**
     * 创建联动流程配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoopFlowCfg(@Valid CoopFlowCfgSaveReqVO createReqVO);

    /**
     * 更新联动流程配置
     *
     * @param updateReqVO 更新信息
     */
    void updateCoopFlowCfg(@Valid CoopFlowCfgSaveReqVO updateReqVO);

    /**
     * 删除联动流程配置
     *
     * @param id 编号
     */
    void deleteCoopFlowCfg(Long id);

    /**
     * 获得联动流程配置
     *
     * @param id 编号
     * @return 联动流程配置
     */
    CoopFlowCfgDO getCoopFlowCfg(Long id);

    /**
     * 获得联动流程配置分页
     *
     * @param pageReqVO 分页查询
     * @return 联动流程配置分页
     */
    PageResult<CoopFlowCfgDO> getCoopFlowCfgPage(CoopFlowCfgPageReqVO pageReqVO);

}