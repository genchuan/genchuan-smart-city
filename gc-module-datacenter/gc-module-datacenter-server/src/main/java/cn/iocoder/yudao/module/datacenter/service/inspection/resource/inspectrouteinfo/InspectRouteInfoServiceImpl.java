package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectrouteinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectrouteinfo.InspectRouteInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectrouteinfo.InspectRouteInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检路线信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRouteInfoServiceImpl implements InspectRouteInfoService {

    @Resource
    private InspectRouteInfoMapper inspectRouteInfoMapper;

    @Override
    public Long createInspectRouteInfo(InspectRouteInfoSaveReqVO createReqVO) {
        // 插入
        InspectRouteInfoDO inspectRouteInfo = BeanUtils.toBean(createReqVO, InspectRouteInfoDO.class);
        inspectRouteInfoMapper.insert(inspectRouteInfo);
        // 返回
        return inspectRouteInfo.getId();
    }

    @Override
    public void updateInspectRouteInfo(InspectRouteInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRouteInfoExists(updateReqVO.getId());
        // 更新
        InspectRouteInfoDO updateObj = BeanUtils.toBean(updateReqVO, InspectRouteInfoDO.class);
        inspectRouteInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRouteInfo(Long id) {
        // 校验存在
        validateInspectRouteInfoExists(id);
        // 删除
        inspectRouteInfoMapper.deleteById(id);
    }

    private void validateInspectRouteInfoExists(Long id) {
        if (inspectRouteInfoMapper.selectById(id) == null) {
            throw exception(INSPECT_ROUTE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public InspectRouteInfoDO getInspectRouteInfo(Long id) {
        return inspectRouteInfoMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRouteInfoDO> getInspectRouteInfoPage(InspectRouteInfoPageReqVO pageReqVO) {
        return inspectRouteInfoMapper.selectPage(pageReqVO);
    }

}