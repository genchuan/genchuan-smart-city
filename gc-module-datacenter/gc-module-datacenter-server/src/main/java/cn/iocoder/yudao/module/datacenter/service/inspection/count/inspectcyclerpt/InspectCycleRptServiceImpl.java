package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectcyclerpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectcyclerpt.InspectCycleRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectcyclerpt.InspectCycleRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检周期统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectCycleRptServiceImpl implements InspectCycleRptService {

    @Resource
    private InspectCycleRptMapper inspectCycleRptMapper;

    @Override
    public Long createInspectCycleRpt(InspectCycleRptSaveReqVO createReqVO) {
        // 插入
        InspectCycleRptDO inspectCycleRpt = BeanUtils.toBean(createReqVO, InspectCycleRptDO.class);
        inspectCycleRptMapper.insert(inspectCycleRpt);
        // 返回
        return inspectCycleRpt.getId();
    }

    @Override
    public void updateInspectCycleRpt(InspectCycleRptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectCycleRptExists(updateReqVO.getId());
        // 更新
        InspectCycleRptDO updateObj = BeanUtils.toBean(updateReqVO, InspectCycleRptDO.class);
        inspectCycleRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectCycleRpt(Long id) {
        // 校验存在
        validateInspectCycleRptExists(id);
        // 删除
        inspectCycleRptMapper.deleteById(id);
    }

    private void validateInspectCycleRptExists(Long id) {
        if (inspectCycleRptMapper.selectById(id) == null) {
            throw exception(INSPECT_CYCLE_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectCycleRptDO getInspectCycleRpt(Long id) {
        return inspectCycleRptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectCycleRptDO> getInspectCycleRptPage(InspectCycleRptPageReqVO pageReqVO) {
        return inspectCycleRptMapper.selectPage(pageReqVO);
    }

}