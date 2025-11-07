package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingdensity;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensitySaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingdensity.GeocodingDensityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingdensity.GeocodingDensityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 数据采集密度配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingDensityServiceImpl implements GeocodingDensityService {

    @Resource
    private GeocodingDensityMapper geocodingDensityMapper;

    @Override
    public Long createGeocodingDensity(GeocodingDensitySaveReqVO createReqVO) {
        // 插入
        GeocodingDensityDO geocodingDensity = BeanUtils.toBean(createReqVO, GeocodingDensityDO.class);

        geocodingDensity.setDensityConfigId(UuidUtils.generateUUID());
        geocodingDensityMapper.insert(geocodingDensity);
        // 返回
        return geocodingDensity.getId();
    }

    @Override
    public void updateGeocodingDensity(GeocodingDensitySaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingDensityExists(updateReqVO.getId());
        // 更新
        GeocodingDensityDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingDensityDO.class);
        geocodingDensityMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingDensity(Long id) {
        // 校验存在
        validateGeocodingDensityExists(id);
        // 删除
        geocodingDensityMapper.deleteById(id);
    }

    private void validateGeocodingDensityExists(Long id) {
        if (geocodingDensityMapper.selectById(id) == null) {
            throw exception(GEOCODING_DENSITY_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingDensityDO getGeocodingDensity(Long id) {
        return geocodingDensityMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingDensityDO> getGeocodingDensityPage(GeocodingDensityPageReqVO pageReqVO) {
        return geocodingDensityMapper.selectPage(pageReqVO);
    }

}