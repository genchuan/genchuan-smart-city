package cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemSb;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbSaveVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemSb.InspectProblemSbDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.report.inspectproblemSb.InspectProblemSbMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检问题上报记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectProblemSbServiceImpl implements InspectProblemSbService {

    @Resource
    private InspectProblemSbMapper inspectProblemSbMapper;

    @Override
    public Long createInspectProblemRpt(InspectProblemSbSaveVO createReqVO) {
        // 插入
        InspectProblemSbDO inspectProblemRpt = BeanUtils.toBean(createReqVO, InspectProblemSbDO.class);
        inspectProblemSbMapper.insert(inspectProblemRpt);
        // 返回
        return inspectProblemRpt.getId();
    }

    @Override
    public void updateInspectProblemRpt(InspectProblemSbSaveVO updateReqVO) {
        // 校验存在
        validateInspectProblemRptExists(updateReqVO.getId());
        // 更新
        InspectProblemSbDO updateObj = BeanUtils.toBean(updateReqVO, InspectProblemSbDO.class);
        inspectProblemSbMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectProblemRpt(Long id) {
        // 校验存在
        validateInspectProblemRptExists(id);
        // 删除
        inspectProblemSbMapper.deleteById(id);
    }

    private void validateInspectProblemRptExists(Long id) {
        if (inspectProblemSbMapper.selectById(id) == null) {
            throw exception(INSPECT_PROBLEM_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectProblemSbDO getInspectProblemRpt(Long id) {
        return inspectProblemSbMapper.selectById(id);
    }

    @Override
    public PageResult<InspectProblemSbDO> getInspectProblemRptPage(InspectProblemSbPageReqVO pageReqVO) {
        return inspectProblemSbMapper.selectPage(pageReqVO);
    }

}