package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrectifyarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrectifyarch.InspectRectifyArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectrectifyarch.InspectRectifyArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检整改档案 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRectifyArchServiceImpl implements InspectRectifyArchService {

    @Resource
    private InspectRectifyArchMapper inspectRectifyArchMapper;

    @Override
    public Long createInspectRectifyArch(InspectRectifyArchSaveReqVO createReqVO) {
        // 插入
        InspectRectifyArchDO inspectRectifyArch = BeanUtils.toBean(createReqVO, InspectRectifyArchDO.class);
        inspectRectifyArchMapper.insert(inspectRectifyArch);
        // 返回
        return inspectRectifyArch.getId();
    }

    @Override
    public void updateInspectRectifyArch(InspectRectifyArchSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRectifyArchExists(updateReqVO.getId());
        // 更新
        InspectRectifyArchDO updateObj = BeanUtils.toBean(updateReqVO, InspectRectifyArchDO.class);
        inspectRectifyArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRectifyArch(Long id) {
        // 校验存在
        validateInspectRectifyArchExists(id);
        // 删除
        inspectRectifyArchMapper.deleteById(id);
    }

    private void validateInspectRectifyArchExists(Long id) {
        if (inspectRectifyArchMapper.selectById(id) == null) {
            throw exception(INSPECT_RECTIFY_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public InspectRectifyArchDO getInspectRectifyArch(Long id) {
        return inspectRectifyArchMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRectifyArchDO> getInspectRectifyArchPage(InspectRectifyArchPageReqVO pageReqVO) {
        return inspectRectifyArchMapper.selectPage(pageReqVO);
    }

}