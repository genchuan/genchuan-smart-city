package cn.iocoder.yudao.module.envirhealth.service.vehicle.workstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.ViolationTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus.ViolationTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.VIOLATION_TYPE_NOT_EXISTS;

/**
 * 违规类型字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ViolationTypeServiceImpl implements ViolationTypeService {

    @Resource
    private ViolationTypeMapper violationTypeMapper;

    @Override
    public Long createViolationType(ViolationTypeSaveReqVO createReqVO) {
        // 插入
        ViolationTypeDO violationType = BeanUtils.toBean(createReqVO, ViolationTypeDO.class);
        violationTypeMapper.insert(violationType);
        // 返回
        return violationType.getId();
    }

    @Override
    public void updateViolationType(ViolationTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateViolationTypeExists(updateReqVO.getId());
        // 更新
        ViolationTypeDO updateObj = BeanUtils.toBean(updateReqVO, ViolationTypeDO.class);
        violationTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteViolationType(Long id) {
        // 校验存在
        validateViolationTypeExists(id);
        // 删除
        violationTypeMapper.deleteById(id);
    }

    private void validateViolationTypeExists(Long id) {
        if (violationTypeMapper.selectById(id) == null) {
            throw exception(VIOLATION_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ViolationTypeDO getViolationType(Long id) {
        return violationTypeMapper.selectById(id);
    }

    @Override
    public PageResult<ViolationTypeDO> getViolationTypePage(ViolationTypePageReqVO pageReqVO) {
        return violationTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getViolationTypeOptions() {

        List<ViolationTypeDO> list;
        list = violationTypeMapper.selectList(
                new LambdaQueryWrapperX<ViolationTypeDO>()
                        .eq(ViolationTypeDO::getDeleted, 0)
                        .orderByDesc(ViolationTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, violationTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(violationTypeDO.getViolationName());
            vo.setValue(violationTypeDO.getViolationTypeId());
            return vo;
        });
    }
}