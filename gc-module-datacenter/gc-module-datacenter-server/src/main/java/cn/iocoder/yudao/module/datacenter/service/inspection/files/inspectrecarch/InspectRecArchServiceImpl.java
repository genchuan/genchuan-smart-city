package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrecarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrecarch.InspectRecArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectrecarch.InspectRecArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检记录档案 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRecArchServiceImpl implements InspectRecArchService {

    @Resource
    private InspectRecArchMapper inspectRecArchMapper;

    @Override
    public Long createInspectRecArch(InspectRecArchSaveReqVO createReqVO) {
        // 插入
        InspectRecArchDO inspectRecArch = BeanUtils.toBean(createReqVO, InspectRecArchDO.class);
        inspectRecArchMapper.insert(inspectRecArch);
        // 返回
        return inspectRecArch.getId();
    }

    @Override
    public void updateInspectRecArch(InspectRecArchSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRecArchExists(updateReqVO.getId());
        // 更新
        InspectRecArchDO updateObj = BeanUtils.toBean(updateReqVO, InspectRecArchDO.class);
        inspectRecArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRecArch(Long id) {
        // 校验存在
        validateInspectRecArchExists(id);
        // 删除
        inspectRecArchMapper.deleteById(id);
    }

    private void validateInspectRecArchExists(Long id) {
        if (inspectRecArchMapper.selectById(id) == null) {
            throw exception(INSPECT_REC_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public InspectRecArchDO getInspectRecArch(Long id) {
        return inspectRecArchMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRecArchDO> getInspectRecArchPage(InspectRecArchPageReqVO pageReqVO) {
        return inspectRecArchMapper.selectPage(pageReqVO);
    }

}