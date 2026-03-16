package cn.iocoder.yudao.module.envirhealth.service.publictoilet.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.*;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.publictoilet.PublicToiletCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.StatisticsRespVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_TOILET_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_TOILET_NOT_EXISTS;

/**
 * 公厕 Service 实现类
 */
@Service
@Validated
public class PublicToiletServiceImpl implements PublicToiletService {

    @Resource
    private PublicToiletMapper publicToiletMapper;

    @Resource
    private ToiletComplaintMapper toiletComplaintMapper;

    @Resource
    private ToiletFacilityRepairMapper toiletFacilityRepairMapper;

    @Resource
    private ToiletCleaningTaskMapper toiletCleaningTaskMapper;

    @Resource
    private ToiletConsumableMapper toiletConsumableMapper;

    @Resource
    private PublicToiletCodeGenerator codeGenerator;

    @Override
    public Long createPublicToilet(PublicToiletSaveReqVO createReqVO) {
        // 1. 检查名称是否重复
        checkNameUnique(createReqVO.getName(), null);

        // 2. 插入
        PublicToiletDO publicToilet = BeanUtils.toBean(createReqVO, PublicToiletDO.class);

        // 3. 自动生成toilet_id(覆盖手动填写的)
        publicToilet.setToiletId(codeGenerator.generateToiletId());

        publicToiletMapper.insert(publicToilet);
        return publicToilet.getId();
    }

    @Override
    public void updatePublicToilet(PublicToiletSaveReqVO updateReqVO) {
        // 1. 检查名称是否重复（排除自身）
        checkNameUnique(updateReqVO.getName(), updateReqVO.getId());

        // 2. 校验存在
        validatePublicToiletExists(updateReqVO.getId());

        // 3. 更新
        PublicToiletDO updateObj = BeanUtils.toBean(updateReqVO, PublicToiletDO.class);
        publicToiletMapper.updateById(updateObj);
    }

    /**
     * 检查公厕名称是否唯一
     */
    private void checkNameUnique(String name, Long id) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        LambdaQueryWrapperX<PublicToiletDO> query = new LambdaQueryWrapperX<PublicToiletDO>()
                .eq(PublicToiletDO::getName, name)
                .eq(PublicToiletDO::getDeleted, false);

        if (id != null) {
            query.ne(PublicToiletDO::getId, id);
        }

        PublicToiletDO existing = publicToiletMapper.selectOne(query);
        if (existing != null) {
            throw exception(PUBLIC_TOILET_NAME_DUPLICATE);
        }
    }

    @Override
    public void deletePublicToilet(Long id) {
        validatePublicToiletExists(id);
        publicToiletMapper.deleteById(id);
    }

    @Override
    public void deletePublicToiletBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<PublicToiletDO> publicToilets = publicToiletMapper.selectBatchIds(ids);
        if (publicToilets.size() != ids.size()) {
            throw exception(PUBLIC_TOILET_NOT_EXISTS);
        }

        publicToiletMapper.deleteBatchIds(ids);
    }

    private void validatePublicToiletExists(Long id) {
        if (publicToiletMapper.selectById(id) == null) {
            throw exception(PUBLIC_TOILET_NOT_EXISTS);
        }
    }

    @Override
    public PublicToiletDO getPublicToilet(Long id) {
        return publicToiletMapper.selectById(id);
    }

    @Override
    public PageResult<PublicToiletDO> getPublicToiletPage(PublicToiletPageReqVO pageReqVO) {
        return publicToiletMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PublicToiletDetailDO> getPublicToiletDetailPage(PublicToiletPageReqVO pageReqVO) {
        Long total = publicToiletMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<PublicToiletDetailDO> list = publicToiletMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<OptionVO> getPublicToiletNameOptions() {
        return publicToiletMapper.selectToiletOptions();
    }

    @Override
    public StatisticsRespVO getPublicToiletStatistics() {
        StatisticsRespVO respVO = new StatisticsRespVO();

        // 1. 查询公厕总数量
        Long totalCount = publicToiletMapper.selectTotalCount();
        respVO.setTotal(totalCount == null ? 0 : totalCount.intValue());

        // 2. 查询公厕计划状态统计
        List<Map<String, Object>> statusStats = toiletCleaningTaskMapper.selectStatisticsByPlanStatus();

        // 3. 转换为Map格式
        Map<String, Integer> planStatusCounts = new LinkedHashMap<>();

        // 4. 填充公厕计划的状态数据
        for (Map<String, Object> stat : statusStats) {
            String statusName = (String) stat.get("status_name");
            Long count = (Long) stat.get("count");
            planStatusCounts.put(statusName, count.intValue());
        }

        // 5. 查询投诉表数据，填充投诉待处置
        Long complaintPendingCount = toiletComplaintMapper.countPendingDisposal();
        planStatusCounts.put("投诉待处置", complaintPendingCount == null ? 0 : complaintPendingCount.intValue());

        // 6. 查询设施维修表数据，填充待维修
        Long repairPendingCount = toiletFacilityRepairMapper.countPendingRepair();
        planStatusCounts.put("设施待维修", repairPendingCount == null ? 0 : repairPendingCount.intValue());

        // 7. 物资待补充
        Long consumableCount = toiletConsumableMapper.countConsumable();
        planStatusCounts.put("物资待补充", consumableCount == null ? 0 : consumableCount.intValue());

        respVO.setPlanStatusCounts(planStatusCounts);
        return respVO;
    }

    @Override
    public PublicToiletCardAllVO getPublicToiletCardAll() {
        PublicToiletCardAllVO statsVO = new PublicToiletCardAllVO();

        // 1. 总公厕数
        Long totalCount = publicToiletMapper.selectTotalCount();
        statsVO.setTotalToiletCount(totalCount == null ? 0 : totalCount.intValue());

        // 2. 正常运营数
        Integer normalOpCount = publicToiletMapper.countNormalOperation();
        statsVO.setNormalOperationCount(normalOpCount == null ? 0 : normalOpCount);

        // 3. 保洁达标数（假设≥95%为达标）
        Integer cleaningQualifiedCount = publicToiletMapper.countCleaningQualified();
        statsVO.setCleaningQualifiedCount(cleaningQualifiedCount == null ? 0 : cleaningQualifiedCount);

        // 4. 无投诉数
        Integer noComplaintCount = toiletComplaintMapper.countToiletWithNoPendingComplaint();
        statsVO.setNoComplaintCount(noComplaintCount == null ? 0 : noComplaintCount);

        return statsVO;
    }

    @Override
    public PublicToiletPieChartsRespVO getPublicToiletPieCharts() {
        PublicToiletPieChartsRespVO resp = new PublicToiletPieChartsRespVO();
        resp.setOperationStatus(publicToiletMapper.selectOperationStatusPie());
        resp.setAreaDistribution(publicToiletMapper.selectAreaDistributionPie());
        return resp;
    }

    @Override
    public List<BarItemVO> getCleaningQualifiedRateByArea() {
        return publicToiletMapper.selectCleaningQualifiedRateByArea();
    }

    @Override
    public PublicToiletAllRespVO getAll() {
        PublicToiletAllRespVO resp = new PublicToiletAllRespVO();
        resp.setCard(getPublicToiletCardAll());
        resp.setPie(getPublicToiletPieCharts());
        resp.setCleaningQualifiedRateByArea(getCleaningQualifiedRateByArea());
        return resp;
    }
}