package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.leaverecord;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.leaverecord.LeaveRecordMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;

/**
 * 离场记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LeaveRecordServiceImpl implements LeaveRecordService {

    @Resource
    private LeaveRecordMapper recordMapper;

    @Override
    public Long createRecord(LeaveRecordSaveReqVO createReqVO) {
        // 插入
        LeaveRecordDO record = BeanUtils.toBean(createReqVO, LeaveRecordDO.class);
        recordMapper.insert(record);

        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(LeaveRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        LeaveRecordDO updateObj = BeanUtils.toBean(updateReqVO, LeaveRecordDO.class);
        recordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        recordMapper.deleteById(id);
    }

    @Override
    public void deleteRecordListByIds(List<Long> ids) {
        // 删除
        recordMapper.deleteByIds(ids);
    }


    private void validateRecordExists(Long id) {
        if (recordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public LeaveRecordDO getRecord(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public PageResult<LeaveRecordDO> getRecordPage(LeaveRecordPageReqVO pageReqVO) {
        return recordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<LeaveRecordRespVO> getRecordPageWithJoin(LeaveRecordPageReqVO pageReqVO) {
        Page<LeaveRecordRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<LeaveRecordRespVO> pageResult = recordMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public Long createRecordSupplement(LeaveRecordCreateReqVO reqVO) {
        LeaveRecordDO record = new LeaveRecordDO();
        record.setPlateNo(reqVO.getPlateNo());
        record.setEnterTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getEnterTime()), 0, java.time.ZoneOffset.ofHours(8)));
        record.setLeaveTime(LocalDateTime.ofEpochSecond(Long.parseLong(reqVO.getLeaveTime()), 0, java.time.ZoneOffset.ofHours(8)));
        // 计算停车时长（分钟）
        long duration = java.time.Duration.between(record.getEnterTime(), record.getLeaveTime()).toMinutes();
        record.setParkDuration((int) duration);
        record.setStatus(reqVO.getStatus());
        record.setStationId(reqVO.getStationId());
        record.setRemark(reqVO.getRemark());
        record.setProofImage(reqVO.getProofImage());
        record.setIsCorrected(false);
        recordMapper.insert(record);
        return record.getId();
    }

}