package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingregion.vo.GeocodingRegionSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingregion.GeocodingRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingregion.GeocodingRegionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 区域数据管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingRegionServiceImpl implements GeocodingRegionService {

    @Resource
    private GeocodingRegionMapper geocodingRegionMapper;

    @Override
    public Long createGeocodingRegion(GeocodingRegionSaveReqVO createReqVO) {
        // 插入
        GeocodingRegionDO geocodingRegion = BeanUtils.toBean(createReqVO, GeocodingRegionDO.class);
        geocodingRegion.setRegionId(UuidUtils.generateUUID());
        geocodingRegionMapper.insert(geocodingRegion);
        // 返回
        return geocodingRegion.getId();
    }

    @Override
    public void updateGeocodingRegion(GeocodingRegionSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingRegionExists(updateReqVO.getId());
        // 更新
        GeocodingRegionDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingRegionDO.class);
        geocodingRegionMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingRegion(Long id) {
        // 校验存在
        validateGeocodingRegionExists(id);
        // 删除
        geocodingRegionMapper.deleteById(id);
    }

    private void validateGeocodingRegionExists(Long id) {
        if (geocodingRegionMapper.selectById(id) == null) {
            throw exception(GEOCODING_REGION_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingRegionDO getGeocodingRegion(Long id) {
        return geocodingRegionMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingRegionDO> getGeocodingRegionPage(GeocodingRegionPageReqVO pageReqVO) {
        return geocodingRegionMapper.selectPage(pageReqVO);
    }

}