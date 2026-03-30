package cn.iocoder.yudao.module.waterdetection.service.samplingfrequency;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingfrequency.SamplingFrequencyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 采样频率设置 Service 接口
 *
 * @author zcq
 */
public interface SamplingFrequencyService {

    /**
     * 创建采样频率设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSamplingFrequency(@Valid SamplingFrequencySaveReqVO createReqVO);

    /**
     * 更新采样频率设置
     *
     * @param updateReqVO 更新信息
     */
    void updateSamplingFrequency(@Valid SamplingFrequencySaveReqVO updateReqVO);

    /**
     * 删除采样频率设置
     *
     * @param id 编号
     */
    void deleteSamplingFrequency(Long id);

    /**
     * 获得采样频率设置
     *
     * @param id 编号
     * @return 采样频率设置
     */
    SamplingFrequencyDO getSamplingFrequency(Long id);

    /**
     * 获得采样频率设置分页
     *
     * @param pageReqVO 分页查询
     * @return 采样频率设置分页
     */
    PageResult<SamplingFrequencyDO> getSamplingFrequencyPage(SamplingFrequencyPageReqVO pageReqVO);

}