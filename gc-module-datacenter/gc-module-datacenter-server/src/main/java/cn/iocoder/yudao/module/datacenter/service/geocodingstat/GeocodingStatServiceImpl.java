package cn.iocoder.yudao.module.datacenter.service.geocodingstat;

import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingstat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingstat.GeocodingStatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocodingstat.GeocodingStatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基本地点数据统计报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingStatServiceImpl implements GeocodingStatService {

    @Resource
    private GeocodingStatMapper geocodingStatMapper;

    @Override
    public Long createGeocodingStat(GeocodingStatSaveReqVO createReqVO) {
        // 插入
        GeocodingStatDO geocodingStat = BeanUtils.toBean(createReqVO, GeocodingStatDO.class);
        geocodingStat.setStatId(UuidUtils.generateUUID());
        geocodingStatMapper.insert(geocodingStat);
        // 返回
        return geocodingStat.getId();
    }

    @Override
    public void updateGeocodingStat(GeocodingStatSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingStatExists(updateReqVO.getId());
        // 更新
        GeocodingStatDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingStatDO.class);
        geocodingStatMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingStat(Long id) {
        // 校验存在
        validateGeocodingStatExists(id);
        // 删除
        geocodingStatMapper.deleteById(id);
    }

    private void validateGeocodingStatExists(Long id) {
        if (geocodingStatMapper.selectById(id) == null) {
            throw exception(GEOCODING_STAT_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingStatDO getGeocodingStat(Long id) {
        return geocodingStatMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingStatDO> getGeocodingStatPage(GeocodingStatPageReqVO pageReqVO) {
        return geocodingStatMapper.selectPage(pageReqVO);
    }

}