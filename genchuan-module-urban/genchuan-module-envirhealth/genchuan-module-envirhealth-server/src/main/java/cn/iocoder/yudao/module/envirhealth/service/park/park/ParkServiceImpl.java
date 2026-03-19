package cn.iocoder.yudao.module.envirhealth.service.park.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.ParkDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.ParkSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.park.ParkMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.park.ParkCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PARK_NOT_EXISTS;

/**
 * 公园 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ParkServiceImpl implements ParkService {

    @Resource
    private ParkMapper parkMapper;

    @Resource
    private ParkCodeGenerator codeGenerator;

    @Override
    public Long createPark(ParkSaveReqVO createReqVO) {
        // 插入
        ParkDO park = BeanUtils.toBean(createReqVO, ParkDO.class);

        park.setParkId(codeGenerator.generateParkId());

        parkMapper.insert(park);
        // 返回
        return park.getId();
    }

    @Override
    public void updatePark(ParkSaveReqVO updateReqVO) {
        // 校验存在
        validateParkExists(updateReqVO.getId());
        // 更新
        ParkDO updateObj = BeanUtils.toBean(updateReqVO, ParkDO.class);
        parkMapper.updateById(updateObj);
    }

    @Override
    public void deletePark(Long id) {
        // 校验存在
        validateParkExists(id);
        // 删除
        parkMapper.deleteById(id);
    }

    private void validateParkExists(Long id) {
        if (parkMapper.selectById(id) == null) {
            throw exception(PARK_NOT_EXISTS);
        }
    }

    @Override
    public ParkDO getPark(Long id) {
        return parkMapper.selectById(id);
    }

    @Override
    public PageResult<ParkDO> getParkPage(ParkPageReqVO pageReqVO) {
        return parkMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ParkDetailDO> getParkDetailPage(ParkPageReqVO pageReqVO) {
        Long total = parkMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ParkDetailDO> list = parkMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public ParkDashboardVO getParkDashboardDashboard() {
        ParkDashboardVO vo = new ParkDashboardVO();

        // 1. 卡片数据
        vo.setTotalParkCount(parkMapper.selectTotalParkCount());
        vo.setNormalOperationCount(parkMapper.selectNormalOperationCount());
        vo.setCleaningStandardMetCount(parkMapper.selectCleaningStandardMetCount());
        vo.setGreeningSurvivalStandardMetCount(parkMapper.selectGreeningSurvivalStandardMetCount());
        vo.setFacilityIntactCount(parkMapper.selectFacilityIntactCount());

        // 2. 圆环图数据
        vo.setOperationStatusDistribution(parkMapper.selectOperationStatusDistribution());
        vo.setAreaDistribution(parkMapper.selectAreaDistribution());

        // 3. 柱状图数据
        vo.setEnvironmentComplianceRateByPark(parkMapper.selectEnvironmentComplianceRateByPark());

        return vo;
    }
}