package cn.iocoder.yudao.module.waterdetection.service.positionresponsibility;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.positionresponsibility.PositionResponsibilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 岗位职责划分管理 Service 接口
 *
 * @author zcq
 */
public interface PositionResponsibilityService {

    /**
     * 创建岗位职责划分管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPositionResponsibility(@Valid PositionResponsibilitySaveReqVO createReqVO);

    /**
     * 更新岗位职责划分管理
     *
     * @param updateReqVO 更新信息
     */
    void updatePositionResponsibility(@Valid PositionResponsibilitySaveReqVO updateReqVO);

    /**
     * 删除岗位职责划分管理
     *
     * @param id 编号
     */
    void deletePositionResponsibility(Long id);

    /**
     * 获得岗位职责划分管理
     *
     * @param id 编号
     * @return 岗位职责划分管理
     */
    PositionResponsibilityDO getPositionResponsibility(Long id);

    /**
     * 获得岗位职责划分管理分页
     *
     * @param pageReqVO 分页查询
     * @return 岗位职责划分管理分页
     */
    PageResult<PositionResponsibilityDO> getPositionResponsibilityPage(PositionResponsibilityPageReqVO pageReqVO);

}