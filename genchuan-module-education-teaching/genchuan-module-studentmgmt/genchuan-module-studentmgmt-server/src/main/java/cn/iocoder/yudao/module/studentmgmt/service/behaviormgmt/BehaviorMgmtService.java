package cn.iocoder.yudao.module.studentmgmt.service.behaviormgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 行为管理 Service 接口
 *
 * @author 芋道源码
 */
public interface BehaviorMgmtService {

    /**
     * 创建行为管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBehaviorMgmt(@Valid BehaviorMgmtSaveReqVO createReqVO);

    /**
     * 更新行为管理
     *
     * @param updateReqVO 更新信息
     */
    void updateBehaviorMgmt(@Valid BehaviorMgmtSaveReqVO updateReqVO);

    /**
     * 删除行为管理
     *
     * @param id 编号
     */
    void deleteBehaviorMgmt(Long id);

    /**
    * 批量删除行为管理
    *
    * @param ids 编号
    */
    void deleteBehaviorMgmtListByIds(List<Long> ids);

    /**
     * 获得行为管理
     *
     * @param id 编号
     * @return 行为管理
     */
    BehaviorMgmtDO getBehaviorMgmt(Long id);

    /**
     * 获得行为管理分页
     *
     * @param pageReqVO 分页查询
     * @return 行为管理分页
     */
    PageResult<BehaviorMgmtDO> getBehaviorMgmtPage(BehaviorMgmtPageReqVO pageReqVO);

}