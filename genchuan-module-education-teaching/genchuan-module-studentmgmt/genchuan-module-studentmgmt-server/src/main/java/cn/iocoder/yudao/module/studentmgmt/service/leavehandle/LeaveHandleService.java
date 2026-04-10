package cn.iocoder.yudao.module.studentmgmt.service.leavehandle;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 离校办理 Service 接口
 *
 * @author 芋道源码
 */
public interface LeaveHandleService {

    /**
     * 创建离校办理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLeaveHandle(@Valid LeaveHandleSaveReqVO createReqVO);

    /**
     * 更新离校办理
     *
     * @param updateReqVO 更新信息
     */
    void updateLeaveHandle(@Valid LeaveHandleSaveReqVO updateReqVO);

    /**
     * 删除离校办理
     *
     * @param id 编号
     */
    void deleteLeaveHandle(Long id);

    /**
    * 批量删除离校办理
    *
    * @param ids 编号
    */
    void deleteLeaveHandleListByIds(List<Long> ids);

    /**
     * 获得离校办理
     *
     * @param id 编号
     * @return 离校办理
     */
    LeaveHandleDO getLeaveHandle(Long id);

    /**
     * 获得离校办理分页
     *
     * @param pageReqVO 分页查询
     * @return 离校办理分页
     */
    PageResult<LeaveHandleDO> getLeaveHandlePage(LeaveHandlePageReqVO pageReqVO);

}