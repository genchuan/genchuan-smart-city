package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.unplateenter;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.string.StrUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterAuditReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter.UnplateEnterDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.unplateenter.UnplateEnterMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import java.math.BigDecimal;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.*;


/**
 * 无牌入场 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class UnplateEnterServiceImpl implements UnplateEnterService {

    @Resource
    private UnplateEnterMapper enterMapper;

    @Override
    public Long createEnter(UnplateEnterSaveReqVO createReqVO) {
        // 插入
        UnplateEnterDO enter = BeanUtils.toBean(createReqVO, UnplateEnterDO.class);
        enterMapper.insert(enter);

        // 返回
        return enter.getId();
    }

    @Override
    public void updateEnter(UnplateEnterSaveReqVO updateReqVO) {
        // 校验存在
        validateEnterExists(updateReqVO.getId());
        // 更新
        UnplateEnterDO updateObj = BeanUtils.toBean(updateReqVO, UnplateEnterDO.class);
        enterMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnter(Long id) {
        // 校验存在
        validateEnterExists(id);
        // 删除
        enterMapper.deleteById(id);
    }

    @Override
    public void deleteEnterListByIds(List<Long> ids) {
        // 删除
        enterMapper.deleteByIds(ids);
    }


    private void validateEnterExists(Long id) {
        if (enterMapper.selectById(id) == null) {
            throw exception(ENTER_NOT_EXISTS);
        }
    }

    @Override
    public UnplateEnterDO getEnter(Long id) {
        return enterMapper.selectById(id);
    }

    @Override
    public PageResult<UnplateEnterDO> getEnterPage(UnplateEnterPageReqVO pageReqVO) {
        return enterMapper.selectPage(pageReqVO);
    }
    @Override
    public PageResult<UnplateEnterRespVO> getUnplateEnterPage(UnplateEnterPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<UnplateEnterRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 查询（使用 IPage 参数）
        IPage<UnplateEnterRespVO> pageResult = enterMapper.selectPageJoinStation(page, reqVO);

        // 3. 脱敏处理（安全处理）
        List<UnplateEnterRespVO> list = pageResult.getRecords().stream()
                .peek(vo -> {
                    String phone = vo.getPhone();
                    if (phone != null && phone.length() == 11) {
                        vo.setPhone(phone.substring(0, 3) + "****" + phone.substring(7));
                    }
                })
                .collect(Collectors.toList());

        // 4. 返回最终结果
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public void createEnterVehiclePass(UnplateEnterCreateReqVO createReqVO) {
        // 插入
        UnplateEnterDO enter = BeanUtils.toBean(createReqVO, UnplateEnterDO.class);
        // 设置默认状态为"待审核"
        enter.setStatus("待审核");
        // 设置登记时间为当前时间
        enter.setRegisterTime(java.time.LocalDateTime.now());
        enterMapper.insert(enter);
    }

    @Override
    public void auditEnter(UnplateEnterAuditReqVO auditReqVO) {
        // 校验记录存在
        UnplateEnterDO enter = enterMapper.selectById(auditReqVO.getId());
        if (enter == null) {
            throw exception(ENTER_NOT_EXISTS);
        }
        // 校验状态只能是"待审核"才能审核
        if (!"待审核".equals(enter.getStatus())) {
            throw exception(ENTER_AUDIT_FAILED);
        }

        // 更新审核信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        if (currentUserId == null) {
            throw exception(USER_NOT_LOGIN);
        }

        UnplateEnterDO updateObj = new UnplateEnterDO();
        updateObj.setId(auditReqVO.getId());
        updateObj.setStatus(auditReqVO.getAuditResult());
        updateObj.setAuditComment(auditReqVO.getAuditComment());
        updateObj.setAuditTime(java.time.LocalDateTime.now());
        updateObj.setAuditUserId(currentUserId);
        enterMapper.updateById(updateObj);
    }

    @Override
    public void confirmEnter(UnplateEnterConfirmReqVO confirmReqVO) {
        // 校验记录存在
        UnplateEnterDO enter = enterMapper.selectById(confirmReqVO.getId());
        if (enter == null) {
            throw exception(ENTER_NOT_EXISTS);
        }
        // 校验状态只能是"已通过"才能确认
        if (!"已通过".equals(enter.getStatus())) {
            throw exception(ENTER_CONFIRM_FAILED);
        }

        // 更新状态为"已入场"
        UnplateEnterDO updateObj = new UnplateEnterDO();
        updateObj.setId(confirmReqVO.getId());
        updateObj.setStatus("已入场");
        enterMapper.updateById(updateObj);
    }

    @Override
    public void correctEnter(UnplateEnterCorrectReqVO correctReqVO) {
        // 校验记录存在
        UnplateEnterDO enter = enterMapper.selectById(correctReqVO.getId());
        if (enter == null) {
            throw exception(ENTER_NOT_EXISTS);
        }
        // 校验状态只能是"待审核"或"已通过"才能修正
        if (!"待审核".equals(enter.getStatus()) && !"已通过".equals(enter.getStatus())) {
            throw exception(ENTER_CORRECT_FAILED);
        }

        // 更新修正信息
        UnplateEnterDO updateObj = new UnplateEnterDO();
        updateObj.setId(correctReqVO.getId());
        updateObj.setCarType(correctReqVO.getCarType());
        updateObj.setCarColor(correctReqVO.getCarColor());
        updateObj.setPhone(correctReqVO.getPhone());
        updateObj.setStationId(correctReqVO.getStationId());
        updateObj.setRemark(correctReqVO.getRemark());
        enterMapper.updateById(updateObj);
    }

    @Override
    public UnplateEnterChartRespVO getUnplateEnterChart(UnplateEnterChartReqVO chartReqVO) {
        UnplateEnterChartRespVO respVO = new UnplateEnterChartRespVO();

        List<Map<String, Object>> stationCountList = enterMapper.selectStationUnplateCount(chartReqVO);
        List<UnplateEnterChartRespVO.StationUnplateCount> stationList = new ArrayList<>();
        for (Map<String, Object> map : stationCountList) {
            UnplateEnterChartRespVO.StationUnplateCount item = new UnplateEnterChartRespVO.StationUnplateCount();
            item.setStationName((String) map.get("stationName"));
            Object countObj = map.get("count");
            item.setCount(countObj != null ? ((Number) countObj).longValue() : 0L);
            stationList.add(item);
        }
        respVO.setStationUnplateCount(stationList);

        Map<String, Object> stats = enterMapper.selectUnplateEnterStats(chartReqVO);
        UnplateEnterChartRespVO.CardData cardData = new UnplateEnterChartRespVO.CardData();

        Object unplateEnterCountObj = stats.get("unplateEnterCount");
        Object auditPassCountObj = stats.get("auditPassCount");

        long unplateEnterCount = unplateEnterCountObj != null ? ((Number) unplateEnterCountObj).longValue() : 0L;
        long auditPassCount = auditPassCountObj != null ? ((Number) auditPassCountObj).longValue() : 0L;

        cardData.setUnplateEnterCount(unplateEnterCount);
        if (unplateEnterCount > 0) {
            cardData.setAuditPassRate(Math.round(auditPassCount * 10000.0 / unplateEnterCount) / 100.0);
        } else {
            cardData.setAuditPassRate(0.0);
        }
        respVO.setCardData(cardData);

        return respVO;
    }

}