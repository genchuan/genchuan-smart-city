package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectpersoninfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectpersoninfo.InspectPersonInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectpersoninfo.InspectPersonInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检人员信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectPersonInfoServiceImpl implements InspectPersonInfoService {

    @Resource
    private InspectPersonInfoMapper inspectPersonInfoMapper;

    @Override
    public Long createInspectPersonInfo(InspectPersonInfoSaveReqVO createReqVO) {
        // 插入
        InspectPersonInfoDO inspectPersonInfo = BeanUtils.toBean(createReqVO, InspectPersonInfoDO.class);
        inspectPersonInfoMapper.insert(inspectPersonInfo);
        // 返回
        return inspectPersonInfo.getId();
    }

    @Override
    public void updateInspectPersonInfo(InspectPersonInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectPersonInfoExists(updateReqVO.getId());
        // 更新
        InspectPersonInfoDO updateObj = BeanUtils.toBean(updateReqVO, InspectPersonInfoDO.class);
        inspectPersonInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectPersonInfo(Long id) {
        // 校验存在
        validateInspectPersonInfoExists(id);
        // 删除
        inspectPersonInfoMapper.deleteById(id);
    }

    private void validateInspectPersonInfoExists(Long id) {
        if (inspectPersonInfoMapper.selectById(id) == null) {
            throw exception(INSPECT_PERSON_INFO_NOT_EXISTS);
        }
    }

    @Override
    public InspectPersonInfoDO getInspectPersonInfo(Long id) {
        return inspectPersonInfoMapper.selectById(id);
    }

    @Override
    public PageResult<InspectPersonInfoDO> getInspectPersonInfoPage(InspectPersonInfoPageReqVO pageReqVO) {
        return inspectPersonInfoMapper.selectPage(pageReqVO);
    }

}