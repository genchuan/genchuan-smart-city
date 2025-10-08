package cn.iocoder.yudao.module.datacenter.service.geocodingaccuracy;

import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingaccuracy.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingaccuracy.GeocodingAccuracyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocodingaccuracy.GeocodingAccuracyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 位置精度标准配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingAccuracyServiceImpl implements GeocodingAccuracyService {

    @Resource
    private GeocodingAccuracyMapper geocodingAccuracyMapper;

    @Override
    public Long createGeocodingAccuracy(GeocodingAccuracySaveReqVO createReqVO) {
        // 插入
        GeocodingAccuracyDO geocodingAccuracy = BeanUtils.toBean(createReqVO, GeocodingAccuracyDO.class);
        geocodingAccuracy.setAccuracyConfigId(UuidUtils.generateUUID());
        geocodingAccuracyMapper.insert(geocodingAccuracy);
        // 返回
        return geocodingAccuracy.getId();
    }

    @Override
    public void updateGeocodingAccuracy(GeocodingAccuracySaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingAccuracyExists(updateReqVO.getId());
        // 更新
        GeocodingAccuracyDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingAccuracyDO.class);
        geocodingAccuracyMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingAccuracy(Long id) {
        // 校验存在
        validateGeocodingAccuracyExists(id);
        // 删除
        geocodingAccuracyMapper.deleteById(id);
    }

    private void validateGeocodingAccuracyExists(Long id) {
        if (geocodingAccuracyMapper.selectById(id) == null) {
            throw exception(GEOCODING_ACCURACY_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingAccuracyDO getGeocodingAccuracy(Long id) {
        return geocodingAccuracyMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingAccuracyDO> getGeocodingAccuracyPage(GeocodingAccuracyPageReqVO pageReqVO) {
        return geocodingAccuracyMapper.selectPage(pageReqVO);
    }

}