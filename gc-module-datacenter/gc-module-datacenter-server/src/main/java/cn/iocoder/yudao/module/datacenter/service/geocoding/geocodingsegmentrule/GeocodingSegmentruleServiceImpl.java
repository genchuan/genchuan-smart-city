package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingsegmentrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentruleSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingsegmentrule.GeocodingSegmentruleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingsegmentrule.GeocodingSegmentruleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 地理编码分段组合规则配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingSegmentruleServiceImpl implements GeocodingSegmentruleService {

    @Resource
    private GeocodingSegmentruleMapper geocodingSegmentruleMapper;

    @Override
    public Long createGeocodingSegmentrule(GeocodingSegmentruleSaveReqVO createReqVO) {
        // 插入
        GeocodingSegmentruleDO geocodingSegmentrule = BeanUtils.toBean(createReqVO, GeocodingSegmentruleDO.class);
        geocodingSegmentrule.setSegmentRuleId(UuidUtils.generateUUID());
        geocodingSegmentruleMapper.insert(geocodingSegmentrule);
        // 返回
        return geocodingSegmentrule.getId();
    }

    @Override
    public void updateGeocodingSegmentrule(GeocodingSegmentruleSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingSegmentruleExists(updateReqVO.getId());
        // 更新
        GeocodingSegmentruleDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingSegmentruleDO.class);
        geocodingSegmentruleMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingSegmentrule(Long id) {
        // 校验存在
        validateGeocodingSegmentruleExists(id);
        // 删除
        geocodingSegmentruleMapper.deleteById(id);
    }

    private void validateGeocodingSegmentruleExists(Long id) {
        if (geocodingSegmentruleMapper.selectById(id) == null) {
            throw exception(GEOCODING_SEGMENTRULE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingSegmentruleDO getGeocodingSegmentrule(Long id) {
        return geocodingSegmentruleMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingSegmentruleDO> getGeocodingSegmentrulePage(GeocodingSegmentrulePageReqVO pageReqVO) {
        return geocodingSegmentruleMapper.selectPage(pageReqVO);
    }

}