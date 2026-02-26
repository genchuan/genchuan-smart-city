package cn.iocoder.yudao.module.envir.service.facility;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.facility.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.facility.FacilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.facility.FacilityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 设施字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FacilityServiceImpl implements FacilityService {

    @Resource
    private FacilityMapper facilityMapper;

    @Override
    public Long createFacility(FacilitySaveReqVO createReqVO) {
        // 插入
        FacilityDO facility = BeanUtils.toBean(createReqVO, FacilityDO.class);
        facilityMapper.insert(facility);
        // 返回
        return facility.getId();
    }

    @Override
    public void updateFacility(FacilitySaveReqVO updateReqVO) {
        // 校验存在
        validateFacilityExists(updateReqVO.getId());
        // 更新
        FacilityDO updateObj = BeanUtils.toBean(updateReqVO, FacilityDO.class);
        facilityMapper.updateById(updateObj);
    }

    @Override
    public void deleteFacility(Long id) {
        // 校验存在
        validateFacilityExists(id);
        // 删除
        facilityMapper.deleteById(id);
    }

    private void validateFacilityExists(Long id) {
        if (facilityMapper.selectById(id) == null) {
            throw exception(FACILITY_NOT_EXISTS);
        }
    }

    @Override
    public FacilityDO getFacility(Long id) {
        return facilityMapper.selectById(id);
    }

    @Override
    public PageResult<FacilityDO> getFacilityPage(FacilityPageReqVO pageReqVO) {
        return facilityMapper.selectPage(pageReqVO);
    }

}