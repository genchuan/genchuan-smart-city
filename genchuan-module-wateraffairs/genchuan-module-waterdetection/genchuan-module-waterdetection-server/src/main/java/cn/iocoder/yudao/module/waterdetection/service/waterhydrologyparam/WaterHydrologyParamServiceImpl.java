package cn.iocoder.yudao.module.waterdetection.service.waterhydrologyparam;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterhydrologyparam.WaterHydrologyParamDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.waterhydrologyparam.WaterHydrologyParamMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 水源水文参数管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterHydrologyParamServiceImpl implements WaterHydrologyParamService {

    @Resource
    private WaterHydrologyParamMapper waterHydrologyParamMapper;

    @Override
    public Long createWaterHydrologyParam(WaterHydrologyParamSaveReqVO createReqVO) {
        // 插入
        WaterHydrologyParamDO waterHydrologyParam = BeanUtils.toBean(createReqVO, WaterHydrologyParamDO.class);
        waterHydrologyParamMapper.insert(waterHydrologyParam);
        // 返回
        return waterHydrologyParam.getId();
    }

    @Override
    public void updateWaterHydrologyParam(WaterHydrologyParamSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterHydrologyParamExists(updateReqVO.getId());
        // 更新
        WaterHydrologyParamDO updateObj = BeanUtils.toBean(updateReqVO, WaterHydrologyParamDO.class);
        waterHydrologyParamMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterHydrologyParam(Long id) {
        // 校验存在
        validateWaterHydrologyParamExists(id);
        // 删除
        waterHydrologyParamMapper.deleteById(id);
    }

    private void validateWaterHydrologyParamExists(Long id) {
        if (waterHydrologyParamMapper.selectById(id) == null) {
            throw exception(WATER_HYDROLOGY_PARAM_NOT_EXISTS);
        }
    }

    @Override
    public WaterHydrologyParamDO getWaterHydrologyParam(Long id) {
        return waterHydrologyParamMapper.selectById(id);
    }

    @Override
    public PageResult<WaterHydrologyParamDO> getWaterHydrologyParamPage(WaterHydrologyParamPageReqVO pageReqVO) {
        return waterHydrologyParamMapper.selectPage(pageReqVO);
    }

}