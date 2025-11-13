package cn.iocoder.yudao.module.datacenter.service.datavisualization.customrptcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.customrptcfg.CustomRptCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 自定义报表配置 Service 接口
 *
 * @author 亘川智城
 */
public interface CustomRptCfgService {

    /**
     * 创建自定义报表配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomRptCfg(@Valid CustomRptCfgSaveReqVO createReqVO);

    /**
     * 更新自定义报表配置
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomRptCfg(@Valid CustomRptCfgSaveReqVO updateReqVO);

    /**
     * 删除自定义报表配置
     *
     * @param id 编号
     */
    void deleteCustomRptCfg(Long id);

    /**
     * 获得自定义报表配置
     *
     * @param id 编号
     * @return 自定义报表配置
     */
    CustomRptCfgDO getCustomRptCfg(Long id);

    /**
     * 获得自定义报表配置分页
     *
     * @param pageReqVO 分页查询
     * @return 自定义报表配置分页
     */
    PageResult<CustomRptCfgDO> getCustomRptCfgPage(CustomRptCfgPageReqVO pageReqVO);

}