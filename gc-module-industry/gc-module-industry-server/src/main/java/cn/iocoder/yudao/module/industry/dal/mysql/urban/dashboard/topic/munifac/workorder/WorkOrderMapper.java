package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.workorder;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 市政设施专题-派发工单 Mapper
 *
 */
@Mapper
public interface WorkOrderMapper {

    /**
     * 查询市政设施专题-派发工单
     *
     * @param workOrderQueryReqVO 查询参数
     * @return WorkOrderRespVO 结果
     */
    WorkOrderRespVO getWorkOrder(WorkOrderQueryReqVO workOrderQueryReqVO);

    @MapKey("mng_comp_id")
    Map<String, Object> selectCompInfoByMngCompId(String mngCompId);

    void insertByMap(Map<String, Object> entityMap);
}
