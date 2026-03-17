package cn.iocoder.yudao.module.envirhealth.service.dictionary.facility;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilityPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilitySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.facility.FacilityDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.facility.FacilityMapper;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.FACILITY_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getFacilityOptions() {

        List<FacilityDO> list;
        list = facilityMapper.selectList(
                new LambdaQueryWrapperX<FacilityDO>()
                        .eq(FacilityDO::getDeleted, 0)
                        .orderByDesc(FacilityDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, facilityDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(facilityDO.getName());
            vo.setValue(facilityDO.getSysFacilityId());
            return vo;
        });
    }

}