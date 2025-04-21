package cn.iocoder.yudao.module.smartcity.service.developmentinspectionplan;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.developmentinspectionplan.DevelopmentInspectionPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.developmentinspectionplan.DevelopmentInspectionPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查计划管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DevelopmentInspectionPlanServiceImpl implements DevelopmentInspectionPlanService {

    @Resource
    private DevelopmentInspectionPlanMapper developmentInspectionPlanMapper;

    @Override
    public Long createDevelopmentInspectionPlan(DevelopmentInspectionPlanSaveReqVO createReqVO) {
        // 插入
        DevelopmentInspectionPlanDO developmentInspectionPlan = BeanUtils.toBean(createReqVO, DevelopmentInspectionPlanDO.class);
        developmentInspectionPlanMapper.insert(developmentInspectionPlan);
        // 返回
        return developmentInspectionPlan.getId();
    }

    @Override
    public void updateDevelopmentInspectionPlan(DevelopmentInspectionPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateDevelopmentInspectionPlanExists(updateReqVO.getId());
        // 更新
        DevelopmentInspectionPlanDO updateObj = BeanUtils.toBean(updateReqVO, DevelopmentInspectionPlanDO.class);
        developmentInspectionPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteDevelopmentInspectionPlan(Long id) {
        // 校验存在
        validateDevelopmentInspectionPlanExists(id);
        // 删除
        developmentInspectionPlanMapper.deleteById(id);
    }

    private void validateDevelopmentInspectionPlanExists(Long id) {
        if (developmentInspectionPlanMapper.selectById(id) == null) {
            throw exception(DEVELOPMENT_INSPECTION_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public DevelopmentInspectionPlanDO getDevelopmentInspectionPlan(Long id) {
        return developmentInspectionPlanMapper.selectById(id);
    }

    @Override
    public PageResult<DevelopmentInspectionPlanDO> getDevelopmentInspectionPlanPage(DevelopmentInspectionPlanPageReqVO pageReqVO) {
        return developmentInspectionPlanMapper.selectPage(pageReqVO);
    }

}