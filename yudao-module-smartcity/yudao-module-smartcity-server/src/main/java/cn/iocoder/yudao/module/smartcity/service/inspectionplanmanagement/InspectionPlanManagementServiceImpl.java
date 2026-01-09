package cn.iocoder.yudao.module.smartcity.service.inspectionplanmanagement;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionplanmanagement.InspectionPlanManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionplanmanagement.InspectionPlanManagementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查计划管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InspectionPlanManagementServiceImpl implements InspectionPlanManagementService {

    @Resource
    private InspectionPlanManagementMapper inspectionPlanManagementMapper;

    @Override
    public Long createInspectionPlanManagement(InspectionPlanManagementSaveReqVO createReqVO) {
        // 插入
        InspectionPlanManagementDO inspectionPlanManagement = BeanUtils.toBean(createReqVO, InspectionPlanManagementDO.class);
        inspectionPlanManagementMapper.insert(inspectionPlanManagement);
        // 返回
        return inspectionPlanManagement.getId();
    }

    @Override
    public void updateInspectionPlanManagement(InspectionPlanManagementSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionPlanManagementExists(updateReqVO.getId());
        // 更新
        InspectionPlanManagementDO updateObj = BeanUtils.toBean(updateReqVO, InspectionPlanManagementDO.class);
        inspectionPlanManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionPlanManagement(Long id) {
        // 校验存在
        validateInspectionPlanManagementExists(id);
        // 删除
        inspectionPlanManagementMapper.deleteById(id);
    }

    private void validateInspectionPlanManagementExists(Long id) {
        if (inspectionPlanManagementMapper.selectById(id) == null) {
            throw exception(INSPECTION_PLAN_MANAGEMENT_NOT_EXISTS);
        }
    }

    @Override
    public InspectionPlanManagementDO getInspectionPlanManagement(Long id) {
        return inspectionPlanManagementMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionPlanManagementDO> getInspectionPlanManagementPage(InspectionPlanManagementPageReqVO pageReqVO) {
        return inspectionPlanManagementMapper.selectPage(pageReqVO);
    }

}