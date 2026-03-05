package cn.iocoder.yudao.module.evaluate.service.status;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 状态字典 Service 接口
 *
 * @author 亘川智城
 */
public interface StatusService {

    /**
     * 创建状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStatus(@Valid StatusSaveReqVO createReqVO);

    /**
     * 更新状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updateStatus(@Valid StatusSaveReqVO updateReqVO);

    /**
     * 删除状态字典
     *
     * @param id 编号
     */
    void deleteStatus(Long id);

    /**
     * 获得状态字典
     *
     * @param id 编号
     * @return 状态字典
     */
    StatusDO getStatus(Long id);

    /**
     * 获得状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 状态字典分页
     */
    PageResult<StatusDO> getStatusPage(StatusPageReqVO pageReqVO);

}