package cn.iocoder.yudao.module.vehiclecharging.service.pile;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile.PileDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation.ChargingStationMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.charginglot.ChargingLotMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pile.PileMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.charge.ChargeModeMapper;
import cn.iocoder.yudao.module.vehiclecharging.framework.common.QrCodeUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 充电桩 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PileServiceImpl implements PileService {

    @Resource
    private PileMapper pileMapper;

    @Resource
    private ChargingStationMapper chargingStationMapper;

    @Resource
    private ChargingLotMapper chargingLotMapper;

    @Resource
    private ChargeModeMapper chargeModeMapper;

    @Autowired(required = false)
    private AdminUserApi adminUserApi;

    /**
     * 充电桩状态ID → 状态名称映射（内存常量，无需查库）
     */
    private static final Map<Long, String> PILE_STATUS_NAME_MAP = Map.of(
            1L, "已启用",
            2L, "已停用",
            3L, "未调试",
            4L, "已调试"
    );

    /**
     * 充电模式ID → 模式名称映射（内存常量，无需查库）
     */
    private static final Map<Long, String> CHARGE_MODE_NAME_MAP = Map.of(
            1L, "直流",
            2L, "交流",
            3L, "交直流混合"
    );

    @Override
    public Long createPile(PileSaveReqVO createReqVO) {
        // 校验 pileCode 唯一性
        if (pileMapper.selectIdByPileCode(createReqVO.getPileCode()) != null) {
            throw exception(PILE_CODE_DUPLICATE);
        }
        // 插入
        PileDO pile = BeanUtils.toBean(createReqVO, PileDO.class);
        // 新增时默认设备状态为未调试（状态ID=3）
        if (pile.getPileStatus() == null) {
            pile.setPileStatus(3L);
        }
        // 默认无故障
        if (pile.getFaultFlag() == null) {
            pile.setFaultFlag(false);
        }
        pileMapper.insert(pile);

        // 返回
        return pile.getId();
    }

    @Override
    public void updatePile(PileSaveReqVO updateReqVO) {
        // 校验存在
        validatePileExists(updateReqVO.getId());
        // 校验 pileCode 唯一性（排除自己）
        if (updateReqVO.getPileCode() != null) {
            Long existId = pileMapper.selectIdByPileCode(updateReqVO.getPileCode());
            if (existId != null && !existId.equals(updateReqVO.getId())) {
                throw exception(PILE_CODE_DUPLICATE);
            }
        }
        // 更新
        PileDO updateObj = BeanUtils.toBean(updateReqVO, PileDO.class);
        pileMapper.updateById(updateObj);
    }

    @Override
    public void deletePile(Long id) {
        // 校验存在
        validatePileExists(id);
        // 删除
        pileMapper.deleteById(id);
    }

    @Override
        public void deletePileListByIds(List<Long> ids) {
        // 删除
        pileMapper.deleteByIds(ids);
        }


    private void validatePileExists(Long id) {
        if (pileMapper.selectById(id) == null) {
            throw exception(PILE_NOT_EXISTS);
        }
    }

    @Override
    public PileRespVO getPile(Long id) {
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            return null;
        }
        return fillNameFields(BeanUtils.toBean(pile, PileRespVO.class));
    }

    @Override
    public PageResult<PileRespVO> getPilePage(PilePageReqVO pageReqVO) {
        PageResult<PileDO> pageResult = pileMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return new PageResult<>(Collections.emptyList(), pageResult.getTotal());
        }
        List<PileRespVO> respList = BeanUtils.toBean((List<PileDO>) pageResult.getList(), PileRespVO.class);
        // 填充关联名称字段
        fillNameFields(respList);
        return new PageResult<>(respList, pageResult.getTotal());
    }

    private PileRespVO fillNameFields(PileRespVO respVO) {
        if (respVO.getStationId() != null) {
            ChargingStationDO station = chargingStationMapper.selectById(respVO.getStationId());
            if (station != null) {
                respVO.setStationName(station.getStationName());
            }
        }
        if (respVO.getLotId() != null) {
            ChargingLotDO lot = chargingLotMapper.selectById(respVO.getLotId());
            if (lot != null) {
                respVO.setLotName(lot.getLotCode());
            }
        }
        if (respVO.getPileStatus() != null) {
            respVO.setPileStatusName(PILE_STATUS_NAME_MAP.get(respVO.getPileStatus()));
        }
        if (respVO.getChargeMode() != null) {
            respVO.setChargeModeName(CHARGE_MODE_NAME_MAP.get(respVO.getChargeMode()));
        }
        if (respVO.getCreator() != null && adminUserApi != null) {
            try {
                AdminUserRespDTO user = adminUserApi.getUser(Long.valueOf(respVO.getCreator())).getCheckedData();
                if (user != null) {
                    respVO.setCreatorName(user.getNickname());
                }
            } catch (Exception ignored) {
            }
        }
        if (respVO.getUpdater() != null && adminUserApi != null) {
            try {
                AdminUserRespDTO user = adminUserApi.getUser(Long.valueOf(respVO.getUpdater())).getCheckedData();
                if (user != null) {
                    respVO.setUpdaterName(user.getNickname());
                }
            } catch (Exception ignored) {
            }
        }
        return respVO;
    }

    private void fillNameFields(List<PileRespVO> respVOList) {
        // 批量填充场站名称
        Set<Long> stationIds = respVOList.stream()
                .map(PileRespVO::getStationId)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet());
        Map<Long, String> stationNameMap = stationIds.isEmpty() ? Collections.emptyMap()
                : convertMap(chargingStationMapper.selectList(ChargingStationDO::getId, stationIds),
                        ChargingStationDO::getId, ChargingStationDO::getStationName);

        // 批量填充车位名称
        Set<Long> lotIds = respVOList.stream()
                .map(PileRespVO::getLotId)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet());
        Map<Long, String> lotNameMap = lotIds.isEmpty() ? Collections.emptyMap()
                : convertMap(chargingLotMapper.selectList(ChargingLotDO::getId, lotIds),
                        ChargingLotDO::getId, ChargingLotDO::getLotCode);

        // 批量填充状态名称
        Set<Long> statusIds = respVOList.stream()
                .map(PileRespVO::getPileStatus)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet());
        // 不需要查库，直接从内存映射获取状态名称
        Map<Long, String> statusNameMap = new java.util.HashMap<>();
        for (Long id : statusIds) {
            statusNameMap.put(id, PILE_STATUS_NAME_MAP.get(id));
        }

        // 不需要查库，直接从内存映射获取充电模式名称
        Map<Long, String> chargeModeNameMap = new java.util.HashMap<>();
        for (Long id : respVOList.stream()
                .map(PileRespVO::getChargeMode)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet())) {
            chargeModeNameMap.put(id, CHARGE_MODE_NAME_MAP.get(id));
        }

        // 批量填充用户名称
        Set<String> creatorIds = respVOList.stream()
                .map(PileRespVO::getCreator)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet());
        Set<String> updaterIds = respVOList.stream()
                .map(PileRespVO::getUpdater)
                .filter(Objects::nonNull)
                .collect(java.util.stream.Collectors.toSet());
        Set<Long> allUserIds = new java.util.HashSet<>();
        creatorIds.forEach(id -> { try { allUserIds.add(Long.valueOf(id)); } catch (Exception ignored) {} });
        updaterIds.forEach(id -> { try { allUserIds.add(Long.valueOf(id)); } catch (Exception ignored) {} });
        Map<Long, String> userNameMap = allUserIds.isEmpty() || adminUserApi == null ? Collections.emptyMap()
                : convertMap(adminUserApi.getUserList(allUserIds).getCheckedData(),
                        AdminUserRespDTO::getId, AdminUserRespDTO::getNickname);

        // 逐条填充
        for (PileRespVO respVO : respVOList) {
            if (respVO.getStationId() != null) {
                respVO.setStationName(stationNameMap.get(respVO.getStationId()));
            }
            if (respVO.getLotId() != null) {
                respVO.setLotName(lotNameMap.get(respVO.getLotId()));
            }
            if (respVO.getPileStatus() != null) {
                respVO.setPileStatusName(statusNameMap.get(respVO.getPileStatus()));
            }
            if (respVO.getChargeMode() != null) {
                respVO.setChargeModeName(chargeModeNameMap.get(respVO.getChargeMode()));
            }
            if (respVO.getCreator() != null) {
                try {
                    respVO.setCreatorName(userNameMap.get(Long.valueOf(respVO.getCreator())));
                } catch (Exception ignored) {}
            }
            if (respVO.getUpdater() != null) {
                try {
                    respVO.setUpdaterName(userNameMap.get(Long.valueOf(respVO.getUpdater())));
                } catch (Exception ignored) {}
            }
        }
    }

    @Override
    public void debugPile(Long id) {
        // 校验充电桩存在
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            throw exception(PILE_NOT_EXISTS);
        }
        // 校验当前状态为未调试（状态ID=3）
        if (pile.getPileStatus() != 3L) {
            throw exception(PILE_STATUS_NOT_DEBUGGING);
        }
        // 更新状态为已调试（状态ID=4）
        PileDO updateObj = PileDO.builder().id(id).pileStatus(4L).build();
        pileMapper.updateById(updateObj);
    }

    @Override
    public void enablePile(Long id) {
        // 校验充电桩存在
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            throw exception(PILE_NOT_EXISTS);
        }
        // 校验当前状态为已调试（状态ID=4）
        if (pile.getPileStatus() != 4L) {
            throw exception(PILE_STATUS_NOT_ENABLED);
        }
        // 更新状态为已启用（状态ID=1）
        PileDO updateObj = PileDO.builder().id(id).pileStatus(1L).build();
        pileMapper.updateById(updateObj);
    }

    @Override
    public void disablePile(Long id, String remark) {
        // 校验充电桩存在
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            throw exception(PILE_NOT_EXISTS);
        }
        // 校验当前状态为已启用（状态ID=1）
        if (pile.getPileStatus() != 1L) {
            throw exception(PILE_STATUS_NOT_ENABLED_FOR_DISABLE);
        }
        // 更新状态为未调试（状态ID=3），并设置备注
        PileDO updateObj = PileDO.builder().id(id).pileStatus(3L).remark(remark).build();
        pileMapper.updateById(updateObj);
    }

    @Override
    public byte[] getPileQrcode(Long id) {
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            throw exception(PILE_NOT_EXISTS);
        }
        String content = pile.getQrcode();
        if (content == null || content.isEmpty()) {
            throw exception(PILE_QRCODE_NOT_EXISTS);
        }
        return QrCodeUtils.generateBytes(content, 300, 300);
    }

    @Override
    public boolean restartPile(Long id) {
        PileDO pile = pileMapper.selectById(id);
        if (pile == null) {
            throw exception(PILE_NOT_EXISTS);
        }
        // TODO: 远程调用设备接口重启充电桩
        return true;
    }

    @Override
    public List<PileChargeModeStatRespVO> getChargeModeStatList(Long stationId) {
        List<PileChargeModeStatRespVO> list = pileMapper.selectChargeModeStat(stationId);
        list.forEach(item -> item.setChargeModeName(CHARGE_MODE_NAME_MAP.get(item.getChargeMode())));
        return list;
    }

    @Override
    public List<PileStatusDictRespVO> getChargeModeDictList() {
        return CHARGE_MODE_NAME_MAP.entrySet().stream()
                .map(e -> {
                    PileStatusDictRespVO vo = new PileStatusDictRespVO();
                    vo.setValue(String.valueOf(e.getKey()));
                    vo.setLabel(e.getValue());
                    return vo;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<PileRunTimeTrendRespVO> getRunTimeTrendList(PileRunTimeTrendReqVO reqVO) {
        List<PileChartRespVO.RunTimeTrend> list = pileMapper.selectRunTimeTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId());
        return BeanUtils.toBean(list, PileRunTimeTrendRespVO.class);
    }

    @Override
    public List<PileStatusDictRespVO> getPileStatusDictList() {
        return PILE_STATUS_NAME_MAP.entrySet().stream()
                .map(e -> {
                    PileStatusDictRespVO vo = new PileStatusDictRespVO();
                    vo.setValue(String.valueOf(e.getKey()));
                    vo.setLabel(e.getValue());
                    return vo;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<PileStatusStatRespVO> getPileStatusStatList() {
        List<PileStatusStatRespVO> list = pileMapper.selectPileStatusStat();
        list.forEach(item -> item.setPileStatusName(PILE_STATUS_NAME_MAP.get(item.getPileStatus())));
        return list;
    }

    @Override
    public PileChartRespVO getPileChart(PileChartReqVO reqVO) {
        PileChartRespVO respVO = new PileChartRespVO();
        respVO.setRunTimeTrendList(pileMapper.selectRunTimeTrend(
                reqVO.getStartTime(), reqVO.getEndTime(), reqVO.getStationId()));
        List<PileChartRespVO.TypeBar> typeBarList = pileMapper.selectTypeBarList(reqVO.getStationId());
        typeBarList.forEach(item -> {
            if (item.getTypeName() != null) {
                item.setTypeNameName(CHARGE_MODE_NAME_MAP.get(Long.valueOf(item.getTypeName())));
            }
        });
        respVO.setTypeBarList(typeBarList);
        respVO.setCardInfo(pileMapper.selectCardInfo(reqVO.getStationId()));
        return respVO;
    }

    @Override
    public List<LotSimpleRespVO> getLotSimpleList() {
        List<ChargingLotDO> list = chargingLotMapper.selectList();
        return convertList(list, lot -> {
            LotSimpleRespVO vo = new LotSimpleRespVO();
            vo.setValue(lot.getId());
            vo.setLabel(lot.getLotCode());
            return vo;
        });
    }

    @Override
    public List<StationSimpleRespVO> getStationSimpleList() {
        List<ChargingStationDO> list = chargingStationMapper.selectList();
        return convertList(list, station -> {
            StationSimpleRespVO vo = new StationSimpleRespVO();
            vo.setValue(station.getId());
            vo.setLabel(station.getStationName());
            return vo;
        });
    }

}