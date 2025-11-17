package cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemlevelrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检问题分级记录 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectProblemLevelRecServiceImpl implements InspectProblemLevelRecService {

    @Resource
    private InspectProblemLevelRecMapper inspectProblemLevelRecMapper;

    @Override
    public Long createInspectProblemLevelRec(InspectProblemLevelRecSaveReqVO createReqVO) {
        // 插入
        InspectProblemLevelRecDO inspectProblemLevelRec = BeanUtils.toBean(createReqVO, InspectProblemLevelRecDO.class);
        inspectProblemLevelRecMapper.insert(inspectProblemLevelRec);
        // 返回
        return inspectProblemLevelRec.getId();
    }

    @Override
    public void updateInspectProblemLevelRec(InspectProblemLevelRecSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectProblemLevelRecExists(updateReqVO.getId());
        // 更新
        InspectProblemLevelRecDO updateObj = BeanUtils.toBean(updateReqVO, InspectProblemLevelRecDO.class);
        inspectProblemLevelRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectProblemLevelRec(Long id) {
        // 校验存在
        validateInspectProblemLevelRecExists(id);
        // 删除
        inspectProblemLevelRecMapper.deleteById(id);
    }

    private void validateInspectProblemLevelRecExists(Long id) {
        if (inspectProblemLevelRecMapper.selectById(id) == null) {
            throw exception(INSPECT_PROBLEM_LEVEL_REC_NOT_EXISTS);
        }
    }

    @Override
    public InspectProblemLevelRecDO getInspectProblemLevelRec(Long id) {
        return inspectProblemLevelRecMapper.selectById(id);
    }

    @Override
    public PageResult<InspectProblemLevelRecDO> getInspectProblemLevelRecPage(InspectProblemLevelRecPageReqVO pageReqVO) {
        return inspectProblemLevelRecMapper.selectPage(pageReqVO);
    }

}