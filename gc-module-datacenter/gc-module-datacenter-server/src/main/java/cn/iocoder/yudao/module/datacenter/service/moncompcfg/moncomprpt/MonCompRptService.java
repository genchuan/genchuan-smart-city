package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncomprpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncomprpt.MonCompRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件统计报 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompRptService {

    /**
     * 创建监测部件统计报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompRpt(@Valid MonCompRptSaveReqVO createReqVO);

    /**
     * 更新监测部件统计报
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompRpt(@Valid MonCompRptSaveReqVO updateReqVO);

    /**
     * 删除监测部件统计报
     *
     * @param id 编号
     */
    void deleteMonCompRpt(Long id);

    /**
     * 获得监测部件统计报
     *
     * @param id 编号
     * @return 监测部件统计报
     */
    MonCompRptDO getMonCompRpt(Long id);

    /**
     * 获得监测部件统计报分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件统计报分页
     */
    PageResult<MonCompRptDO> getMonCompRptPage(MonCompRptPageReqVO pageReqVO);

}