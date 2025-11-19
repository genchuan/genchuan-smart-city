package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectefficacyrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectefficacyrpt.InspectEfficacyRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectefficacyrpt.InspectEfficacyRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检效率统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectEfficacyRptServiceImpl implements InspectEfficacyRptService {

    @Resource
    private InspectEfficacyRptMapper inspectEfficacyRptMapper;

    @Override
    public Long createInspectEfficacyRpt(InspectEfficacyRptSaveReqVO createReqVO) {
        // 插入
        InspectEfficacyRptDO inspectEfficacyRpt = BeanUtils.toBean(createReqVO, InspectEfficacyRptDO.class);
        inspectEfficacyRptMapper.insert(inspectEfficacyRpt);
        // 返回
        return inspectEfficacyRpt.getId();
    }

    @Override
    public void updateInspectEfficacyRpt(InspectEfficacyRptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectEfficacyRptExists(updateReqVO.getId());
        // 更新
        InspectEfficacyRptDO updateObj = BeanUtils.toBean(updateReqVO, InspectEfficacyRptDO.class);
        inspectEfficacyRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectEfficacyRpt(Long id) {
        // 校验存在
        validateInspectEfficacyRptExists(id);
        // 删除
        inspectEfficacyRptMapper.deleteById(id);
    }

    private void validateInspectEfficacyRptExists(Long id) {
        if (inspectEfficacyRptMapper.selectById(id) == null) {
            throw exception(INSPECT_EFFICACY_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectEfficacyRptDO getInspectEfficacyRpt(Long id) {
        return inspectEfficacyRptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectEfficacyRptDO> getInspectEfficacyRptPage(InspectEfficacyRptPageReqVO pageReqVO) {
        return inspectEfficacyRptMapper.selectPage(pageReqVO);
    }

}