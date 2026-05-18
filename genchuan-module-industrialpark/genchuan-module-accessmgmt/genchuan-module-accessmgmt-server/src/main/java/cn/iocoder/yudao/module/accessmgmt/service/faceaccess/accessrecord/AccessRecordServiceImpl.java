package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.accessrecord.AccessRecordDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.faceaccess.accessrecord.AccessRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 通行记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AccessRecordServiceImpl implements AccessRecordService {

    @Resource
    private AccessRecordMapper accessRecordMapper;

    @Override
    public PageResult<AccessRecordRespVO> getAccessRecordPage(AccessRecordPageReqVO pageReqVO) {
        PageResult<AccessRecordDO> pageResult = accessRecordMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, AccessRecordRespVO.class);
    }

    @Override
    public AccessRecordRespVO getAccessRecord(Long id) {
        AccessRecordDO entity = accessRecordMapper.selectById(id);
        if (entity == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, AccessRecordRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean checkAccessRecord(AccessRecordCheckReqVO reqVO) {
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setCheckResult(reqVO.getCheckResult());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean alarmAccessRecord(AccessRecordAlarmReqVO reqVO) {
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAlarmStatus(reqVO.getAlarmContent());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean handleAccessRecord(AccessRecordHandleReqVO reqVO) {
        AccessRecordDO exist = accessRecordMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(ACCESS_RECORD_NOT_EXISTS);
        }
        AccessRecordDO updateObj = new AccessRecordDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleResult(reqVO.getHandleResult());
        accessRecordMapper.updateById(updateObj);
        return true;
    }

    @Override
    public List<AccessRecordRespVO> getAccessRecordList(AccessRecordPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<AccessRecordDO> pageResult = accessRecordMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), AccessRecordRespVO.class);
    }

    @Override
    public AccessRecordChartRespVO getAccessRecordChart(Long startTime, Long endTime) {
        AccessRecordChartRespVO chartVO = new AccessRecordChartRespVO();
        chartVO.setTimeTrendList(accessRecordMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setDayTrendList(accessRecordMapper.selectDayTrendList(startTime, endTime));
        chartVO.setAreaCountList(accessRecordMapper.selectAreaCountList(startTime, endTime));
        chartVO.setUserCountList(accessRecordMapper.selectUserCountList(startTime, endTime));
        return chartVO;
    }

}
