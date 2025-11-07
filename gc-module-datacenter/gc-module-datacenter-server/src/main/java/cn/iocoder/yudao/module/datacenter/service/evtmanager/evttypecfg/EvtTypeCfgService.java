package cn.iocoder.yudao.module.datacenter.service.evtmanager.evttypecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evttypecfg.EvtTypeCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件类型配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtTypeCfgService {

    /**
     * 创建事件类型配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtTypeCfg(@Valid EvtTypeCfgSaveReqVO createReqVO);

    /**
     * 更新事件类型配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtTypeCfg(@Valid EvtTypeCfgSaveReqVO updateReqVO);

    /**
     * 删除事件类型配置
     *
     * @param id 编号
     */
    void deleteEvtTypeCfg(Long id);

    /**
     * 获得事件类型配置
     *
     * @param id 编号
     * @return 事件类型配置
     */
    EvtTypeCfgDO getEvtTypeCfg(Long id);

    /**
     * 获得事件类型配置分页
     *
     * @param pageReqVO 分页查询
     * @return 事件类型配置分页
     */
    PageResult<EvtTypeCfgDO> getEvtTypeCfgPage(EvtTypeCfgPageReqVO pageReqVO);

}