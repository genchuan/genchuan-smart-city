package cn.iocoder.yudao.module.waterdetection.service.samplingpoint;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingpoint.SamplingPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 采样点规划 Service 接口
 *
 * @author zcq
 */
public interface SamplingPointService {

    /**
     * 创建采样点规划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSamplingPoint(@Valid SamplingPointSaveReqVO createReqVO);

    /**
     * 更新采样点规划
     *
     * @param updateReqVO 更新信息
     */
    void updateSamplingPoint(@Valid SamplingPointSaveReqVO updateReqVO);

    /**
     * 删除采样点规划
     *
     * @param id 编号
     */
    void deleteSamplingPoint(Long id);

    /**
     * 获得采样点规划
     *
     * @param id 编号
     * @return 采样点规划
     */
    SamplingPointDO getSamplingPoint(Long id);

    /**
     * 获得采样点规划分页
     *
     * @param pageReqVO 分页查询
     * @return 采样点规划分页
     */
    PageResult<SamplingPointDO> getSamplingPointPage(SamplingPointPageReqVO pageReqVO);

}