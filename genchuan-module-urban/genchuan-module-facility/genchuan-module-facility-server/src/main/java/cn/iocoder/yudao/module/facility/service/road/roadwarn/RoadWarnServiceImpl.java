package cn.iocoder.yudao.module.facility.service.road.roadwarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.*;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnCandidate;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor.RoadMonitorDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn.RoadWarnDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadfacility.RoadFacilityMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadmonitor.RoadMonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadwarn.RoadWarnMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import cn.iocoder.yudao.module.facility.framework.lxsutils.road.RoadWarnUtil;
import cn.iocoder.yudao.module.facility.service.syswarn.SysWarnService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.WARN_NOT_EXISTS;

/**
 * 预警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadWarnServiceImpl implements RoadWarnService {

    @Resource
    private RoadWarnMapper roadWarnMapper;

    @Resource
    private SysWarnService sysWarnService;

    @Resource
    private RoadFacilityMapper roadFacilityMapper;

    @Resource
    private SysDeviceMapper sysDeviceMapper;

    @Resource
    private RoadMonitorMapper roadMonitorMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> createWarn(RoadWarnSaveReqVO createReqVO) {
        //通过监测id获取DO，通过is_warning=1判断是否可以触发预警。通过DO获取facilityId，deviceId
        // 0. 通过监测id获取code
        if (createReqVO.getMonitorId()==null){
            throw exception(500,"监测数据id不能为0");
        }
        RoadMonitorDO monitor = roadMonitorMapper.selectById(createReqVO.getMonitorId());
        if (monitor == null) {
            throw exception(500,"监测数据不存在，id：" + createReqVO.getMonitorId());
        }

        if (!"1".equals(monitor.getIsWarning())){
            //如果监测数据 的 预警状态 不是1（可以触发预警但是还没触发）
            if ("0".equals(monitor.getIsWarning())){
                throw exception(500,"检测数据无超限指标，不可触发预警");
            }
            if ("2".equals(monitor.getIsWarning())){
                throw exception(500,"检测数据已经触发过了预警");
            }
//            throw exception(500,"监测数据不可触发预警或者已触发过预警");
        }

        createReqVO.setMonitorCode(monitor.getMonitorCode());
        createReqVO.setFacilityId(monitor.getRoadId());
        createReqVO.setDeviceId(monitor.getDeviceId());


        // 1. 道路设施类型用“道路”
        createReqVO.setFacilityType("道路");

        // 2. 通过道路id获取设施名称和code
        RoadFacilityDO facility = roadFacilityMapper.selectById(createReqVO.getFacilityId());
        if (facility == null) {
            throw exception(500,"道路设施不存在，id：" + createReqVO.getFacilityId());
        }
        createReqVO.setFacilityName(facility.getRoadName());
        createReqVO.setFacilityCode(facility.getRoadCode());

        // 3. 通过设备id获取code
        Long deviceId = createReqVO.getDeviceId();
        if (deviceId == null) {
            throw exception(500, "监测设备ID不能为空");
        }

        SysDeviceDO device = sysDeviceMapper.selectById(createReqVO.getDeviceId());
        if (device == null) {
            throw exception(500, "监测设备不存在，id：" + createReqVO.getDeviceId());
        }

        //从实体中获取设备编码
        createReqVO.setDeviceCode(device.getDeviceCode());

        // 5.设置触发时间为监测记录时间
        createReqVO.setTriggerTime(monitor.getRecordTime());


        // 6. 根据监测id获取数据，自动计算得到type、overIndex、overValue、thresholdValue
        // 用来存放所有超标项
        List<SysWarnCandidate> candidates = RoadWarnUtil.calculateCandidates(monitor);

        if (candidates.isEmpty()) {
            throw exception(500, "监测数据未触发任何超标条件，无法生成预警");
        }

        // ===================== 一次监测生成多条预警 =====================
        List<Long> warnIdList = new ArrayList<>();

        for (SysWarnCandidate c : candidates) {

            RoadWarnSaveReqVO itemReqVO =
                    BeanUtils.toBean(createReqVO, RoadWarnSaveReqVO.class);

            itemReqVO.setType(c.getType());
            itemReqVO.setOverIndex(c.getOverIndex());
            itemReqVO.setOverValue(c.getOverValue());
            itemReqVO.setThresholdValue(c.getThresholdValue());

            SysWarnSaveReqVO sysWarnSaveReqVO =
                    BeanUtils.toBean(itemReqVO, SysWarnSaveReqVO.class);

            Long warnId = sysWarnService.createSysWarn(sysWarnSaveReqVO);
            warnIdList.add(warnId);
        }

        //添加完预警后，更新对应的检测数据：is_warning，warning_id_list_str
// ===================== 回写监测数据预警状态 =====================

// 1. is_warning = 2（已预警）
        monitor.setIsWarning(2);

// 2. 预警ID列表，逗号分隔
        String warnIdListStr = warnIdList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        monitor.setWarningIdListStr(warnIdListStr);

// 3. 更新监测表
        roadMonitorMapper.updateById(monitor);

        // 返回
        return warnIdList;
    }





    @Override
    public void updateWarn(WarnSaveReqVO updateReqVO) {
        // 校验存在
        validateWarnExists(updateReqVO.getId());
        // 更新
        RoadWarnDO updateObj = BeanUtils.toBean(updateReqVO, RoadWarnDO.class);
        roadWarnMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarn(Long id) {
        // 校验存在
        validateWarnExists(id);
        // 删除
        roadWarnMapper.deleteById(id);
    }

    private void validateWarnExists(Long id) {
        if (roadWarnMapper.selectById(id) == null) {
            throw exception(WARN_NOT_EXISTS);
        }
    }

    @Override
    public RoadWarnDO getWarn(Long id) {
        return roadWarnMapper.selectById(id);
    }

    @Override
    public PageResult<RoadWarnDO> getWarnPage(WarnPageReqVO pageReqVO) {
        return roadWarnMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RoadWarnPageRespVO> pageRoadWarn(RoadWarnPageReqVO reqVO) {
        System.out.println("cs2026-03-03 15:53:46:" + reqVO);
        PageResult<SysWarnRespVO> sysWarnPage = sysWarnService.getSysWarnPage(reqVO);


        PageResult<RoadWarnPageRespVO> roadWarn = BeanUtils.toBean(sysWarnPage, RoadWarnPageRespVO.class);

        return roadWarn;
    }

}
