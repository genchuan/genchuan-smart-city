package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.feerule;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRuleSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.AddFeeRuleReqVO;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.feerule.FeeRuleMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.FEE_RULE_NOT_EXISTS;

/**
 * 收费规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FeeRuleServiceImpl implements FeeRuleService {

    @Resource
    private FeeRuleMapper feeRuleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public FeeRuleChartRespVO getFeeRuleChart() {
        // 1. 查询卡片数据（一定保证不为 null）

        FeeRuleChartRespVO resp = new FeeRuleChartRespVO();

        // 1. 查询卡片统计（单独查，不嵌套VO，绝对不报错）
        FeeRuleChartRespVO.CardDataVO cardData = feeRuleMapper.selectCardData();
        if (cardData == null) {
            cardData = new FeeRuleChartRespVO.CardDataVO();
            cardData.setEnableRuleCount(0);
            cardData.setTotalMatchRate(BigDecimal.ZERO);
        }
        resp.setCardData(cardData);

        // 2. 查询柱状图
        List<FeeRuleChartRespVO.StationBarVO> barList = feeRuleMapper.selectStationBarList();
        resp.setStationBarList(barList);

        return resp;
    }

    @Override
    public FeeRuleDO getFeeRule(Long id) {
        return feeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<FeeRuleDO> getFeeRulePage(FeeRulePageReqVO pageReqVO) {
        return feeRuleMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableFeeRule(List<Long> ids) {
        feeRuleMapper.update(new LambdaUpdateWrapper<FeeRuleDO>()
                .in(FeeRuleDO::getId, ids)
                .set(FeeRuleDO::getStatus, "已生效")
                .set(FeeRuleDO::getAuditTime, LocalDateTime.now())
                .set(FeeRuleDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableFeeRule(List<Long> ids) {
        feeRuleMapper.update(new LambdaUpdateWrapper<FeeRuleDO>()
                .in(FeeRuleDO::getId, ids)
                .set(FeeRuleDO::getStatus, "已禁用")
                .set(FeeRuleDO::getAuditTime, LocalDateTime.now())
                .set(FeeRuleDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    //========================== 公共抽取方法：全类复用 ==============================

    /**
     * 校验收费规则是否存在
     */
    private FeeRuleDO validateFeeRuleDOExists(Long id) {
        FeeRuleDO feeRule = feeRuleMapper.selectById(id);
        if (feeRule == null) {
            throw exception("收费规则不存在");
        }
        return feeRule;
    }

    /**
     * 根据【场站ID + 费率类型】查询收费规则列表 → 用于【唯一性校验】
     * 业务约束：同一个场站、同一个费率类型，只允许存在 1 条未删除数据
     * 使用 LIMIT 2 是为了：
     * 1. 判断是否存在数据
     * 2. 判断是否重复（>=1 条代表重复）
     * 3. 避免数据异常时查出大量数据导致性能问题
     *
     * @return 最多 2 条数据
     */
    private List<FeeRuleDO> listFeeRuleForUniqueCheck(Long stationId, String rateType) {
        return feeRuleMapper.selectList(new LambdaQueryWrapper<FeeRuleDO>()
                .eq(FeeRuleDO::getStationId, stationId)
                .eq(FeeRuleDO::getRateType, rateType)
                .eq(FeeRuleDO::getDeleted, 0)
                .last("LIMIT 2")
        );
    }
    /**
     * 校验收费规则唯一性（新增/更新通用）
     * @param stationId 场站ID
     * @param rateType 费率类型
     * @param excludeId 排除的ID（更新时传自身ID，新增传 null）
     */
    private void validateFeeRuleUnique(Long stationId, String rateType, Long excludeId) {
        // 1. 复用公共查询方法，获取 场站+费率类型 的规则列表（最多2条）
        List<FeeRuleDO> ruleList = listFeeRuleForUniqueCheck(stationId, rateType);

        // ====================== 【多条一定是错的】 ======================
        if (ruleList.size() > 1) {
            throw exception("数据异常：该场站+费率类型存在多条重复收费规则，请先清理数据！");
        }

        // 2. 校验是否重复
        boolean isDuplicate = ruleList.stream()
                .anyMatch(rule -> {
                    // 情况1：新增场景 → 只要有数据就是重复
                    if (excludeId == null) {
                        return true;
                    }
                    // 情况2：更新场景 → 不是自己，才是重复
                    return !rule.getId().equals(excludeId);
                });

        // 3. 重复则抛异常
        if (isDuplicate) {
            throw exception("该场站+费率类型已存在收费规则，不可重复添加");
        }
    }
    //==========================新增=====================================================

    @Override
    public void addFeeRule(AddFeeRuleReqVO reqVO) {
        // ====================== 【关键】唯一性校验 ======================
        // 同一个场站 + 同一种费率类型 → 只能有一条规则
        validateFeeRuleUnique(reqVO.getStationId(),reqVO.getRateType(),null);

        // ====================== Bean 转换 ======================
        FeeRuleDO feeRule = BeanUtils.toBean(reqVO, FeeRuleDO.class);

        // ====================== 自动设置默认值 ======================
        feeRule.setStatus("待生效");          // 【需求】默认状态：待生效
        feeRule.setMatchRate(BigDecimal.ZERO); // 默认匹配率 0.00

        // ====================== 插入 ======================
        feeRuleMapper.insert(feeRule);
    }

    //==========================更新=====================================================

    @Override
    public void updateFeeRuleBiz(FeeRuleUpdateReqVO updateReqVO) {
        // 1. 校验存在
        FeeRuleDO oldRule = validateFeeRuleDOExists(updateReqVO.getId());

        // 2. 唯一性校验：同一个场站 + 同一种费率类型 不能重复
        validateFeeRuleUnique(updateReqVO.getStationId(),updateReqVO.getRateType(),oldRule.getId());
        // 3. 对象转换
        FeeRuleDO updateBean = BeanUtils.toBean(updateReqVO, FeeRuleDO.class);

        // 5. 执行更新
        feeRuleMapper.updateById(updateBean);
    }

    //==========================导入=====================================================
    @Override
    public FeeRuleImportResp importFeeRule(MultipartFile file, boolean updateSupport) {
        FeeRuleImportResp resp = new FeeRuleImportResp();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // Excel 解析
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    AddFeeRuleReqVO.class.getName()
            );

            List<AddFeeRuleReqVO> reqList = (List<AddFeeRuleReqVO>) resultMap.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<FeeRuleImportResp.ImportFailure> failures = new ArrayList<>();

            // 遍历导入
            for (int i = 0; i < reqList.size(); i++) {
                AddFeeRuleReqVO req = reqList.get(i);
                int rowIndex = i + 2;

                try {
                    if (updateSupport) {
                        updateFeeRuleIfExists(req); // 存在更新，不存在新增
                    } else {
                        addFeeRule(req); // 仅新增
                    }
                    successCount++;

                } catch (Exception e) {
                    FeeRuleImportResp.ImportFailure failure = new FeeRuleImportResp.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            FeeRuleImportResp.ImportFailure failure = new FeeRuleImportResp.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }
    // ====================== 存在则更新，不存在则新增 ======================
    private void updateFeeRuleIfExists(AddFeeRuleReqVO req) {
        // 1. 复用公共查询方法，获取同场站+同费率类型的规则（最多2条）
        List<FeeRuleDO> ruleList = listFeeRuleForUniqueCheck(req.getStationId(), req.getRateType());

        // ====================== 【关键】校验数据唯一性 ======================
        // 如果查出 >=2 条，说明数据库脏数据、重复了 → 直接报错！
        if (ruleList.size() > 1) {
            throw exception("数据异常：该场站+费率类型存在多条重复收费规则，请先清理数据！");
        }

        // 2. 获取单条数据（0 条或 1 条）
        FeeRuleDO existing = ruleList.stream().findFirst().orElse(null);

        if (existing == null) {
            // 不存在 → 新增（自带唯一性校验）
            addFeeRule(req);
        } else {
            // 存在 → 转换成更新VO
            FeeRuleUpdateReqVO update = BeanUtils.toBean(req, FeeRuleUpdateReqVO.class);
            update.setId(existing.getId());

            // 调用原有更新方法（自带唯一性校验）
            updateFeeRuleBiz(update);
        }
    }





}
