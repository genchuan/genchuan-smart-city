package cn.iocoder.yudao.module.envirhealth.service.river.river;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ToolDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.ToolMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.RiverMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.UserMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.river.RiverCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.json.JsonIdToNameUtil;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.RIVER_NOT_EXISTS;

/**
 * 河道 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RiverServiceImpl implements RiverService {

    @Resource
    private RiverMapper riverMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ToolMapper toolMapper;

    @Resource
    private RiverCodeGenerator codeGenerator;

    @Override
    public Long createRiver(RiverSaveReqVO createReqVO) {
        // 插入
        RiverDO river = BeanUtils.toBean(createReqVO, RiverDO.class);

        river.setId(null);
        river.setRiverId(codeGenerator.generateRiverId());

        riverMapper.insert(river);
        // 返回
        return river.getId();
    }

    @Override
    public void updateRiver(RiverSaveReqVO updateReqVO) {
        // 校验存在
        validateRiverExists(updateReqVO.getId());
        // 更新
        RiverDO updateObj = BeanUtils.toBean(updateReqVO, RiverDO.class);
        riverMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiver(Long id) {
        // 校验存在
        validateRiverExists(id);
        // 删除
        riverMapper.deleteById(id);
    }

    private void validateRiverExists(Long id) {
        if (riverMapper.selectById(id) == null) {
            throw exception(RIVER_NOT_EXISTS);
        }
    }

    @Override
    public RiverDO getRiver(Long id) {
        return riverMapper.selectById(id);
    }

    @Override
    public PageResult<RiverDO> getRiverPage(RiverPageReqVO pageReqVO) {
        return riverMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RiverDetailDO> getRiverDetailPage(RiverPageReqVO pageReqVO) {
        Long total = riverMapper.selectCount(pageReqVO);
        if (total == 0) return PageResult.empty();

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        List<RiverDetailDO> list = riverMapper.selectDetailPage(pageReqVO);

        // 1. 处理人员
        Set<String> staffIds = JsonIdToNameUtil.collectIds(list, RiverDetailDO::getStaffIds);
        Map<String, String> staffMap = JsonIdToNameUtil.toMap(
                userMapper.selectList(new LambdaQueryWrapperX<UserDO>()
                        .in(UserDO::getUserId, staffIds)
                        .eq(UserDO::getDeleted, 0)),
                UserDO::getUserId,
                UserDO::getUserName
        );
        JsonIdToNameUtil.fillNames(list, RiverDetailDO::getStaffIds, RiverDetailDO::setStaffsNameStr, staffMap);

        // 2. 处理工具
        Set<String> toolIds = JsonIdToNameUtil.collectIds(list, RiverDetailDO::getToolIds);
        Map<String, String> toolMap = JsonIdToNameUtil.toMap(
                toolMapper.selectList(new LambdaQueryWrapperX<ToolDO>()
                        .in(ToolDO::getSysToolId, toolIds)
                        .eq(ToolDO::getDeleted, 0)),
                ToolDO::getSysToolId,
                ToolDO::getName
        );
        JsonIdToNameUtil.fillNames(list, RiverDetailDO::getToolIds, RiverDetailDO::setToolsNameStr, toolMap);

        return new PageResult<>(list, total);
    }

    @Override
    public RiverDashboardVO getRiverDashboard() {
        RiverDashboardVO vo = new RiverDashboardVO();

        // 1. 卡片数据
        vo.setTotalRiverCount(riverMapper.selectTotalRiverCount());
        vo.setCleaningCoverageMetCount(riverMapper.selectCleaningCoverageMetCount());
        vo.setWaterQualityMetCount(riverMapper.selectWaterQualityMetCount());
        vo.setProblemCompletedCount(riverMapper.selectProblemCompletedCount());

        // 2. 圆环图数据
        vo.setAreaDistribution(riverMapper.selectAreaDistribution());
        vo.setOperationStatusDistribution(riverMapper.selectOperationStatusDistribution());

        // 3. 柱状图数据
        vo.setWaterQualityRateByRiver(riverMapper.selectWaterQualityRateByRiver());

        return vo;
    }
}