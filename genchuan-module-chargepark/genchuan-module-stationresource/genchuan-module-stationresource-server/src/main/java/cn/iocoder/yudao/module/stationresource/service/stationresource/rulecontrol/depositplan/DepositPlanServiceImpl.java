package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.depositplan;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.depositplan.DepositPlanMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
@Validated
public class DepositPlanServiceImpl implements DepositPlanService {

    @Resource
    private DepositPlanMapper depositPlanMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public DepositPlanChartRespVO getDepositPlanChart() {
        DepositPlanChartRespVO resp = new DepositPlanChartRespVO();
        // 查询卡片统计
        DepositPlanChartRespVO.CardDataVO cardData = depositPlanMapper.selectCardData();
        if (cardData == null) {
            cardData = new DepositPlanChartRespVO.CardDataVO();
            cardData.setEnablePlanCount(0);
            cardData.setTotalDepositOrderCount(0);
        }
        resp.setCardData(cardData);
        // 查询柱状图
        List<DepositPlanChartRespVO.SceneBarVO> barList = depositPlanMapper.selectSceneBarList();
        resp.setSceneBarList(barList);
        return resp;
    }

    @Override
    public DepositPlanDO getDepositPlan(Long id) {
        return depositPlanMapper.selectById(id);
    }

    @Override
    public PageResult<DepositPlanDO> getDepositPlanPage(DepositPlanPageReqVO pageReqVO) {
        return depositPlanMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableDepositPlan(List<Long> ids) {
        depositPlanMapper.update(new LambdaUpdateWrapper<DepositPlanDO>()
                .in(DepositPlanDO::getId, ids)
                .set(DepositPlanDO::getStatus, "已生效")
                .set(DepositPlanDO::getAuditTime, LocalDateTime.now())
                .set(DepositPlanDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableDepositPlan(List<Long> ids) {
        depositPlanMapper.update(new LambdaUpdateWrapper<DepositPlanDO>()
                .in(DepositPlanDO::getId, ids)
                .set(DepositPlanDO::getStatus, "已禁用")
                .set(DepositPlanDO::getAuditTime, LocalDateTime.now())
                .set(DepositPlanDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    private DepositPlanDO validateDepositPlanExists(Long id) {
        DepositPlanDO plan = depositPlanMapper.selectById(id);
        if (plan == null) {
            throw exception("押金方案不存在");
        }
        return plan;
    }

    private List<DepositPlanDO> listForUniqueCheck(Long stationId, String scene) {
        return depositPlanMapper.selectList(new LambdaQueryWrapper<DepositPlanDO>()
                .eq(DepositPlanDO::getStationId, stationId)
                .eq(DepositPlanDO::getScene, scene)
                .eq(DepositPlanDO::getDeleted, Boolean.FALSE)
                .last("LIMIT 2")
        );
    }

    private void validateUnique(Long stationId, String scene, Long excludeId) {
        List<DepositPlanDO> list = listForUniqueCheck(stationId, scene);
        if (list.size() > 1) {
            throw exception("数据异常：该场站+场景存在多条押金方案");
        }
        boolean duplicate = list.stream().anyMatch(plan -> {
            if (excludeId == null) {
                return true;
            }
            return !plan.getId().equals(excludeId);
        });
        if (duplicate) {
            throw exception("该场站+场景已存在押金方案，不可重复添加");
        }
    }

    @Override
    public void createDepositPlan(DepositPlanCreateReqVO reqVO) {
        validateUnique(reqVO.getStationId(), reqVO.getScene(), null);
        DepositPlanDO plan = BeanUtils.toBean(reqVO, DepositPlanDO.class);
        plan.setStatus("待生效");
        plan.setDepositOrderCount(0);
        depositPlanMapper.insert(plan);
    }

    @Override
    public void updateDepositPlanBiz(DepositPlanUpdateReqVO updateReqVO) {
        DepositPlanDO old = validateDepositPlanExists(updateReqVO.getId());
        validateUnique(updateReqVO.getStationId(), updateReqVO.getScene(), old.getId());
        DepositPlanDO updateBean = BeanUtils.toBean(updateReqVO, DepositPlanDO.class);
        depositPlanMapper.updateById(updateBean);
    }

    @Override
    public DepositPlanImportResp importDepositPlan(MultipartFile file, boolean updateSupport) {
        DepositPlanImportResp resp = new DepositPlanImportResp();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());
        try {
            Map<String, Object> map = VrvExcelUtils.importExcelAndReturnEntity(file, DepositPlanCreateReqVO.class.getName());
            List<DepositPlanCreateReqVO> reqList = (List<DepositPlanCreateReqVO>) map.get("entityList");
            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }
            int success = 0;
            List<DepositPlanImportResp.ImportFailure> failures = new ArrayList<>();
            for (int i = 0; i < reqList.size(); i++) {
                DepositPlanCreateReqVO req = reqList.get(i);
                int row = i + 2;
                try {
                    if (updateSupport) {
                        updateIfExists(req);
                    } else {
                        createDepositPlan(req);
                    }
                    success++;
                } catch (Exception e) {
                    DepositPlanImportResp.ImportFailure failure = new DepositPlanImportResp.ImportFailure();
                    failure.setRowIndex(row);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }
            resp.setSuccessCount(success);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;
        } catch (Exception e) {
            DepositPlanImportResp.ImportFailure failure = new DepositPlanImportResp.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    private void updateIfExists(DepositPlanCreateReqVO req) {
        List<DepositPlanDO> list = listForUniqueCheck(req.getStationId(), req.getScene());
        if (list.size() > 1) {
            throw exception("数据异常：该场站+场景存在多条押金方案");
        }
        DepositPlanDO exist = list.stream().findFirst().orElse(null);
        if (exist == null) {
            createDepositPlan(req);
        } else {
            DepositPlanUpdateReqVO update = BeanUtils.toBean(req, DepositPlanUpdateReqVO.class);
            update.setId(exist.getId());
            updateDepositPlanBiz(update);
        }
    }
}
