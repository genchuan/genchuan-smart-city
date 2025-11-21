package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectledgerarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectledgerarch.InspectLedgerArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectledgerarch.InspectLedgerArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检台账档案 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectLedgerArchServiceImpl implements InspectLedgerArchService {

    @Resource
    private InspectLedgerArchMapper inspectLedgerArchMapper;

    @Override
    public Long createInspectLedgerArch(InspectLedgerArchSaveReqVO createReqVO) {
        // 插入
        InspectLedgerArchDO inspectLedgerArch = BeanUtils.toBean(createReqVO, InspectLedgerArchDO.class);
        inspectLedgerArchMapper.insert(inspectLedgerArch);
        // 返回
        return inspectLedgerArch.getId();
    }

    @Override
    public void updateInspectLedgerArch(InspectLedgerArchSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectLedgerArchExists(updateReqVO.getId());
        // 更新
        InspectLedgerArchDO updateObj = BeanUtils.toBean(updateReqVO, InspectLedgerArchDO.class);
        inspectLedgerArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectLedgerArch(Long id) {
        // 校验存在
        validateInspectLedgerArchExists(id);
        // 删除
        inspectLedgerArchMapper.deleteById(id);
    }

    private void validateInspectLedgerArchExists(Long id) {
        if (inspectLedgerArchMapper.selectById(id) == null) {
            throw exception(INSPECT_LEDGER_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public InspectLedgerArchDO getInspectLedgerArch(Long id) {
        return inspectLedgerArchMapper.selectById(id);
    }

    @Override
    public PageResult<InspectLedgerArchDO> getInspectLedgerArchPage(InspectLedgerArchPageReqVO pageReqVO) {
        return inspectLedgerArchMapper.selectPage(pageReqVO);
    }

}