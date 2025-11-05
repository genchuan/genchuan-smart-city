package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodinghouse;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHousePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHouseSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinghouse.GeocodingHouseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodinghouse.GeocodingHouseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 门(楼)牌数据管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingHouseServiceImpl implements GeocodingHouseService {

    @Resource
    private GeocodingHouseMapper geocodingHouseMapper;

    @Override
    public Long createGeocodingHouse(GeocodingHouseSaveReqVO createReqVO) {
        // 插入
        GeocodingHouseDO geocodingHouse = BeanUtils.toBean(createReqVO, GeocodingHouseDO.class);
        geocodingHouse.setHouseId(UuidUtils.generateUUID());
        geocodingHouseMapper.insert(geocodingHouse);
        // 返回
        return geocodingHouse.getId();
    }

    @Override
    public void updateGeocodingHouse(GeocodingHouseSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingHouseExists(updateReqVO.getId());
        // 更新
        GeocodingHouseDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingHouseDO.class);
        geocodingHouseMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingHouse(Long id) {
        // 校验存在
        validateGeocodingHouseExists(id);
        // 删除
        geocodingHouseMapper.deleteById(id);
    }

    private void validateGeocodingHouseExists(Long id) {
        if (geocodingHouseMapper.selectById(id) == null) {
            throw exception(GEOCODING_HOUSE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingHouseDO getGeocodingHouse(Long id) {
        return geocodingHouseMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingHouseDO> getGeocodingHousePage(GeocodingHousePageReqVO pageReqVO) {
        return geocodingHouseMapper.selectPage(pageReqVO);
    }

}