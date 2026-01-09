package cn.iocoder.yudao.module.smartcity.service.keyworktasks;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo.KeyWorkTasksPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo.KeyWorkTasksSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.keyworktasks.KeyWorkTasksDO;
import jakarta.validation.Valid;

/**
 * 重点工作任务 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface KeyWorkTasksService {

    /**
     * 创建重点工作任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createKeyWorkTasks(@Valid KeyWorkTasksSaveReqVO createReqVO);

    /**
     * 更新重点工作任务
     *
     * @param updateReqVO 更新信息
     */
    void updateKeyWorkTasks(@Valid KeyWorkTasksSaveReqVO updateReqVO);

    /**
     * 删除重点工作任务
     *
     * @param id 编号
     */
    void deleteKeyWorkTasks(Long id);

    /**
     * 获得重点工作任务
     *
     * @param id 编号
     * @return 重点工作任务
     */
    KeyWorkTasksDO getKeyWorkTasks(Long id);

    /**
     * 获得重点工作任务分页
     *
     * @param pageReqVO 分页查询
     * @return 重点工作任务分页
     */
    PageResult<KeyWorkTasksDO> getKeyWorkTasksPage(KeyWorkTasksPageReqVO pageReqVO);

}