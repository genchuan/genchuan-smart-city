package cn.iocoder.yudao.module.envirhealth.service.river.monitortype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorTypeDO;
import jakarta.validation.Valid;

/**
 * 监测类型字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface MonitorTypeService {

    /**
     * 创建监测类型字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitorType(@Valid MonitorTypeSaveReqVO createReqVO);

    /**
     * 更新监测类型字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitorType(@Valid MonitorTypeSaveReqVO updateReqVO);

    /**
     * 删除监测类型字典表
     *
     * @param id 编号
     */
    void deleteMonitorType(Long id);

    /**
     * 获得监测类型字典表
     *
     * @param id 编号
     * @return 监测类型字典表
     */
    MonitorTypeDO getMonitorType(Long id);

    /**
     * 获得监测类型字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 监测类型字典表分页
     */
    PageResult<MonitorTypeDO> getMonitorTypePage(MonitorTypePageReqVO pageReqVO);

}