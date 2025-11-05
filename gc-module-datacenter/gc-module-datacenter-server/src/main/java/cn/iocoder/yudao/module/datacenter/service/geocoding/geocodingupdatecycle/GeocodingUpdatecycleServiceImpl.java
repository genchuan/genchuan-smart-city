package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingupdatecycle;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecyclePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo.GeocodingUpdatecycleSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingupdatecycle.GeocodingUpdatecycleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingupdatecycle.GeocodingUpdatecycleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 数据更新周期配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingUpdatecycleServiceImpl implements GeocodingUpdatecycleService {

    @Resource
    private GeocodingUpdatecycleMapper geocodingUpdatecycleMapper;

    @Override
    public Long createGeocodingUpdatecycle(GeocodingUpdatecycleSaveReqVO createReqVO) {
        // 插入
        GeocodingUpdatecycleDO geocodingUpdatecycle = BeanUtils.toBean(createReqVO, GeocodingUpdatecycleDO.class);
        geocodingUpdatecycle.setUpdateCycleId(UuidUtils.generateUUID());
        geocodingUpdatecycleMapper.insert(geocodingUpdatecycle);
        // 返回
        return geocodingUpdatecycle.getId();
    }

    @Override
    public void updateGeocodingUpdatecycle(GeocodingUpdatecycleSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingUpdatecycleExists(updateReqVO.getId());
        // 更新
        GeocodingUpdatecycleDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingUpdatecycleDO.class);
        geocodingUpdatecycleMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingUpdatecycle(Long id) {
        // 校验存在
        validateGeocodingUpdatecycleExists(id);
        // 删除
        geocodingUpdatecycleMapper.deleteById(id);
    }

    private void validateGeocodingUpdatecycleExists(Long id) {
        if (geocodingUpdatecycleMapper.selectById(id) == null) {
            throw exception(GEOCODING_UPDATECYCLE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingUpdatecycleDO getGeocodingUpdatecycle(Long id) {
        return geocodingUpdatecycleMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingUpdatecycleDO> getGeocodingUpdatecyclePage(GeocodingUpdatecyclePageReqVO pageReqVO) {
        return geocodingUpdatecycleMapper.selectPage(pageReqVO);
    }

}