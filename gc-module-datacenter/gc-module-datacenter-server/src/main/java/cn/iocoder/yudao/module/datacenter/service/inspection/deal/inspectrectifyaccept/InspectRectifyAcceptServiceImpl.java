package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifyaccept;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检整改结果验收 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRectifyAcceptServiceImpl implements InspectRectifyAcceptService {

    @Resource
    private InspectRectifyAcceptMapper inspectRectifyAcceptMapper;

    @Override
    public Long createInspectRectifyAccept(InspectRectifyAcceptSaveReqVO createReqVO) {
        // 插入
        InspectRectifyAcceptDO inspectRectifyAccept = BeanUtils.toBean(createReqVO, InspectRectifyAcceptDO.class);
        inspectRectifyAcceptMapper.insert(inspectRectifyAccept);
        // 返回
        return inspectRectifyAccept.getId();
    }

    @Override
    public void updateInspectRectifyAccept(InspectRectifyAcceptSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRectifyAcceptExists(updateReqVO.getId());
        // 更新
        InspectRectifyAcceptDO updateObj = BeanUtils.toBean(updateReqVO, InspectRectifyAcceptDO.class);
        inspectRectifyAcceptMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRectifyAccept(Long id) {
        // 校验存在
        validateInspectRectifyAcceptExists(id);
        // 删除
        inspectRectifyAcceptMapper.deleteById(id);
    }

    private void validateInspectRectifyAcceptExists(Long id) {
        if (inspectRectifyAcceptMapper.selectById(id) == null) {
            throw exception(INSPECT_RECTIFY_ACCEPT_NOT_EXISTS);
        }
    }

    @Override
    public InspectRectifyAcceptDO getInspectRectifyAccept(Long id) {
        return inspectRectifyAcceptMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRectifyAcceptDO> getInspectRectifyAcceptPage(InspectRectifyAcceptPageReqVO pageReqVO) {
        return inspectRectifyAcceptMapper.selectPage(pageReqVO);
    }

}