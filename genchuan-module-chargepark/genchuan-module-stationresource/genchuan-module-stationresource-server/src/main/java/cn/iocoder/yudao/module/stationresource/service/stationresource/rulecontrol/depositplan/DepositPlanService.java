package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.depositplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DepositPlanService {

    DepositPlanDO getDepositPlan(Long id);

    PageResult<DepositPlanDO> getDepositPlanPage(DepositPlanPageReqVO pageReqVO);

    void createDepositPlan(DepositPlanCreateReqVO reqVO);

    DepositPlanImportResp importDepositPlan(MultipartFile file, boolean updateSupport);

    void updateDepositPlanBiz(DepositPlanUpdateReqVO updateReqVO);

    void enableDepositPlan(List<Long> ids);

    void disableDepositPlan(List<Long> ids);

    DepositPlanChartRespVO getDepositPlanChart();
}
