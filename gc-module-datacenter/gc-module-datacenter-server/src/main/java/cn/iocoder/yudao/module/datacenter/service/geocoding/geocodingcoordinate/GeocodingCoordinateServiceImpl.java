package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingcoordinate;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinateSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingcoordinate.GeocodingCoordinateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingcoordinate.GeocodingCoordinateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 坐标系配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingCoordinateServiceImpl implements GeocodingCoordinateService {

    @Resource
    private GeocodingCoordinateMapper geocodingCoordinateMapper;

    @Override
    public Long createGeocodingCoordinate(GeocodingCoordinateSaveReqVO createReqVO) {
        // 插入
        GeocodingCoordinateDO geocodingCoordinate = BeanUtils.toBean(createReqVO, GeocodingCoordinateDO.class);
        geocodingCoordinate.setCoordinateId(UuidUtils.generateUUID());
        geocodingCoordinateMapper.insert(geocodingCoordinate);
        // 返回
        return geocodingCoordinate.getId();
    }

    @Override
    public void updateGeocodingCoordinate(GeocodingCoordinateSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingCoordinateExists(updateReqVO.getId());
        // 更新
        GeocodingCoordinateDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingCoordinateDO.class);
        geocodingCoordinateMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingCoordinate(Long id) {
        // 校验存在
        validateGeocodingCoordinateExists(id);
        // 删除
        geocodingCoordinateMapper.deleteById(id);
    }

    private void validateGeocodingCoordinateExists(Long id) {
        if (geocodingCoordinateMapper.selectById(id) == null) {
            throw exception(GEOCODING_COORDINATE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingCoordinateDO getGeocodingCoordinate(Long id) {
        return geocodingCoordinateMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingCoordinateDO> getGeocodingCoordinatePage(GeocodingCoordinatePageReqVO pageReqVO) {
        return geocodingCoordinateMapper.selectPage(pageReqVO);
    }

}