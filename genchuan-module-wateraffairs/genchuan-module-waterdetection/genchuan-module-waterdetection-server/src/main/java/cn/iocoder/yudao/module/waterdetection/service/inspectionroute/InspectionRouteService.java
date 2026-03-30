package cn.iocoder.yudao.module.waterdetection.service.inspectionroute;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectionroute.InspectionRouteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡检路线规划与优化 Service 接口
 *
 * @author zcq
 */
public interface InspectionRouteService {

    /**
     * 创建巡检路线规划与优化
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionRoute(@Valid InspectionRouteSaveReqVO createReqVO);

    /**
     * 更新巡检路线规划与优化
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionRoute(@Valid InspectionRouteSaveReqVO updateReqVO);

    /**
     * 删除巡检路线规划与优化
     *
     * @param id 编号
     */
    void deleteInspectionRoute(Long id);

    /**
     * 获得巡检路线规划与优化
     *
     * @param id 编号
     * @return 巡检路线规划与优化
     */
    InspectionRouteDO getInspectionRoute(Long id);

    /**
     * 获得巡检路线规划与优化分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检路线规划与优化分页
     */
    PageResult<InspectionRouteDO> getInspectionRoutePage(InspectionRoutePageReqVO pageReqVO);

}