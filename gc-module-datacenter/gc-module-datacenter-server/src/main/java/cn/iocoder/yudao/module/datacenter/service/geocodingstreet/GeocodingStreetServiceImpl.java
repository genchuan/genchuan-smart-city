package cn.iocoder.yudao.module.datacenter.service.geocodingstreet;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingstreet.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingstreet.GeocodingStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocodingstreet.GeocodingStreetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 街巷数据管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingStreetServiceImpl implements GeocodingStreetService {

    @Resource
    private GeocodingStreetMapper geocodingStreetMapper;

    @Override
    public Long createGeocodingStreet(GeocodingStreetSaveReqVO createReqVO) {
        // 插入
        GeocodingStreetDO geocodingStreet = BeanUtils.toBean(createReqVO, GeocodingStreetDO.class);
        geocodingStreetMapper.insert(geocodingStreet);
        // 返回
        return geocodingStreet.getId();
    }

    @Override
    public void updateGeocodingStreet(GeocodingStreetSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingStreetExists(updateReqVO.getId());
        // 更新
        GeocodingStreetDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingStreetDO.class);
        geocodingStreetMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingStreet(Long id) {
        // 校验存在
        validateGeocodingStreetExists(id);
        // 删除
        geocodingStreetMapper.deleteById(id);
    }

    private void validateGeocodingStreetExists(Long id) {
        if (geocodingStreetMapper.selectById(id) == null) {
            throw exception(GEOCODING_STREET_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingStreetDO getGeocodingStreet(Long id) {
        return geocodingStreetMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingStreetDO> getGeocodingStreetPage(GeocodingStreetPageReqVO pageReqVO) {
        return geocodingStreetMapper.selectPage(pageReqVO);
    }

}