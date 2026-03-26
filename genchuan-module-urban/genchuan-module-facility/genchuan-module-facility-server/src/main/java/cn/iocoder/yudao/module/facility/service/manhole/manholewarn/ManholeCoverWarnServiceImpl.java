package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.syswarn.SysWarnMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.COVER_NOT_EXISTS;

@Service
public class ManholeCoverWarnServiceImpl implements ManholeCoverWarnService {

    @Resource
    private SysWarnMapper sysWarnMapper;

    @Resource
    private ManholeCoverMapper coverMapper;

    @Resource
    private ManholeMonitorMapper monitorMapper;


    @Override
    public PageResult<ManholeCoverWarnPageRespVO> getWarnPage(ManholeCoverWarnPageReqVO reqVO) {

        // 分页
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(reqVO.getPageNo());
        pageParam.setPageSize(reqVO.getPageSize());

        // 查询
        List<ManholeCoverWarnPageRespVO> list = sysWarnMapper.selectManholeCoverWarnPage(reqVO, pageParam);
        Long total = sysWarnMapper.selectManholeCoverWarnCount(reqVO);

        return new PageResult<>(list, total);
    }

    @Override
    public ManholeCoverWarnDetailRespVO getWarnDetail(ManholeCoverWarnDetailReqVO reqVO) {
        return sysWarnMapper.selectManholeCoverWarnDetail(reqVO.getWarnId());
    }


    //TODO 待完善数据库字段于接口文档匹配
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManholeCoverWarnTriggerAlarmRespVO triggerAlarm(String coverId, ManholeCoverWarnTriggerAlarmReqVO reqVO) {
        // 1. 校验井盖是否存在
        ManholeCoverDO cover = coverMapper.selectById(coverId);
        if (cover == null) {
            throw exception(COVER_NOT_EXISTS);
        }
        // TODO 待完善：从 manhole_config 读取报警方式、接收人
        // TODO 待完善：报警内容自动拼接（井盖编号+位置+指标）
        // TODO 待完善：执行实际报警推送（短信/电话/平台）
        // 2. 构造预警记录（Yudao 数据库 DO）
        SysWarnDO warn = new SysWarnDO();
        warn.setWarnNo("WARN-" + System.currentTimeMillis());
        warn.setFacilityId(Long.valueOf(coverId));
        warn.setFacilityName(cover.getCoverNo());
        warn.setDeviceCode(cover.getCoverNo());
        warn.setFacilityType("窨井盖");
        warn.setLevel(reqVO.getAlarmLevel());
        warn.setStatus("待处置");
        warn.setAssignStatus("未派单");
        warn.setCreator(String.valueOf(reqVO.getOperateUserId()));
        warn.setDeleted(false);

        // 3. 插入预警表
        sysWarnMapper.insert(warn);

        // TODO 待完善：自动生成处置工单 work_order / disposal_order

        // 4. 构造返回
        ManholeCoverWarnTriggerAlarmRespVO resp = new ManholeCoverWarnTriggerAlarmRespVO();
        resp.setAlarmId(String.valueOf(warn.getId()));
        resp.setCoverId(coverId);
        resp.setAlarmStatus(1);
        resp.setTenantId(reqVO.getTenantId());
        return resp;
    }
}
