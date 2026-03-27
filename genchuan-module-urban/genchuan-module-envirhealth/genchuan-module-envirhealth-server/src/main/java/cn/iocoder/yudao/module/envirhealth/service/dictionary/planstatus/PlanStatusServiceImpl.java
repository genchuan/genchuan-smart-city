package cn.iocoder.yudao.module.envirhealth.service.dictionary.planstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.planstatus.vo.PlanStatusOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.planstatus.vo.PlanStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.planstatus.vo.PlanStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.PlanStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.PlanStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PLAN_STATUS_NOT_EXISTS;

/**
 * 计划状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PlanStatusServiceImpl implements PlanStatusService {

    @Resource
    private PlanStatusMapper planStatusMapper;

    @Override
    public Long createPlanStatus(PlanStatusSaveReqVO createReqVO) {
        // 插入
        PlanStatusDO planStatus = BeanUtils.toBean(createReqVO, PlanStatusDO.class);
        planStatusMapper.insert(planStatus);
        // 返回
        return planStatus.getId();
    }

    @Override
    public void updatePlanStatus(PlanStatusSaveReqVO updateReqVO) {
        // 校验存在
        validatePlanStatusExists(updateReqVO.getId());
        // 更新
        PlanStatusDO updateObj = BeanUtils.toBean(updateReqVO, PlanStatusDO.class);
        planStatusMapper.updateById(updateObj);
    }

    @Override
    public void deletePlanStatus(Long id) {
        // 校验存在
        validatePlanStatusExists(id);
        // 删除
        planStatusMapper.deleteById(id);
    }

    private void validatePlanStatusExists(Long id) {
        if (planStatusMapper.selectById(id) == null) {
            throw exception(PLAN_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public PlanStatusDO getPlanStatus(Long id) {
        return planStatusMapper.selectById(id);
    }

    @Override
    public PageResult<PlanStatusDO> getPlanStatusPage(PlanStatusPageReqVO pageReqVO) {
        return planStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PlanStatusOptionVO> getPlanStatusOptions() {

        List<PlanStatusDO> list;
        list = planStatusMapper.selectList(
                new LambdaQueryWrapperX<PlanStatusDO>()
                        .eq(PlanStatusDO::getDeleted, 0)
                        .in(PlanStatusDO::getName, "未开始", "进行中", "已完成","已暂停")
                        .orderByDesc(PlanStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, planStatusDO -> {
            PlanStatusOptionVO vo = new PlanStatusOptionVO();
            vo.setLabel(planStatusDO.getName());
            vo.setValue(planStatusDO.getSysPlanStatusId());
            return vo;
        });
    }
}