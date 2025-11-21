package cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttaskallocaterec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检任务分配记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectTaskAllocateRecServiceImpl implements InspectTaskAllocateRecService {

    @Resource
    private InspectTaskAllocateRecMapper inspectTaskAllocateRecMapper;

    @Override
    public Long createInspectTaskAllocateRec(InspectTaskAllocateRecSaveReqVO createReqVO) {
        // 插入
        InspectTaskAllocateRecDO inspectTaskAllocateRec = BeanUtils.toBean(createReqVO, InspectTaskAllocateRecDO.class);
        inspectTaskAllocateRecMapper.insert(inspectTaskAllocateRec);
        // 返回
        return inspectTaskAllocateRec.getId();
    }

    @Override
    public void updateInspectTaskAllocateRec(InspectTaskAllocateRecSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTaskAllocateRecExists(updateReqVO.getId());
        // 更新
        InspectTaskAllocateRecDO updateObj = BeanUtils.toBean(updateReqVO, InspectTaskAllocateRecDO.class);
        inspectTaskAllocateRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTaskAllocateRec(Long id) {
        // 校验存在
        validateInspectTaskAllocateRecExists(id);
        // 删除
        inspectTaskAllocateRecMapper.deleteById(id);
    }

    private void validateInspectTaskAllocateRecExists(Long id) {
        if (inspectTaskAllocateRecMapper.selectById(id) == null) {
            throw exception(INSPECT_TASK_ALLOCATE_REC_NOT_EXISTS);
        }
    }

    @Override
    public InspectTaskAllocateRecDO getInspectTaskAllocateRec(Long id) {
        return inspectTaskAllocateRecMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTaskAllocateRecDO> getInspectTaskAllocateRecPage(InspectTaskAllocateRecPageReqVO pageReqVO) {
        return inspectTaskAllocateRecMapper.selectPage(pageReqVO);
    }

}