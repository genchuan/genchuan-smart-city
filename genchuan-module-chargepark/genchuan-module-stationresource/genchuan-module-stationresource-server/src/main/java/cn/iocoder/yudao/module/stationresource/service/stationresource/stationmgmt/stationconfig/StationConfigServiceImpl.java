package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationconfig;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.AddStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.UpdateStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.statistics.StationConfigChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationconfig.StationConfigDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationconfig.StationConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 场站配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class StationConfigServiceImpl implements StationConfigService {

    @Resource
    private StationConfigMapper stationConfigMapper;

    @Override
    public StationConfigChartRespVO getStationConfigChart() {
        StationConfigChartRespVO resp = new StationConfigChartRespVO();


//        List<Map<String, Object>> typeMapList = stationConfigMapper.selectTypeGroupCount();
//        log.info("cscs{}",typeMapList);
//        List<StationConfigChartRespVO.TypePieDTO> typePieList = typeMapList.stream().map(map -> {
//            StationConfigChartRespVO.TypePieDTO dto = new StationConfigChartRespVO.TypePieDTO();
//            dto.setName((String) map.get("name"));
//            dto.setValue(((Long) map.get("value")).intValue());
//            return dto;
//        }).toList();
        // 1. 按 type 分组统计（饼图）
        List<StationConfigChartRespVO.TypePieDTO> typePieList = stationConfigMapper.selectTypeGroupCount();
        resp.setTypePieList(typePieList);

        // 2. 卡片数据
        StationConfigChartRespVO.CardDataDTO card = new StationConfigChartRespVO.CardDataDTO();

        // 已配置场站数（去重 station_id）
        Integer stationCount = stationConfigMapper.selectConfigedStationCount();
        card.setConfigedStationCount(stationCount);

        // 已生效配置数量（status = 已生效）
        Integer enableCount = stationConfigMapper.selectEnableConfigCount();
        card.setEnableConfigCount(enableCount);

        resp.setCardData(card);
        return resp;
    }
    @Override
    public void enableStationConfig(List<Long> ids) {
        // 1. 获取当前登录用户ID
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        // 构建更新条件
        LambdaUpdateWrapper<StationConfigDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .in(StationConfigDO::getId, ids)       // 批量 ID
                .set(StationConfigDO::getStatus, "已生效") // 设置为 已生效
                // 审核时间 → 当前时间
                .set(StationConfigDO::getAuditTime, LocalDateTime.now())
                // 审核人 → 当前登录用户
                .set(StationConfigDO::getAuditUserId, loginUserId)
                // 同步时间 → 当前时间
                .set(StationConfigDO::getSyncTime, LocalDateTime.now());

        // 执行更新
        stationConfigMapper.update(null, updateWrapper);
    }

    @Override
    public void disableStationConfig(List<Long> ids) {
        // 1. 获取当前登录用户ID
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        // 构建更新条件
        LambdaUpdateWrapper<StationConfigDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .in(StationConfigDO::getId, ids)         // 批量 ID
                .set(StationConfigDO::getStatus, "未生效") // 设置为 未生效
                // 审核时间 → 当前时间
                .set(StationConfigDO::getAuditTime, LocalDateTime.now())
                // 审核人 → 当前登录用户
                .set(StationConfigDO::getAuditUserId, loginUserId)
                // 同步时间 → 当前时间
                .set(StationConfigDO::getSyncTime, LocalDateTime.now());

        // 执行更新
        stationConfigMapper.update(null, updateWrapper);
    }
    @Override
    public Long createStationConfig(StationConfigSaveReqVO createReqVO) {
        // 插入
        StationConfigDO stationConfig = BeanUtils.toBean(createReqVO, StationConfigDO.class);
        stationConfigMapper.insert(stationConfig);

        // 返回
        return stationConfig.getId();
    }

    @Override
    public void updateStationConfig(@Valid UpdateStationConfigReqVO updateReqVO) {
        // 校验存在
        validateStationConfigExists(updateReqVO.getId());
        // 更新
        StationConfigDO updateObj = BeanUtils.toBean(updateReqVO, StationConfigDO.class);
        stationConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteStationConfig(Long id) {
        // 校验存在
        validateStationConfigExists(id);
        // 删除
        stationConfigMapper.deleteById(id);
    }

    @Override
        public void deleteStationConfigListByIds(List<Long> ids) {
        // 删除
        stationConfigMapper.deleteByIds(ids);
        }


    private void validateStationConfigExists(Long id) {
        if (stationConfigMapper.selectById(id) == null) {
            throw exception(STATION_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public StationConfigRespVO getStationConfig(Long id) {
        StationConfigPageReqVO reqVO =new StationConfigPageReqVO();
        reqVO.setId(id);
        reqVO.setPageSize(1);
        StationConfigRespVO stationConfigRespVO = getStationConfigPage(reqVO).getList().stream().findFirst().orElse(null);

        return stationConfigRespVO;
    }

    @Override
    public PageResult<StationConfigRespVO> getStationConfigPage(StationConfigPageReqVO pageReqVO) {
        Page<StationConfigRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        Page<StationConfigRespVO> resultPage = stationConfigMapper.getPage(page, pageReqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    public Long addStationConfig(AddStationConfigReqVO createReqVO) {
        // 1. VO 转 DO
        StationConfigDO stationConfig = BeanUtils.toBean(createReqVO, StationConfigDO.class);

        // 2. 需求要求：新增默认状态 = 未生效（必须加，否则数据库字段为null）
        stationConfig.setStatus("未生效");

        // 3. 插入数据库
        stationConfigMapper.insert(stationConfig);

        // 4. 返回主键ID
        return stationConfig.getId();
    }

}
