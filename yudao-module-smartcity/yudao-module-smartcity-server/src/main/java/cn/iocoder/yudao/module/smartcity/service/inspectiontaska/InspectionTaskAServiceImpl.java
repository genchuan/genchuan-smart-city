package cn.iocoder.yudao.module.smartcity.service.inspectiontaska;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectiontaska.InspectionTaskADO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectiontaska.InspectionTaskAMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查任务 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InspectionTaskAServiceImpl implements InspectionTaskAService {

    @Resource
    private InspectionTaskAMapper inspectionTaskAMapper;

    @Override
    public Long createInspectionTaskA(InspectionTaskASaveReqVO createReqVO) {
        // 插入
        InspectionTaskADO inspectionTaskA = BeanUtils.toBean(createReqVO, InspectionTaskADO.class);
        inspectionTaskAMapper.insert(inspectionTaskA);
        // 返回
        return inspectionTaskA.getId();
    }

    @Override
    public void updateInspectionTaskA(InspectionTaskASaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionTaskAExists(updateReqVO.getId());
        // 更新
        InspectionTaskADO updateObj = BeanUtils.toBean(updateReqVO, InspectionTaskADO.class);
        inspectionTaskAMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionTaskA(Long id) {
        // 校验存在
        validateInspectionTaskAExists(id);
        // 删除
        inspectionTaskAMapper.deleteById(id);
    }

    private void validateInspectionTaskAExists(Long id) {
        if (inspectionTaskAMapper.selectById(id) == null) {
            throw exception(INSPECTION_TASK_A_NOT_EXISTS);
        }
    }

    @Override
    public InspectionTaskADO getInspectionTaskA(Long id) {
        return inspectionTaskAMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionTaskADO> getInspectionTaskAPage(InspectionTaskAPageReqVO pageReqVO) {
        return inspectionTaskAMapper.selectPage(pageReqVO);
    }

}