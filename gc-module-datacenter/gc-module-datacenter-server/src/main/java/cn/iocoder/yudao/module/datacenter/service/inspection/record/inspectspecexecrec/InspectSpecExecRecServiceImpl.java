package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectspecexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectspecexecrec.InspectSpecExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectspecexecrec.InspectSpecExecRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 专项巡查执行记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSpecExecRecServiceImpl implements InspectSpecExecRecService {

    @Resource
    private InspectSpecExecRecMapper inspectSpecExecRecMapper;

    @Override
    public Long createInspectSpecExecRec(InspectSpecExecRecSaveReqVO createReqVO) {
        // 插入
        InspectSpecExecRecDO inspectSpecExecRec = BeanUtils.toBean(createReqVO, InspectSpecExecRecDO.class);
        inspectSpecExecRecMapper.insert(inspectSpecExecRec);
        // 返回
        return inspectSpecExecRec.getId();
    }

    @Override
    public void updateInspectSpecExecRec(InspectSpecExecRecSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSpecExecRecExists(updateReqVO.getId());
        // 更新
        InspectSpecExecRecDO updateObj = BeanUtils.toBean(updateReqVO, InspectSpecExecRecDO.class);
        inspectSpecExecRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSpecExecRec(Long id) {
        // 校验存在
        validateInspectSpecExecRecExists(id);
        // 删除
        inspectSpecExecRecMapper.deleteById(id);
    }

    private void validateInspectSpecExecRecExists(Long id) {
        if (inspectSpecExecRecMapper.selectById(id) == null) {
            throw exception(INSPECT_SPEC_EXEC_REC_NOT_EXISTS);
        }
    }

    @Override
    public InspectSpecExecRecDO getInspectSpecExecRec(Long id) {
        return inspectSpecExecRecMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSpecExecRecDO> getInspectSpecExecRecPage(InspectSpecExecRecPageReqVO pageReqVO) {
        return inspectSpecExecRecMapper.selectPage(pageReqVO);
    }

}