package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingpoi;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingpoi.GeocodingPoiDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingpoi.GeocodingPoiMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 兴趣点数据管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingPoiServiceImpl implements GeocodingPoiService {

    @Resource
    private GeocodingPoiMapper geocodingPoiMapper;

    @Override
    public Long createGeocodingPoi(GeocodingPoiSaveReqVO createReqVO) {
        // 插入
        GeocodingPoiDO geocodingPoi = BeanUtils.toBean(createReqVO, GeocodingPoiDO.class);
        geocodingPoi.setPoiId(UuidUtils.generateUUID());
        geocodingPoiMapper.insert(geocodingPoi);
        // 返回
        return geocodingPoi.getId();
    }

    @Override
    public void updateGeocodingPoi(GeocodingPoiSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingPoiExists(updateReqVO.getId());
        // 更新
        GeocodingPoiDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingPoiDO.class);
        geocodingPoiMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingPoi(Long id) {
        // 校验存在
        validateGeocodingPoiExists(id);
        // 删除
        geocodingPoiMapper.deleteById(id);
    }

    private void validateGeocodingPoiExists(Long id) {
        if (geocodingPoiMapper.selectById(id) == null) {
            throw exception(GEOCODING_POI_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingPoiDO getGeocodingPoi(Long id) {
        return geocodingPoiMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingPoiDO> getGeocodingPoiPage(GeocodingPoiPageReqVO pageReqVO) {
        return geocodingPoiMapper.selectPage(pageReqVO);
    }

}