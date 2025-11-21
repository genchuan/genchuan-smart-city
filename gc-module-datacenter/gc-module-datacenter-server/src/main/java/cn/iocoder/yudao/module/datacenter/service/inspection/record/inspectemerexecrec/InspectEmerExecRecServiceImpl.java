package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectemerexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectemerexecrec.InspectEmerExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectemerexecrec.InspectEmerExecRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应急巡查执行记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectEmerExecRecServiceImpl implements InspectEmerExecRecService {

    @Resource
    private InspectEmerExecRecMapper inspectEmerExecRecMapper;

    @Override
    public Long createInspectEmerExecRec(InspectEmerExecRecSaveReqVO createReqVO) {
        // 插入
        InspectEmerExecRecDO inspectEmerExecRec = BeanUtils.toBean(createReqVO, InspectEmerExecRecDO.class);
        inspectEmerExecRecMapper.insert(inspectEmerExecRec);
        // 返回
        return inspectEmerExecRec.getId();
    }

    @Override
    public void updateInspectEmerExecRec(InspectEmerExecRecSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectEmerExecRecExists(updateReqVO.getId());
        // 更新
        InspectEmerExecRecDO updateObj = BeanUtils.toBean(updateReqVO, InspectEmerExecRecDO.class);
        inspectEmerExecRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectEmerExecRec(Long id) {
        // 校验存在
        validateInspectEmerExecRecExists(id);
        // 删除
        inspectEmerExecRecMapper.deleteById(id);
    }

    private void validateInspectEmerExecRecExists(Long id) {
        if (inspectEmerExecRecMapper.selectById(id) == null) {
            throw exception(INSPECT_EMER_EXEC_REC_NOT_EXISTS);
        }
    }

    @Override
    public InspectEmerExecRecDO getInspectEmerExecRec(Long id) {
        return inspectEmerExecRecMapper.selectById(id);
    }

    @Override
    public PageResult<InspectEmerExecRecDO> getInspectEmerExecRecPage(InspectEmerExecRecPageReqVO pageReqVO) {
        return inspectEmerExecRecMapper.selectPage(pageReqVO);
    }

}