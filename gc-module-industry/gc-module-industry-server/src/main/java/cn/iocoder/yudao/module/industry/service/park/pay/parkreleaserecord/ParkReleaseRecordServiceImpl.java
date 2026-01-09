package cn.iocoder.yudao.module.industry.service.park.pay.parkreleaserecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordAddReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreleaserecord.ParkReleaseRecordDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkreleaserecord.ParkReleaseRecordMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 放行记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkReleaseRecordServiceImpl implements ParkReleaseRecordService {

    @Resource
    private ParkReleaseRecordMapper parkReleaseRecordMapper;

    @Override
    public Long createParkReleaseRecord(ParkReleaseRecordSaveReqVO createReqVO) {
        // 插入
        ParkReleaseRecordDO parkReleaseRecord = BeanUtils.toBean(createReqVO, ParkReleaseRecordDO.class);
        parkReleaseRecordMapper.insert(parkReleaseRecord);
        // 返回
        return parkReleaseRecord.getId();
    }

    @Override
    public void updateParkReleaseRecord(ParkReleaseRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateParkReleaseRecordExists(updateReqVO.getId());
        // 更新
        ParkReleaseRecordDO updateObj = BeanUtils.toBean(updateReqVO, ParkReleaseRecordDO.class);
        parkReleaseRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkReleaseRecord(Long id) {
        // 校验存在
        validateParkReleaseRecordExists(id);
        // 删除
        parkReleaseRecordMapper.deleteById(id);
    }

    private void validateParkReleaseRecordExists(Long id) {
        if (parkReleaseRecordMapper.selectById(id) == null) {
            throw exception(PARK_RELEASE_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public ParkReleaseRecordDO getParkReleaseRecord(Long id) {
        return parkReleaseRecordMapper.selectById(id);
    }

    @Override
    public PageResult<ParkReleaseRecordDO> getParkReleaseRecordPage(ParkReleaseRecordPageReqVO pageReqVO) {
        return parkReleaseRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addParkReleaseRecord(ParkReleaseRecordAddReqVO addReqVO) {
        //1. TODO 关联缴费记录id后面需要换成真实的
        //2. 放行指令标识UUID
        Long releaseCmdId= 1L;
        addReqVO.setReleaseCmdId(releaseCmdId);
        //3. 放行实际时间用现在
        addReqVO.setReleaseTime(LocalDateTime.now());

        //4.TODO 闸机唯一标识换成真实的
        // 插入
        ParkReleaseRecordDO parkReleaseRecord = BeanUtils.toBean(addReqVO, ParkReleaseRecordDO.class);
        parkReleaseRecordMapper.insert(parkReleaseRecord);
        // 返回
        return parkReleaseRecord.getId();
    }

}
