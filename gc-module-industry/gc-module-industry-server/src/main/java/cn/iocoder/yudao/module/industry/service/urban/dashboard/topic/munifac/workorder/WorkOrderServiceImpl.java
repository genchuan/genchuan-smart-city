package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.workorder;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.workorder.WorkOrderMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

/**
 * 市政设施专题-派发工单 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 WorkOrderService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class WorkOrderServiceImpl implements WorkOrderService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private WorkOrderMapper workOrderMapper;

    /**
     * 查询市政设施专题-派发工单数据
     *
     * @param workOrderQueryReqVO 查询条件 VO 对象
     * @return WorkOrderRespVO 查询结果 VO 对象
     */
    @Override
    public WorkOrderRespVO getWorkOrder(WorkOrderQueryReqVO workOrderQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return workOrderMapper.getWorkOrder(workOrderQueryReqVO);
    }

    @Override
    public Long createWorkOrder(WorkOrderCreateReqVO workOrderCreateReqVO) {
        //1.通过req的mng_comp_id获取对应 biz_mng_comp 表中，将其的字段：mng_comp_id、comp_name、dept_code构造成 mngCompResp
        Map<String, Object> compMap =
                workOrderMapper.selectCompInfoByMngCompId(workOrderCreateReqVO.getMngCompId());
        System.out.println("cs2025-12-08 15:59:17:"+compMap);

        if (compMap == null|| compMap.isEmpty()) {
            throw new RuntimeException("设施不存在，mng_comp_id=" + workOrderCreateReqVO.getMngCompId());
        }
        // 2. 如果返回的 Map 是 {MF-001={dept_code=..., comp_name=..., mng_comp_id=...}}
        Map<String, Object> mngCompResp = (Map<String, Object>) compMap.get(workOrderCreateReqVO.getMngCompId());

        if (mngCompResp == null|| mngCompResp.isEmpty()) {
            throw new RuntimeException("设施数据解析失败，mng_comp_id=" + workOrderCreateReqVO.getMngCompId());
        }

        // 2. 用 Map 构造要插入的工单数据
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put("mng_comp_id", mngCompResp.get("mng_comp_id"));
        entityMap.put("comp_name", mngCompResp.get("comp_name"));
        entityMap.put("dept_code", mngCompResp.get("dept_code"));
        entityMap.put("wo_code", "WO-" + System.currentTimeMillis()); // 工单编号
        entityMap.put("status", 0); // 默认未开始

        // 3. 插入数据库（Mapper 需要写对应方法支持 Map 插入）
        workOrderMapper.insertByMap(entityMap);

        // 4. 返回自增主键（如果 Mapper 支持返回主键的话）
        Number id = (Number) entityMap.get("id");
        return id != null ? id.longValue() : null;

//        return null;
    }
}
