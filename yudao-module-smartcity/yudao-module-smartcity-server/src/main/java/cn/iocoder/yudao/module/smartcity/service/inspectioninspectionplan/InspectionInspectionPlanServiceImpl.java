package cn.iocoder.yudao.module.smartcity.service.inspectioninspectionplan;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectioninspectionplan.InspectionInspectionPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectioninspectionplan.InspectionInspectionPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查计划 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InspectionInspectionPlanServiceImpl implements InspectionInspectionPlanService {

    @Resource
    private InspectionInspectionPlanMapper inspectionInspectionPlanMapper;

    @Override
    public Long createInspectionInspectionPlan(InspectionInspectionPlanSaveReqVO createReqVO) {
        // 插入
        InspectionInspectionPlanDO inspectionInspectionPlan = BeanUtils.toBean(createReqVO, InspectionInspectionPlanDO.class);
        inspectionInspectionPlanMapper.insert(inspectionInspectionPlan);
        // 返回
        return inspectionInspectionPlan.getId();
    }

    @Override
    public void updateInspectionInspectionPlan(InspectionInspectionPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionInspectionPlanExists(updateReqVO.getId());
        // 更新
        InspectionInspectionPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectionInspectionPlanDO.class);
        inspectionInspectionPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionInspectionPlan(Long id) {
        // 校验存在
        validateInspectionInspectionPlanExists(id);
        // 删除
        inspectionInspectionPlanMapper.deleteById(id);
    }

    private void validateInspectionInspectionPlanExists(Long id) {
        if (inspectionInspectionPlanMapper.selectById(id) == null) {
            throw exception(INSPECTION_INSPECTION_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public InspectionInspectionPlanDO getInspectionInspectionPlan(Long id) {
        return inspectionInspectionPlanMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionInspectionPlanDO> getInspectionInspectionPlanPage(InspectionInspectionPlanPageReqVO pageReqVO) {
        return inspectionInspectionPlanMapper.selectPage(pageReqVO);
    }

}