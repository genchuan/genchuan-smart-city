package cn.iocoder.yudao.module.waterdetection.service.leakagecontrolplan;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.leakagecontrolplan.LeakageControlPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 漏损控制方案建议 Service 接口
 *
 * @author zcq
 */
public interface LeakageControlPlanService {

    /**
     * 创建漏损控制方案建议
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLeakageControlPlan(@Valid LeakageControlPlanSaveReqVO createReqVO);

    /**
     * 更新漏损控制方案建议
     *
     * @param updateReqVO 更新信息
     */
    void updateLeakageControlPlan(@Valid LeakageControlPlanSaveReqVO updateReqVO);

    /**
     * 删除漏损控制方案建议
     *
     * @param id 编号
     */
    void deleteLeakageControlPlan(Long id);

    /**
     * 获得漏损控制方案建议
     *
     * @param id 编号
     * @return 漏损控制方案建议
     */
    LeakageControlPlanDO getLeakageControlPlan(Long id);

    /**
     * 获得漏损控制方案建议分页
     *
     * @param pageReqVO 分页查询
     * @return 漏损控制方案建议分页
     */
    PageResult<LeakageControlPlanDO> getLeakageControlPlanPage(LeakageControlPlanPageReqVO pageReqVO);

}