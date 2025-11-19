package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectproblemrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectproblemrpt.InspectProblemRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectproblemrpt.InspectProblemRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检问题统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectProblemRptServiceImpl implements InspectProblemRptService {

    @Resource
    private InspectProblemRptMapper inspectProblemRptMapper;

    @Override
    public Long createInspectProblemRpt(InspectProblemRptSaveReqVO createReqVO) {
        // 插入
        InspectProblemRptDO inspectProblemRpt = BeanUtils.toBean(createReqVO, InspectProblemRptDO.class);
        inspectProblemRptMapper.insert(inspectProblemRpt);
        // 返回
        return inspectProblemRpt.getId();
    }

    @Override
    public void updateInspectProblemRpt(InspectProblemRptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectProblemRptExists(updateReqVO.getId());
        // 更新
        InspectProblemRptDO updateObj = BeanUtils.toBean(updateReqVO, InspectProblemRptDO.class);
        inspectProblemRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectProblemRpt(Long id) {
        // 校验存在
        validateInspectProblemRptExists(id);
        // 删除
        inspectProblemRptMapper.deleteById(id);
    }

    private void validateInspectProblemRptExists(Long id) {
        if (inspectProblemRptMapper.selectById(id) == null) {
            throw exception(INSPECT_PROBLEM_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectProblemRptDO getInspectProblemRpt(Long id) {
        return inspectProblemRptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectProblemRptDO> getInspectProblemRptPage(InspectProblemRptPageReqVO pageReqVO) {
        return inspectProblemRptMapper.selectPage(pageReqVO);
    }

}