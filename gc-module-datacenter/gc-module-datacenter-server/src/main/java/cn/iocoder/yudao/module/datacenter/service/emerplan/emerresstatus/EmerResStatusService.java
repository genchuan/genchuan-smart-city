package cn.iocoder.yudao.module.datacenter.service.emerplan.emerresstatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerresstatus.EmerResStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资源状态统计 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerResStatusService {

    /**
     * 创建资源状态统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerResStatus(@Valid EmerResStatusSaveReqVO createReqVO);

    /**
     * 更新资源状态统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerResStatus(@Valid EmerResStatusSaveReqVO updateReqVO);

    /**
     * 删除资源状态统计
     *
     * @param id 编号
     */
    void deleteEmerResStatus(Long id);

    /**
     * 获得资源状态统计
     *
     * @param id 编号
     * @return 资源状态统计
     */
    EmerResStatusDO getEmerResStatus(Long id);

    /**
     * 获得资源状态统计分页
     *
     * @param pageReqVO 分页查询
     * @return 资源状态统计分页
     */
    PageResult<EmerResStatusDO> getEmerResStatusPage(EmerResStatusPageReqVO pageReqVO);

}