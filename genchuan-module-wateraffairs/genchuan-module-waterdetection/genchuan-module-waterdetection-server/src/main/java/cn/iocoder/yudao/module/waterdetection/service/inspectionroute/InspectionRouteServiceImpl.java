package cn.iocoder.yudao.module.waterdetection.service.inspectionroute;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectionroute.InspectionRouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.inspectionroute.InspectionRouteMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 巡检路线规划与优化 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectionRouteServiceImpl implements InspectionRouteService {

    @Resource
    private InspectionRouteMapper inspectionRouteMapper;

    @Override
    public Long createInspectionRoute(InspectionRouteSaveReqVO createReqVO) {
        // 插入
        InspectionRouteDO inspectionRoute = BeanUtils.toBean(createReqVO, InspectionRouteDO.class);
        inspectionRouteMapper.insert(inspectionRoute);
        // 返回
        return inspectionRoute.getId();
    }

    @Override
    public void updateInspectionRoute(InspectionRouteSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionRouteExists(updateReqVO.getId());
        // 更新
        InspectionRouteDO updateObj = BeanUtils.toBean(updateReqVO, InspectionRouteDO.class);
        inspectionRouteMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionRoute(Long id) {
        // 校验存在
        validateInspectionRouteExists(id);
        // 删除
        inspectionRouteMapper.deleteById(id);
    }

    private void validateInspectionRouteExists(Long id) {
        if (inspectionRouteMapper.selectById(id) == null) {
            throw exception(INSPECTION_ROUTE_NOT_EXISTS);
        }
    }

    @Override
    public InspectionRouteDO getInspectionRoute(Long id) {
        return inspectionRouteMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionRouteDO> getInspectionRoutePage(InspectionRoutePageReqVO pageReqVO) {
        return inspectionRouteMapper.selectPage(pageReqVO);
    }

}