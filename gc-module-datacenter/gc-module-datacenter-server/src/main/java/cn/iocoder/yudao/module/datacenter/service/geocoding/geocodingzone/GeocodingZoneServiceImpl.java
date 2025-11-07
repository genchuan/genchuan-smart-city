package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingzone;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZonePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZoneSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingzone.GeocodingZoneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingzone.GeocodingZoneMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 地片与区片数据管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingZoneServiceImpl implements GeocodingZoneService {

    @Resource
    private GeocodingZoneMapper geocodingZoneMapper;

    @Override
    public Long createGeocodingZone(GeocodingZoneSaveReqVO createReqVO) {
        // 插入
        GeocodingZoneDO geocodingZone = BeanUtils.toBean(createReqVO, GeocodingZoneDO.class);
        geocodingZone.setZoneId(UuidUtils.generateUUID());
        geocodingZoneMapper.insert(geocodingZone);
        // 返回
        return geocodingZone.getId();
    }

    @Override
    public void updateGeocodingZone(GeocodingZoneSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingZoneExists(updateReqVO.getId());
        // 更新
        GeocodingZoneDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingZoneDO.class);
        geocodingZoneMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingZone(Long id) {
        // 校验存在
        validateGeocodingZoneExists(id);
        // 删除
        geocodingZoneMapper.deleteById(id);
    }

    private void validateGeocodingZoneExists(Long id) {
        if (geocodingZoneMapper.selectById(id) == null) {
            throw exception(GEOCODING_ZONE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingZoneDO getGeocodingZone(Long id) {
        return geocodingZoneMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingZoneDO> getGeocodingZonePage(GeocodingZonePageReqVO pageReqVO) {
        return geocodingZoneMapper.selectPage(pageReqVO);
    }

}