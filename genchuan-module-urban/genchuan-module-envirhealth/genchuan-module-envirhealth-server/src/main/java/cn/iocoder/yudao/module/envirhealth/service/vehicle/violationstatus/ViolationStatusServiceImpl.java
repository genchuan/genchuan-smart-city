package cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.ViolationStatusMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.VIOLATION_STATUS_NOT_EXISTS;

/**
 * 违规状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ViolationStatusServiceImpl implements ViolationStatusService {

    @Resource
    private ViolationStatusMapper violationStatusMapper;

    @Override
    public Long createViolationStatus(ViolationStatusSaveReqVO createReqVO) {
        // 插入
        ViolationStatusDO violationStatus = BeanUtils.toBean(createReqVO, ViolationStatusDO.class);
        violationStatusMapper.insert(violationStatus);
        // 返回
        return violationStatus.getId();
    }

    @Override
    public void updateViolationStatus(ViolationStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateViolationStatusExists(updateReqVO.getId());
        // 更新
        ViolationStatusDO updateObj = BeanUtils.toBean(updateReqVO, ViolationStatusDO.class);
        violationStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteViolationStatus(Long id) {
        // 校验存在
        validateViolationStatusExists(id);
        // 删除
        violationStatusMapper.deleteById(id);
    }

    private void validateViolationStatusExists(Long id) {
        if (violationStatusMapper.selectById(id) == null) {
            throw exception(VIOLATION_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public ViolationStatusDO getViolationStatus(Long id) {
        return violationStatusMapper.selectById(id);
    }

    @Override
    public PageResult<ViolationStatusDO> getViolationStatusPage(ViolationStatusPageReqVO pageReqVO) {
        return violationStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getViolationStatusOptions() {

        List<ViolationStatusDO> list;
        list = violationStatusMapper.selectList(
                new LambdaQueryWrapperX<ViolationStatusDO>()
                        .eq(ViolationStatusDO::getDeleted, 0)
                        .orderByDesc(ViolationStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, violationStatusDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(violationStatusDO.getViolationStatusName());
            vo.setValue(violationStatusDO.getViolationStatusId());
            return vo;
        });
    }
}