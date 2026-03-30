package cn.iocoder.yudao.module.waterdetection.service.watersampleinfo;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleInfoDO;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampleinfo.WaterSampleInfoMapper;
import cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampleinfo.WaterSampleResultMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 水质检测信息 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class WaterSampleInfoServiceImpl implements WaterSampleInfoService {

    @Resource
    private WaterSampleInfoMapper waterSampleInfoMapper;
    @Resource
    private WaterSampleResultMapper waterSampleResultMapper;

    @Override
    public Long createWaterSampleInfo(WaterSampleInfoSaveReqVO createReqVO) {
        // 插入
        WaterSampleInfoDO waterSampleInfo = BeanUtils.toBean(createReqVO, WaterSampleInfoDO.class);
        waterSampleInfoMapper.insert(waterSampleInfo);
        // 返回
        return waterSampleInfo.getId();
    }

    @Override
    public void updateWaterSampleInfo(WaterSampleInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterSampleInfoExists(updateReqVO.getId());
        // 更新
        WaterSampleInfoDO updateObj = BeanUtils.toBean(updateReqVO, WaterSampleInfoDO.class);
        waterSampleInfoMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWaterSampleInfo(Long id) {
        // 校验存在
        validateWaterSampleInfoExists(id);
        // 删除
        waterSampleInfoMapper.deleteById(id);

        // 删除子表
        deleteWaterSampleResultByWaterSampleId(id);
    }

    private void validateWaterSampleInfoExists(Long id) {
        if (waterSampleInfoMapper.selectById(id) == null) {
            throw exception(WATER_SAMPLE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public WaterSampleInfoDO getWaterSampleInfo(Long id) {
        return waterSampleInfoMapper.selectById(id);
    }

    @Override
    public PageResult<WaterSampleInfoDO> getWaterSampleInfoPage(WaterSampleInfoPageReqVO pageReqVO) {
        return waterSampleInfoMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（出厂水检测结果） ====================

    @Override
    public PageResult<WaterSampleResultDO> getWaterSampleResultPage(PageParam pageReqVO, Long waterSampleId) {
        return waterSampleResultMapper.selectPage(pageReqVO, waterSampleId);
    }

    @Override
    public Long createWaterSampleResult(WaterSampleResultDO waterSampleResult) {
        waterSampleResultMapper.insert(waterSampleResult);
        return waterSampleResult.getId();
    }

    @Override
    public void updateWaterSampleResult(WaterSampleResultDO waterSampleResult) {
        // 校验存在
        validateWaterSampleResultExists(waterSampleResult.getId());
        // 更新
        waterSampleResult.setUpdater(null).setUpdateTime(null); // 解决更新情况下：updateTime 不更新
        waterSampleResultMapper.updateById(waterSampleResult);
    }

    @Override
    public void deleteWaterSampleResult(Long id) {
        // 校验存在
        validateWaterSampleResultExists(id);
        // 删除
        waterSampleResultMapper.deleteById(id);
    }

    @Override
    public WaterSampleResultDO getWaterSampleResult(Long id) {
        return waterSampleResultMapper.selectById(id);
    }

    private void validateWaterSampleResultExists(Long id) {
        if (waterSampleResultMapper.selectById(id) == null) {
            throw exception(WATER_SAMPLE_RESULT_NOT_EXISTS);
        }
    }

    private void deleteWaterSampleResultByWaterSampleId(Long waterSampleId) {
        waterSampleResultMapper.deleteByWaterSampleId(waterSampleId);
    }

}