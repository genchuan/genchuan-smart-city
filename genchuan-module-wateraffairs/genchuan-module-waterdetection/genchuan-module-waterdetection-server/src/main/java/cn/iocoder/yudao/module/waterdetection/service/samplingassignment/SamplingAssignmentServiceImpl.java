package cn.iocoder.yudao.module.waterdetection.service.samplingassignment;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingassignment.SamplingAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingassignment.SamplingAssignmentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 采样人员分配 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class SamplingAssignmentServiceImpl implements SamplingAssignmentService {

    @Resource
    private SamplingAssignmentMapper samplingAssignmentMapper;

    @Override
    public Long createSamplingAssignment(SamplingAssignmentSaveReqVO createReqVO) {
        // 插入
        SamplingAssignmentDO samplingAssignment = BeanUtils.toBean(createReqVO, SamplingAssignmentDO.class);
        samplingAssignmentMapper.insert(samplingAssignment);
        // 返回
        return samplingAssignment.getId();
    }

    @Override
    public void updateSamplingAssignment(SamplingAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateSamplingAssignmentExists(updateReqVO.getId());
        // 更新
        SamplingAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, SamplingAssignmentDO.class);
        samplingAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteSamplingAssignment(Long id) {
        // 校验存在
        validateSamplingAssignmentExists(id);
        // 删除
        samplingAssignmentMapper.deleteById(id);
    }

    private void validateSamplingAssignmentExists(Long id) {
        if (samplingAssignmentMapper.selectById(id) == null) {
            throw exception(SAMPLING_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public SamplingAssignmentDO getSamplingAssignment(Long id) {
        return samplingAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<SamplingAssignmentDO> getSamplingAssignmentPage(SamplingAssignmentPageReqVO pageReqVO) {
        return samplingAssignmentMapper.selectPage(pageReqVO);
    }

}