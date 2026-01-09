package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac;



import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.MuniFacMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 市政设施专题 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MuniFacServiceImpl implements MuniFacService {

    @Resource
    private MuniFacMapper muniFacMapper;

    @Override
    public MuniFacRespVO getMuniFac(MuniFacQueryReqVO muniFacQueryReqVO) {
            return muniFacMapper.getMuniFac(muniFacQueryReqVO);
    }

    @Override
    public FacOverviewRespVO getMuniFacOverview(FacOverviewReqVO facOverviewReqVO) {

        // ====== 这里设置隐藏字段（Swagger 不展示，但业务能用）======
        // 查询市政设施对应大类
        facOverviewReqVO.setMuniFacMajorList(Arrays.asList("01"));

        // 紧急故障对应大类
        facOverviewReqVO.setEmergencyMajorList(Arrays.asList("01"));

        // 紧急故障对应小类
        facOverviewReqVO.setEmergencyMinorList(Arrays.asList("01"));
        // =======================================================



        // 构建返回对象
        FacOverviewRespVO respVO = new FacOverviewRespVO();

        // 1. 紧急故障数
        Integer emergencyCount = muniFacMapper.getEmergencyCount(facOverviewReqVO);
        respVO.setEmergencyCount(emergencyCount != null ? emergencyCount : 0);

        // 2. 一般故障数
        Integer normalCount = muniFacMapper.getNormalCount(facOverviewReqVO);
        respVO.setNormalCount(normalCount != null ? normalCount : 0);

        // 3. 总故障数
        Integer totalFault = muniFacMapper.getTotalFault(facOverviewReqVO);
        respVO.setTotalFault(totalFault != null ? totalFault : 0);

        // 4. 已处置数
        Integer handledCount = muniFacMapper.getHandledCount(facOverviewReqVO);
        respVO.setHandledCount(handledCount != null ? handledCount : 0);

        // 5. 处置率（%）
        BigDecimal handlingRate = muniFacMapper.getHandlingRate(facOverviewReqVO);
        respVO.setHandlingRate(handlingRate != null ? handlingRate : BigDecimal.ZERO);

        // 6. 超时未处置数
        Integer timeoutCount = muniFacMapper.getTimeoutCount(facOverviewReqVO);
        respVO.setTimeoutCount(timeoutCount != null ? timeoutCount : 0);


        return respVO;
    }

}
