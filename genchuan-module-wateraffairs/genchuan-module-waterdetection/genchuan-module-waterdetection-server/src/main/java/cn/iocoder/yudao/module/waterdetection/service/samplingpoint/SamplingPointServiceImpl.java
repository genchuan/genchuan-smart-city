package cn.iocoder.yudao.module.waterdetection.service.samplingpoint;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingpoint.SamplingPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingpoint.SamplingPointMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 采样点规划 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class SamplingPointServiceImpl implements SamplingPointService {

    @Resource
    private SamplingPointMapper samplingPointMapper;

    @Override
    public Long createSamplingPoint(SamplingPointSaveReqVO createReqVO) {
        // 插入
        SamplingPointDO samplingPoint = BeanUtils.toBean(createReqVO, SamplingPointDO.class);
        samplingPointMapper.insert(samplingPoint);
        // 返回
        return samplingPoint.getId();
    }

    @Override
    public void updateSamplingPoint(SamplingPointSaveReqVO updateReqVO) {
        // 校验存在
        validateSamplingPointExists(updateReqVO.getId());
        // 更新
        SamplingPointDO updateObj = BeanUtils.toBean(updateReqVO, SamplingPointDO.class);
        samplingPointMapper.updateById(updateObj);
    }

    @Override
    public void deleteSamplingPoint(Long id) {
        // 校验存在
        validateSamplingPointExists(id);
        // 删除
        samplingPointMapper.deleteById(id);
    }

    private void validateSamplingPointExists(Long id) {
        if (samplingPointMapper.selectById(id) == null) {
            throw exception(SAMPLING_POINT_NOT_EXISTS);
        }
    }

    @Override
    public SamplingPointDO getSamplingPoint(Long id) {
        return samplingPointMapper.selectById(id);
    }

    @Override
    public PageResult<SamplingPointDO> getSamplingPointPage(SamplingPointPageReqVO pageReqVO) {
        return samplingPointMapper.selectPage(pageReqVO);
    }

}