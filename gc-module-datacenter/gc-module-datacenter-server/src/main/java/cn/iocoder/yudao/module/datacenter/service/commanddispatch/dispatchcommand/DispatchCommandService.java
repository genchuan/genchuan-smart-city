package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchcommand.DispatchCommandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 调度指令 Service 接口
 *
 * @author 亘川智城
 */
public interface DispatchCommandService {

    /**
     * 创建调度指令
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDispatchCommand(@Valid DispatchCommandSaveReqVO createReqVO);

    /**
     * 更新调度指令
     *
     * @param updateReqVO 更新信息
     */
    void updateDispatchCommand(@Valid DispatchCommandSaveReqVO updateReqVO);

    /**
     * 删除调度指令
     *
     * @param id 编号
     */
    void deleteDispatchCommand(Long id);

    /**
     * 获得调度指令
     *
     * @param id 编号
     * @return 调度指令
     */
    DispatchCommandDO getDispatchCommand(Long id);

    /**
     * 获得调度指令分页
     *
     * @param pageReqVO 分页查询
     * @return 调度指令分页
     */
    PageResult<DispatchCommandDO> getDispatchCommandPage(DispatchCommandPageReqVO pageReqVO);

}