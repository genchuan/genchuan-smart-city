package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectacceptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectacceptarch.InspectAcceptArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectacceptarch.InspectAcceptArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检验收档案 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectAcceptArchServiceImpl implements InspectAcceptArchService {

    @Resource
    private InspectAcceptArchMapper inspectAcceptArchMapper;

    @Override
    public Long createInspectAcceptArch(InspectAcceptArchSaveReqVO createReqVO) {
        // 插入
        InspectAcceptArchDO inspectAcceptArch = BeanUtils.toBean(createReqVO, InspectAcceptArchDO.class);
        inspectAcceptArchMapper.insert(inspectAcceptArch);
        // 返回
        return inspectAcceptArch.getId();
    }

    @Override
    public void updateInspectAcceptArch(InspectAcceptArchSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectAcceptArchExists(updateReqVO.getId());
        // 更新
        InspectAcceptArchDO updateObj = BeanUtils.toBean(updateReqVO, InspectAcceptArchDO.class);
        inspectAcceptArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectAcceptArch(Long id) {
        // 校验存在
        validateInspectAcceptArchExists(id);
        // 删除
        inspectAcceptArchMapper.deleteById(id);
    }

    private void validateInspectAcceptArchExists(Long id) {
        if (inspectAcceptArchMapper.selectById(id) == null) {
            throw exception(INSPECT_ACCEPT_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public InspectAcceptArchDO getInspectAcceptArch(Long id) {
        return inspectAcceptArchMapper.selectById(id);
    }

    @Override
    public PageResult<InspectAcceptArchDO> getInspectAcceptArchPage(InspectAcceptArchPageReqVO pageReqVO) {
        return inspectAcceptArchMapper.selectPage(pageReqVO);
    }

}