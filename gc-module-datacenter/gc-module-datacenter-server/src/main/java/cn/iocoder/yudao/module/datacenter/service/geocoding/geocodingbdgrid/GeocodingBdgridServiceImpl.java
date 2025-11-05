package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingbdgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingbdgrid.GeocodingBdgridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingbdgrid.GeocodingBdgridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 北斗网格位置码配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingBdgridServiceImpl implements GeocodingBdgridService {

    @Resource
    private GeocodingBdgridMapper geocodingBdgridMapper;

    @Override
    public Long createGeocodingBdgrid(GeocodingBdgridSaveReqVO createReqVO) {
        // 插入
        GeocodingBdgridDO geocodingBdgrid = BeanUtils.toBean(createReqVO, GeocodingBdgridDO.class);
        geocodingBdgrid.setBdGridConfigId(UuidUtils.generateUUID());
        geocodingBdgridMapper.insert(geocodingBdgrid);
        // 返回
        return geocodingBdgrid.getId();
    }

    @Override
    public void updateGeocodingBdgrid(GeocodingBdgridSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingBdgridExists(updateReqVO.getId());
        // 更新
        GeocodingBdgridDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingBdgridDO.class);
        geocodingBdgridMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingBdgrid(Long id) {
        // 校验存在
        validateGeocodingBdgridExists(id);
        // 删除
        geocodingBdgridMapper.deleteById(id);
    }

    private void validateGeocodingBdgridExists(Long id) {
        if (geocodingBdgridMapper.selectById(id) == null) {
            throw exception(GEOCODING_BDGRID_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingBdgridDO getGeocodingBdgrid(Long id) {
        return geocodingBdgridMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingBdgridDO> getGeocodingBdgridPage(GeocodingBdgridPageReqVO pageReqVO) {
        return geocodingBdgridMapper.selectPage(pageReqVO);
    }

}