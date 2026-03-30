package cn.iocoder.yudao.module.waterdetection.service.samplingassignment;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingassignment.SamplingAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 采样人员分配 Service 接口
 *
 * @author zcq
 */
public interface SamplingAssignmentService {

    /**
     * 创建采样人员分配
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSamplingAssignment(@Valid SamplingAssignmentSaveReqVO createReqVO);

    /**
     * 更新采样人员分配
     *
     * @param updateReqVO 更新信息
     */
    void updateSamplingAssignment(@Valid SamplingAssignmentSaveReqVO updateReqVO);

    /**
     * 删除采样人员分配
     *
     * @param id 编号
     */
    void deleteSamplingAssignment(Long id);

    /**
     * 获得采样人员分配
     *
     * @param id 编号
     * @return 采样人员分配
     */
    SamplingAssignmentDO getSamplingAssignment(Long id);

    /**
     * 获得采样人员分配分页
     *
     * @param pageReqVO 分页查询
     * @return 采样人员分配分页
     */
    PageResult<SamplingAssignmentDO> getSamplingAssignmentPage(SamplingAssignmentPageReqVO pageReqVO);

}