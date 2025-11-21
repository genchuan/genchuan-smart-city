package cn.iocoder.yudao.module.datacenter.service.inspection.inspectstdinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.inspectstdinfo.InspectStdInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.inspectstdinfo.InspectStdInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检标准信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectStdInfoServiceImpl implements InspectStdInfoService {

    @Resource
    private InspectStdInfoMapper inspectStdInfoMapper;

    @Override
    public Long createInspectStdInfo(InspectStdInfoSaveReqVO createReqVO) {
        // 插入
        InspectStdInfoDO inspectStdInfo = BeanUtils.toBean(createReqVO, InspectStdInfoDO.class);
        inspectStdInfoMapper.insert(inspectStdInfo);
        // 返回
        return inspectStdInfo.getId();
    }

    @Override
    public void updateInspectStdInfo(InspectStdInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectStdInfoExists(updateReqVO.getId());
        // 更新
        InspectStdInfoDO updateObj = BeanUtils.toBean(updateReqVO, InspectStdInfoDO.class);
        inspectStdInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectStdInfo(Long id) {
        // 校验存在
        validateInspectStdInfoExists(id);
        // 删除
        inspectStdInfoMapper.deleteById(id);
    }

    private void validateInspectStdInfoExists(Long id) {
        if (inspectStdInfoMapper.selectById(id) == null) {
            throw exception(INSPECT_STD_INFO_NOT_EXISTS);
        }
    }

    @Override
    public InspectStdInfoDO getInspectStdInfo(Long id) {
        return inspectStdInfoMapper.selectById(id);
    }

    @Override
    public PageResult<InspectStdInfoDO> getInspectStdInfoPage(InspectStdInfoPageReqVO pageReqVO) {
        return inspectStdInfoMapper.selectPage(pageReqVO);
    }

}