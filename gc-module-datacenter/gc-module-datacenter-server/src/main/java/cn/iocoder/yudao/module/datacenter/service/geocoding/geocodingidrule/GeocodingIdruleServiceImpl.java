package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingidrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo.GeocodingIdrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo.GeocodingIdruleSaveReqVO;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingidrule.GeocodingIdruleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.geocoding.geocodingidrule.GeocodingIdruleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 标识码编码规则配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GeocodingIdruleServiceImpl implements GeocodingIdruleService {

    @Resource
    private GeocodingIdruleMapper geocodingIdruleMapper;

    @Override
    public Long createGeocodingIdrule(GeocodingIdruleSaveReqVO createReqVO) {
        // 插入
        GeocodingIdruleDO geocodingIdrule = BeanUtils.toBean(createReqVO, GeocodingIdruleDO.class);
        geocodingIdrule.setIdRuleId(UuidUtils.generateUUID());
        geocodingIdruleMapper.insert(geocodingIdrule);
        // 返回
        return geocodingIdrule.getId();
    }

    @Override
    public void updateGeocodingIdrule(GeocodingIdruleSaveReqVO updateReqVO) {
        // 校验存在
        validateGeocodingIdruleExists(updateReqVO.getId());
        // 更新
        GeocodingIdruleDO updateObj = BeanUtils.toBean(updateReqVO, GeocodingIdruleDO.class);
        geocodingIdruleMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeocodingIdrule(Long id) {
        // 校验存在
        validateGeocodingIdruleExists(id);
        // 删除
        geocodingIdruleMapper.deleteById(id);
    }

    private void validateGeocodingIdruleExists(Long id) {
        if (geocodingIdruleMapper.selectById(id) == null) {
            throw exception(GEOCODING_IDRULE_NOT_EXISTS);
        }
    }

    @Override
    public GeocodingIdruleDO getGeocodingIdrule(Long id) {
        return geocodingIdruleMapper.selectById(id);
    }

    @Override
    public PageResult<GeocodingIdruleDO> getGeocodingIdrulePage(GeocodingIdrulePageReqVO pageReqVO) {
        return geocodingIdruleMapper.selectPage(pageReqVO);
    }

}