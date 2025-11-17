package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectdailyexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectdailyexecrec.InspectDailyExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectdailyexecrec.InspectDailyExecRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 日常巡查执行记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectDailyExecRecServiceImpl implements InspectDailyExecRecService {

    @Resource
    private InspectDailyExecRecMapper inspectDailyExecRecMapper;

    @Override
    public Long createInspectDailyExecRec(InspectDailyExecRecSaveReqVO createReqVO) {
        // 插入
        InspectDailyExecRecDO inspectDailyExecRec = BeanUtils.toBean(createReqVO, InspectDailyExecRecDO.class);
        inspectDailyExecRecMapper.insert(inspectDailyExecRec);
        // 返回
        return inspectDailyExecRec.getId();
    }

    @Override
    public void updateInspectDailyExecRec(InspectDailyExecRecSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectDailyExecRecExists(updateReqVO.getId());
        // 更新
        InspectDailyExecRecDO updateObj = BeanUtils.toBean(updateReqVO, InspectDailyExecRecDO.class);
        inspectDailyExecRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectDailyExecRec(Long id) {
        // 校验存在
        validateInspectDailyExecRecExists(id);
        // 删除
        inspectDailyExecRecMapper.deleteById(id);
    }

    private void validateInspectDailyExecRecExists(Long id) {
        if (inspectDailyExecRecMapper.selectById(id) == null) {
            throw exception(INSPECT_DAILY_EXEC_REC_NOT_EXISTS);
        }
    }

    @Override
    public InspectDailyExecRecDO getInspectDailyExecRec(Long id) {
        return inspectDailyExecRecMapper.selectById(id);
    }

    @Override
    public PageResult<InspectDailyExecRecDO> getInspectDailyExecRecPage(InspectDailyExecRecPageReqVO pageReqVO) {
        return inspectDailyExecRecMapper.selectPage(pageReqVO);
    }

}