package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspecttyperpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspecttyperpt.InspectTypeRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspecttyperpt.InspectTypeRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检类型统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectTypeRptServiceImpl implements InspectTypeRptService {

    @Resource
    private InspectTypeRptMapper inspectTypeRptMapper;

    @Override
    public Long createInspectTypeRpt(InspectTypeRptSaveReqVO createReqVO) {
        // 插入
        InspectTypeRptDO inspectTypeRpt = BeanUtils.toBean(createReqVO, InspectTypeRptDO.class);
        inspectTypeRptMapper.insert(inspectTypeRpt);
        // 返回
        return inspectTypeRpt.getId();
    }

    @Override
    public void updateInspectTypeRpt(InspectTypeRptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTypeRptExists(updateReqVO.getId());
        // 更新
        InspectTypeRptDO updateObj = BeanUtils.toBean(updateReqVO, InspectTypeRptDO.class);
        inspectTypeRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTypeRpt(Long id) {
        // 校验存在
        validateInspectTypeRptExists(id);
        // 删除
        inspectTypeRptMapper.deleteById(id);
    }

    private void validateInspectTypeRptExists(Long id) {
        if (inspectTypeRptMapper.selectById(id) == null) {
            throw exception(INSPECT_TYPE_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectTypeRptDO getInspectTypeRpt(Long id) {
        return inspectTypeRptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTypeRptDO> getInspectTypeRptPage(InspectTypeRptPageReqVO pageReqVO) {
        return inspectTypeRptMapper.selectPage(pageReqVO);
    }

}