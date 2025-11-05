package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingquality;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualitySaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingquality.GeocodingQualityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingquality.GeocodingQualityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 地理编码数据质量统计报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingQualityServiceImpl implements GeocodingQualityService {

    @Resource
    private GeocodingQualityMapper geocodingQualityMapper;

    @Override
    public Long createGeocodingQuality(GeocodingQualitySaveReqVO createReqVO) {
        // 插入
        GeocodingQualityDO geocodingQuality = BeanUtils.toBean(createReqVO, GeocodingQualityDO.class);
        geocodingQuality.setQualityStatId(UuidUtils.generateUUID());
        geocodingQualityMapper.insert(geocodingQuality);
        // 返回
        return geocodingQuality.getId();
    }

    @Override
    public void updateGeocodingQuality(GeocodingQualitySaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingQualityExists(updateReqVO.getId());
        // 更新
        GeocodingQualityDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingQualityDO.class);
        geocodingQualityMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingQuality(Long id) {
        // 校验存在
        validateGeocodingQualityExists(id);
        // 删除
        geocodingQualityMapper.deleteById(id);
    }

    private void validateGeocodingQualityExists(Long id) {
        if (geocodingQualityMapper.selectById(id) == null) {
            throw exception(GEOCODING_QUALITY_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingQualityDO getGeocodingQuality(Long id) {
        return geocodingQualityMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingQualityDO> getGeocodingQualityPage(GeocodingQualityPageReqVO pageReqVO) {
        return geocodingQualityMapper.selectPage(pageReqVO);
    }

}