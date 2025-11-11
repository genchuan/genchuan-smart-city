package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonevt.BasicMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测事件 Service 接口
 *
 * @author zcq
 */
public interface BasicMonEvtService {

    /**
     * 创建基础监测事件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonEvt(@Valid BasicMonEvtSaveReqVO createReqVO);

    /**
     * 更新基础监测事件
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonEvt(@Valid BasicMonEvtSaveReqVO updateReqVO);

    /**
     * 删除基础监测事件
     *
     * @param id 编号
     */
    void deleteBasicMonEvt(Long id);

    /**
     * 获得基础监测事件
     *
     * @param id 编号
     * @return 基础监测事件
     */
    BasicMonEvtDO getBasicMonEvt(Long id);

    /**
     * 获得基础监测事件分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测事件分页
     */
    PageResult<BasicMonEvtDO> getBasicMonEvtPage(BasicMonEvtPageReqVO pageReqVO);

}