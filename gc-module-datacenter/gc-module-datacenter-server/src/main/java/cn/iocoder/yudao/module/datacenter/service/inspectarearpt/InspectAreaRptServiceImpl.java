package cn.iocoder.yudao.module.datacenter.service.inspectarearpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectarearpt.InspectAreaRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspectarearpt.InspectAreaRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检区域统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectAreaRptServiceImpl implements InspectAreaRptService {

    @Resource
    private InspectAreaRptMapper inspectAreaRptMapper;

    @Override
    public Long createInspectAreaRpt(InspectAreaRptSaveReqVO createReqVO) {
        // 插入
        InspectAreaRptDO inspectAreaRpt = BeanUtils.toBean(createReqVO, InspectAreaRptDO.class);
        inspectAreaRptMapper.insert(inspectAreaRpt);
        // 返回
        return inspectAreaRpt.getId();
    }

    @Override
    public void updateInspectAreaRpt(InspectAreaRptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectAreaRptExists(updateReqVO.getId());
        // 更新
        InspectAreaRptDO updateObj = BeanUtils.toBean(updateReqVO, InspectAreaRptDO.class);
        inspectAreaRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectAreaRpt(Long id) {
        // 校验存在
        validateInspectAreaRptExists(id);
        // 删除
        inspectAreaRptMapper.deleteById(id);
    }

    private void validateInspectAreaRptExists(Long id) {
        if (inspectAreaRptMapper.selectById(id) == null) {
            throw exception(INSPECT_AREA_RPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectAreaRptDO getInspectAreaRpt(Long id) {
        return inspectAreaRptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectAreaRptDO> getInspectAreaRptPage(InspectAreaRptPageReqVO pageReqVO) {
        return inspectAreaRptMapper.selectPage(pageReqVO);
    }

}