package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtdatacfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtdatacfg.MonEvtDataCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件数据配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtDataCfgService {

    /**
     * 创建监测事件数据配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtDataCfg(@Valid MonEvtDataCfgSaveReqVO createReqVO);

    /**
     * 更新监测事件数据配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtDataCfg(@Valid MonEvtDataCfgSaveReqVO updateReqVO);

    /**
     * 删除监测事件数据配置
     *
     * @param id 编号
     */
    void deleteMonEvtDataCfg(Long id);

    /**
     * 获得监测事件数据配置
     *
     * @param id 编号
     * @return 监测事件数据配置
     */
    MonEvtDataCfgDO getMonEvtDataCfg(Long id);

    /**
     * 获得监测事件数据配置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件数据配置分页
     */
    PageResult<MonEvtDataCfgDO> getMonEvtDataCfgPage(MonEvtDataCfgPageReqVO pageReqVO);

}