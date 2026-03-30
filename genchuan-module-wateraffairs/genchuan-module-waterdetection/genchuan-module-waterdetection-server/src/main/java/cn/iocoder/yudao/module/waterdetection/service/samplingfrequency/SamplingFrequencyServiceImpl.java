package cn.iocoder.yudao.module.waterdetection.service.samplingfrequency;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingfrequency.SamplingFrequencyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingfrequency.SamplingFrequencyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 采样频率设置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class SamplingFrequencyServiceImpl implements SamplingFrequencyService {

    @Resource
    private SamplingFrequencyMapper samplingFrequencyMapper;

    @Override
    public Long createSamplingFrequency(SamplingFrequencySaveReqVO createReqVO) {
        // 插入
        SamplingFrequencyDO samplingFrequency = BeanUtils.toBean(createReqVO, SamplingFrequencyDO.class);
        samplingFrequencyMapper.insert(samplingFrequency);
        // 返回
        return samplingFrequency.getId();
    }

    @Override
    public void updateSamplingFrequency(SamplingFrequencySaveReqVO updateReqVO) {
        // 校验存在
        validateSamplingFrequencyExists(updateReqVO.getId());
        // 更新
        SamplingFrequencyDO updateObj = BeanUtils.toBean(updateReqVO, SamplingFrequencyDO.class);
        samplingFrequencyMapper.updateById(updateObj);
    }

    @Override
    public void deleteSamplingFrequency(Long id) {
        // 校验存在
        validateSamplingFrequencyExists(id);
        // 删除
        samplingFrequencyMapper.deleteById(id);
    }

    private void validateSamplingFrequencyExists(Long id) {
        if (samplingFrequencyMapper.selectById(id) == null) {
            throw exception(SAMPLING_FREQUENCY_NOT_EXISTS);
        }
    }

    @Override
    public SamplingFrequencyDO getSamplingFrequency(Long id) {
        return samplingFrequencyMapper.selectById(id);
    }

    @Override
    public PageResult<SamplingFrequencyDO> getSamplingFrequencyPage(SamplingFrequencyPageReqVO pageReqVO) {
        return samplingFrequencyMapper.selectPage(pageReqVO);
    }

}