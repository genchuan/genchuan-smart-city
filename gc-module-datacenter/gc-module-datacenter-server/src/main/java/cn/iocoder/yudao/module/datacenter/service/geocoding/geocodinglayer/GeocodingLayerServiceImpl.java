package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodinglayer;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo.GeocodingLayerSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinglayer.GeocodingLayerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodinglayer.GeocodingLayerMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 图层代码配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingLayerServiceImpl implements GeocodingLayerService {

    @Resource
    private GeocodingLayerMapper geocodingLayerMapper;

    @Override
    public Long createGeocodingLayer(GeocodingLayerSaveReqVO createReqVO) {
        // 插入
        GeocodingLayerDO geocodingLayer = BeanUtils.toBean(createReqVO, GeocodingLayerDO.class);
        geocodingLayer.setLayerConfigId(UuidUtils.generateUUID());
        geocodingLayerMapper.insert(geocodingLayer);
        // 返回
        return geocodingLayer.getId();
    }

    @Override
    public void updateGeocodingLayer(GeocodingLayerSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingLayerExists(updateReqVO.getId());
        // 更新
        GeocodingLayerDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingLayerDO.class);
        geocodingLayerMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingLayer(Long id) {
        // 校验存在
        validateGeocodingLayerExists(id);
        // 删除
        geocodingLayerMapper.deleteById(id);
    }

    private void validateGeocodingLayerExists(Long id) {
        if (geocodingLayerMapper.selectById(id) == null) {
            throw exception(GEOCODING_LAYER_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingLayerDO getGeocodingLayer(Long id) {
        return geocodingLayerMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingLayerDO> getGeocodingLayerPage(GeocodingLayerPageReqVO pageReqVO) {
        return geocodingLayerMapper.selectPage(pageReqVO);
    }

}