package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtrpt.MonEvtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件统计报 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtRptService {

    /**
     * 创建监测事件统计报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtRpt(@Valid MonEvtRptSaveReqVO createReqVO);

    /**
     * 更新监测事件统计报
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtRpt(@Valid MonEvtRptSaveReqVO updateReqVO);

    /**
     * 删除监测事件统计报
     *
     * @param id 编号
     */
    void deleteMonEvtRpt(Long id);

    /**
     * 获得监测事件统计报
     *
     * @param id 编号
     * @return 监测事件统计报
     */
    MonEvtRptDO getMonEvtRpt(Long id);

    /**
     * 获得监测事件统计报分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件统计报分页
     */
    PageResult<MonEvtRptDO> getMonEvtRptPage(MonEvtRptPageReqVO pageReqVO);

}