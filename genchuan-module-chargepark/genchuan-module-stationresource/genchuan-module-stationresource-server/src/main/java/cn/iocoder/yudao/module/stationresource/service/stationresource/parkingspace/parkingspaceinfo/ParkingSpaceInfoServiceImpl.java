package cn.iocoder.yudao.module.stationresource.service.stationresource.parkingspace.parkingspaceinfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.AddParkingSpaceInfoReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.BindParkingSpaceReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.ImportResultVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.statistics.ParkingSpaceChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo.StationInfoMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.qrcode.QrCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 车位信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingSpaceInfoServiceImpl implements ParkingSpaceInfoService {

    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public ParkingSpaceChartRespVO getParkingSpaceChart() {
        ParkingSpaceChartRespVO resp = new ParkingSpaceChartRespVO();

        // 1. 查询地图列表（三表联查：车位 + 场站 + 片区，直接带出经纬度）
        List<ParkingSpaceChartRespVO.SpaceMapDTO> spaceMapList = parkingSpaceInfoMapper.selectParkingSpaceChartList();
        resp.setSpaceMapList(spaceMapList);

        // 2. 查询统计卡片数据
        ParkingSpaceChartRespVO.CardDataDTO cardData = new ParkingSpaceChartRespVO.CardDataDTO();
        cardData.setTotalSpaceCount(parkingSpaceInfoMapper.selectTotalSpaceCount());
        cardData.setAvailableSpaceCount(parkingSpaceInfoMapper.selectAvailableSpaceCount());
        resp.setCardData(cardData);

        return resp;
    }
//    @Override
//    public ParkingSpaceChartRespVO getParkingSpaceChart() {
//        ParkingSpaceChartRespVO resp = new ParkingSpaceChartRespVO();
//
//        // ===================== 1. 查询全部有效车位（不含禁用、未删除）=====================
//        List<ParkingSpaceInfoDO> spaceList = parkingSpaceInfoMapper.selectList(Wrappers.lambdaQuery(ParkingSpaceInfoDO.class)
//                .eq(ParkingSpaceInfoDO::getDeleted, false)
//                .ne(ParkingSpaceInfoDO::getStatus, "已禁用")
//        );
//
//        if (CollUtil.isEmpty(spaceList)) {
//            resp.setSpaceMapList(Collections.emptyList());
//            ParkingSpaceChartRespVO.CardDataDTO card = new ParkingSpaceChartRespVO.CardDataDTO();
//            card.setTotalSpaceCount(0L);
//            card.setAvailableSpaceCount(0L);
//            resp.setCardData(card);
//            return resp;
//        }
//
//        // ===================== 2. 批量查询 场站 =====================
//        Set<Long> stationIds = spaceList.stream()
//                .map(ParkingSpaceInfoDO::getStationId)
//                .collect(Collectors.toSet());
//
//        List<StationInfoDO> stationList = stationInfoMapper.selectBatchIds(stationIds);
//        Map<Long, StationInfoDO> stationMap = stationList.stream()
//                .collect(Collectors.toMap(StationInfoDO::getId, s -> s));
//
//        // ===================== 3. 批量查询 片区（经纬度来源）=====================
//        Set<Long> areaIds = stationList.stream()
//                .map(StationInfoDO::getAreaId)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toSet());
//
//        List<AreaInfoDO> areaList = areaInfoMapper.selectBatchIds(areaIds);
//        Map<Long, AreaInfoDO> areaMap = areaList.stream()
//                .collect(Collectors.toMap(AreaInfoDO::getId, a -> a));
//
//        // ===================== 4. 组装地图数据（经纬度从片区表 area_info 取）=====================
//        List<ParkingSpaceChartRespVO.SpaceMapDTO> mapList = spaceList.stream().map(space -> {
//            ParkingSpaceChartRespVO.SpaceMapDTO map = new ParkingSpaceChartRespVO.SpaceMapDTO();
//            map.setId(space.getId());
//            map.setName(StrUtil.emptyIfNull(space.getLocation())); // 车位位置
//            map.setRealStatus(space.getRealStatus());
//
//            // 从 场站 → 片区 → 取经纬度
//            double lon = 0.0;
//            double lat = 0.0;
//
//            StationInfoDO station = stationMap.get(space.getStationId());
//            if (station != null) {
//                AreaInfoDO area = areaMap.get(station.getAreaId());
//                if (area != null && area.getLon() != null && area.getLat() != null) {
//                    lon = area.getLon().doubleValue();
//                    lat = area.getLat().doubleValue();
//                }
//            }
//
//            map.setLon(lon);
//            map.setLat(lat);
//            return map;
//        }).collect(Collectors.toList());
//
//        resp.setSpaceMapList(mapList);
//
//        // ===================== 5. 卡片统计 =====================
//        // 总车位数
//        long totalCount = parkingSpaceInfoMapper.selectCount(Wrappers.lambdaQuery(ParkingSpaceInfoDO.class)
//                .eq(ParkingSpaceInfoDO::getDeleted, false));
//
//        // 空闲车位数
//        long availableCount = parkingSpaceInfoMapper.selectCount(Wrappers.lambdaQuery(ParkingSpaceInfoDO.class)
//                .eq(ParkingSpaceInfoDO::getDeleted, false)
//                .eq(ParkingSpaceInfoDO::getRealStatus, "空闲"));
//
//        ParkingSpaceChartRespVO.CardDataDTO cardData = new ParkingSpaceChartRespVO.CardDataDTO();
//        cardData.setTotalSpaceCount(totalCount);
//        cardData.setAvailableSpaceCount(availableCount);
//        resp.setCardData(cardData);
//
//        return resp;
//    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindParkingSpace(BindParkingSpaceReqVO reqVO) {
        List<Long> ids = reqVO.getIds();
        Long deviceId = reqVO.getDeviceId();

        // 1. 获取当前登录用户（芋道框架通用）
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        Long userId = loginUser.getId();

        // 2. 批量更新车位信息
        LambdaUpdateWrapper<ParkingSpaceInfoDO> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(ParkingSpaceInfoDO::getId, ids)
                // 绑定设备ID
                //TODO 后续需要对绑定的设备进行存在性校验
                .set(ParkingSpaceInfoDO::getDeviceId, deviceId)
                // 状态 → 已绑定（对应字典：parking_space_info_status）
                .set(ParkingSpaceInfoDO::getStatus, "已绑定")
                // 绑定人
                .set(ParkingSpaceInfoDO::getBindUserId, userId)
                // 绑定时间
                .set(ParkingSpaceInfoDO::getBindTime, LocalDateTime.now())
                // 状态更新时间
                .set(ParkingSpaceInfoDO::getStatusUpdateTime, LocalDateTime.now());

        // 执行批量更新
        parkingSpaceInfoMapper.update(null, updateWrapper);
    }
    /**
     * 导入车位信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportResultVO importParkingSpaceInfo(MultipartFile file, boolean updateSupport) {
        ImportResultVO resp = new ImportResultVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // ===================== 【完整使用 VrvExcelUtils】 =====================
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    AddParkingSpaceInfoReqVO.class.getName() // 车位导入模板对应的 AddReq
            );

            // 获取实体列表
            List<AddParkingSpaceInfoReqVO> reqList = (List<AddParkingSpaceInfoReqVO>) resultMap.get("entityList");

            // 判空
            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportResultVO.ImportFailure> failures = new ArrayList<>();

            // 遍历导入
            for (int i = 0; i < reqList.size(); i++) {
                AddParkingSpaceInfoReqVO req = reqList.get(i);
                int rowIndex = i + 2; // Excel 真实行号

                try {
                    // ==============================================
                    // 支持更新 / 仅新增 二合一逻辑
                    // ==============================================
                    if (updateSupport) {
                        updateParkingSpaceIfExists(req); // 存在则更新，不存在则新增
                    } else {
                        addParkingSpaceInfo(req); // 仅新增（重复直接报错）
                    }
                    successCount++;

                } catch (Exception e) {
                    // 记录失败
                    ImportResultVO.ImportFailure failure = new ImportResultVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 封装返回
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            // 文件解析异常
            ImportResultVO.ImportFailure failure = new ImportResultVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    // ==================== 以下两个方法需要你根据业务实现，我给你标准模板 ====================
//    /**
//     * 仅新增车位（重复报错）
//     */
//    private void addParkingSpaceInfo(AddParkingSpaceInfoReqVO req) {
//        // 1. 校验车位编号是否存在
//        // 2. 转换为 DO
//        // 3. 插入数据库
//        ParkingSpaceInfoDO entity = new ParkingSpaceInfoDO();
//        // BeanUtils 拷贝属性
//        // 校验 + 插入
//        parkingSpaceInfoMapper.insert(entity);
//    }

    /**
     * 存在则更新，不存在则新增
     */
    private void updateParkingSpaceIfExists(AddParkingSpaceInfoReqVO req) {
        // 1. 根据编号查询车位
        LambdaQueryWrapper<ParkingSpaceInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkingSpaceInfoDO::getSpaceNo,req.getSpaceNo())
                .eq(ParkingSpaceInfoDO::getDeleted,0);
        ParkingSpaceInfoDO exist = parkingSpaceInfoMapper.selectOne(wrapper);
        if (exist == null) {
            // 不存在 → 新增
            addParkingSpaceInfo(req);
        } else {
            // 存在 → 更新（只拷贝非空字段）
            ParkingSpaceInfoDO updateEntity = BeanUtils.toBean(req, ParkingSpaceInfoDO.class);
            updateEntity.setId(exist.getId()); // 必须设置ID

            // ========= 关键：只更新非空字段，避免覆盖 null =========
            LambdaUpdateWrapper<ParkingSpaceInfoDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(ParkingSpaceInfoDO::getId, exist.getId());

            // 执行更新
            parkingSpaceInfoMapper.update(updateEntity, updateWrapper);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addParkingSpaceInfo(AddParkingSpaceInfoReqVO createReqVO) {
        // 1. 校验车位编号唯一
        ParkingSpaceInfoDO exist = parkingSpaceInfoMapper.selectOne(ParkingSpaceInfoDO::getSpaceNo, createReqVO.getSpaceNo());
        if (exist != null) {
            throw exception("车位编号已存在，请勿重复创建");
        }

        // 2. 转换 DO
        ParkingSpaceInfoDO entity = BeanUtils.toBean(createReqVO, ParkingSpaceInfoDO.class);

        // 3. 后端自动生成二维码（核心）
        String qrBase64 = QrCodeUtils.generateBase64(createReqVO.getSpaceNo());
        entity.setQrcode(qrBase64);
        // 3. 未创建时默认状态：未绑定
        entity.setStatus("未绑定");

        // 4. 插入
        parkingSpaceInfoMapper.insert(entity);
        return entity.getId();
    }
//    @Override
//    public Long createParkingSpaceInfo(ParkingSpaceInfoSaveReqVO createReqVO) {
//        // 插入
//        ParkingSpaceInfoDO parkingSpaceInfo = BeanUtils.toBean(createReqVO, ParkingSpaceInfoDO.class);
//        parkingSpaceInfoMapper.insert(parkingSpaceInfo);
//
//        // 返回
//        return parkingSpaceInfo.getId();
//    }

    @Override
    public void updateParkingSpaceInfo(ParkingSpaceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateParkingSpaceInfoExists(updateReqVO.getId());
        // 更新
        ParkingSpaceInfoDO updateObj = BeanUtils.toBean(updateReqVO, ParkingSpaceInfoDO.class);
        parkingSpaceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkingSpaceInfo(Long id) {
        // 校验存在
        validateParkingSpaceInfoExists(id);
        // 删除
        parkingSpaceInfoMapper.deleteById(id);
    }

    @Override
        public void deleteParkingSpaceInfoListByIds(List<Long> ids) {
        // 删除
        parkingSpaceInfoMapper.deleteByIds(ids);
        }


    private void validateParkingSpaceInfoExists(Long id) {
        if (parkingSpaceInfoMapper.selectById(id) == null) {
            throw exception(PARKING_SPACE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public ParkingSpaceInfoDO getParkingSpaceInfo(Long id) {
        return parkingSpaceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingSpaceInfoDO> getParkingSpaceInfoPage(ParkingSpaceInfoPageReqVO pageReqVO) {
        return parkingSpaceInfoMapper.selectPage(pageReqVO);
    }

}
