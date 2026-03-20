package cn.iocoder.yudao.module.envirhealth.service.commercialstreet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.CommercialStreetDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.CommercialStreetPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.CommercialStreetSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.commercialstreet.CommercialStreetMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.commercialstreet.CommercialStreetCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.COMMERCIAL_STREET_NOT_EXISTS;

/**
 * 商业街 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CommercialStreetServiceImpl implements CommercialStreetService {

    @Resource
    private CommercialStreetMapper commercialStreetMapper;

    @Resource
    private CommercialStreetCodeGenerator codeGenerator;

    @Override
    public Long createCommercialStreet(CommercialStreetSaveReqVO createReqVO) {
        // 插入
        CommercialStreetDO commercialStreet = BeanUtils.toBean(createReqVO, CommercialStreetDO.class);

        commercialStreet.setStreetId(codeGenerator.generateStreetId());

        commercialStreetMapper.insert(commercialStreet);
        // 返回
        return commercialStreet.getId();
    }

    @Override
    public void updateCommercialStreet(CommercialStreetSaveReqVO updateReqVO) {
        // 校验存在
        validateCommercialStreetExists(updateReqVO.getId());
        // 更新
        CommercialStreetDO updateObj = BeanUtils.toBean(updateReqVO, CommercialStreetDO.class);
        commercialStreetMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommercialStreet(Long id) {
        // 校验存在
        validateCommercialStreetExists(id);
        // 删除
        commercialStreetMapper.deleteById(id);
    }

    private void validateCommercialStreetExists(Long id) {
        if (commercialStreetMapper.selectById(id) == null) {
            throw exception(COMMERCIAL_STREET_NOT_EXISTS);
        }
    }

    @Override
    public CommercialStreetDO getCommercialStreet(Long id) {
        return commercialStreetMapper.selectById(id);
    }

    @Override
    public PageResult<CommercialStreetDO> getCommercialStreetPage(CommercialStreetPageReqVO pageReqVO) {
        return commercialStreetMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CommercialStreetDetailDO> getCommercialStreetDetailPage(CommercialStreetPageReqVO pageReqVO) {
        Long total = commercialStreetMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<CommercialStreetDetailDO> list = commercialStreetMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public CommercialStreetDashboardRespVO getCommercialStreetDashboard() {
        CommercialStreetDashboardRespVO resp = new CommercialStreetDashboardRespVO();

        // 1. 卡片数据
        resp.setTotalStreetCount(commercialStreetMapper.selectTotalStreetCount());
        resp.setCleaningCoverageMetCount(commercialStreetMapper.selectCleaningCoverageMetCount());
        resp.setFacilityIntactCount(commercialStreetMapper.selectFacilityIntactCount());
        resp.setCollectionCompletedCount(commercialStreetMapper.selectCollectionCompletedCount());

        // 2. 圆环图数据
        resp.setAreaDistribution(commercialStreetMapper.selectAreaDistributionPie());
        resp.setOperationStatusDistribution(commercialStreetMapper.selectOperationStatusDistributionPie());

        // 3. 柱状图数据
        resp.setProblemDisposalDurationByStreet(commercialStreetMapper.selectProblemDisposalDurationByStreetBar());

        return resp;
    }
}