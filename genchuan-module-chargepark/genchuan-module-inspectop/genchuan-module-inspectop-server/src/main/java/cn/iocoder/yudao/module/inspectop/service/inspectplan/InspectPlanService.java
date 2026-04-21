package cn.iocoder.yudao.module.inspectop.service.inspectplan;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectplan.InspectPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 巡检计划 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectPlanService {

    /**
     * 创建巡检计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectPlan(@Valid InspectPlanSaveReqVO createReqVO);

    /**
     * 更新巡检计划
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectPlan(@Valid InspectPlanSaveReqVO updateReqVO);

    /**
     * 删除巡检计划
     *
     * @param id 编号
     */
    void deleteInspectPlan(Long id);

    /**
    * 批量删除巡检计划
    *
    * @param ids 编号
    */
    void deleteInspectPlanListByIds(List<Long> ids);

    /**
     * 获得巡检计划
     *
     * @param id 编号
     * @return 巡检计划
     */
    InspectPlanDO getInspectPlan(Long id);

    /**
     * 获得巡检计划分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检计划分页
     */
    PageResult<InspectPlanDO> getInspectPlanPage(InspectPlanPageReqVO pageReqVO);

    /**
     * 导入巡检计划
     *
     * @param file Excel文件
     * @param updateSupport 是否更新已存在的数据
     * @return 导入结果
     */
    ImportRespVO importInspectPlan(MultipartFile file, boolean updateSupport);

    /**
     * 更新巡检计划状态
     *
     * @param id 计划ID
     * @param status 状态值
     */
    void updateInspectPlanStatus(Long id, String status);

    /**
     * 获得巡检计划图表统计
     *
     * @param reqVO 查询参数
     * @return 图表统计结果
     */
    InspectPlanChartRespVO getInspectPlanChart(InspectPlanChartReqVO reqVO);
}