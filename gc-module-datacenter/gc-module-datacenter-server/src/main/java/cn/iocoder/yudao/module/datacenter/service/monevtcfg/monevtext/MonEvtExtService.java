package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtext;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtext.MonEvtExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 扩展监测事件配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtExtService {

    /**
     * 创建扩展监测事件配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtExt(@Valid MonEvtExtSaveReqVO createReqVO);

    /**
     * 更新扩展监测事件配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtExt(@Valid MonEvtExtSaveReqVO updateReqVO);

    /**
     * 删除扩展监测事件配置
     *
     * @param id 编号
     */
    void deleteMonEvtExt(Long id);

    /**
     * 获得扩展监测事件配置
     *
     * @param id 编号
     * @return 扩展监测事件配置
     */
    MonEvtExtDO getMonEvtExt(Long id);

    /**
     * 获得扩展监测事件配置分页
     *
     * @param pageReqVO 分页查询
     * @return 扩展监测事件配置分页
     */
    PageResult<MonEvtExtDO> getMonEvtExtPage(MonEvtExtPageReqVO pageReqVO);

}