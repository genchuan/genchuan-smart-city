package cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrecord.ParkPointsRecordDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpointsrecord.ParkPointsRecordMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 积分变动记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPointsRecordServiceImpl implements ParkPointsRecordService {

    @Resource
    private ParkPointsRecordMapper parkPointsRecordMapper;

    @Override
    public Long createParkPointsRecord(ParkPointsRecordSaveReqVO createReqVO) {
        // 插入
        ParkPointsRecordDO parkPointsRecord = BeanUtils.toBean(createReqVO, ParkPointsRecordDO.class);
        parkPointsRecordMapper.insert(parkPointsRecord);
        // 返回
        return parkPointsRecord.getId();
    }

    @Override
    public void updateParkPointsRecord(ParkPointsRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPointsRecordExists(updateReqVO.getId());
        // 更新
        ParkPointsRecordDO updateObj = BeanUtils.toBean(updateReqVO, ParkPointsRecordDO.class);
        parkPointsRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPointsRecord(Long id) {
        // 校验存在
        validateParkPointsRecordExists(id);
        // 删除
        parkPointsRecordMapper.deleteById(id);
    }

    private void validateParkPointsRecordExists(Long id) {
        if (parkPointsRecordMapper.selectById(id) == null) {
            throw exception(PARK_POINTS_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public ParkPointsRecordDO getParkPointsRecord(Long id) {
        return parkPointsRecordMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPointsRecordDO> getParkPointsRecordPage(ParkPointsRecordPageReqVO pageReqVO) {
        return parkPointsRecordMapper.selectPage(pageReqVO);
    }

}
