package cn.iocoder.yudao.module.system.service.riskcontrol;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.riskcontrol.RiskControlDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 风险管控 Service 接口
 *
 * @author zhucongquan
 */
public interface RiskControlService {

    /**
     * 创建风险管控
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createRiskControl(@Valid RiskControlSaveReqVO createReqVO);

    /**
     * 更新风险管控
     *
     * @param updateReqVO 更新信息
     */
    void updateRiskControl(@Valid RiskControlSaveReqVO updateReqVO);

    /**
     * 删除风险管控
     *
     * @param id 编号
     */
    void deleteRiskControl(Integer id);

    /**
     * 获得风险管控
     *
     * @param id 编号
     * @return 风险管控
     */
    RiskControlDO getRiskControl(Integer id);

    /**
     * 获得风险管控分页
     *
     * @param pageReqVO 分页查询
     * @return 风险管控分页
     */
    PageResult<RiskControlDO> getRiskControlPage(RiskControlPageReqVO pageReqVO);

}