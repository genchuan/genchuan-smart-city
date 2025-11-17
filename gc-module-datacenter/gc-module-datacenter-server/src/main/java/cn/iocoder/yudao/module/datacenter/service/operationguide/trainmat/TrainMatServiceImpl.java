package cn.iocoder.yudao.module.datacenter.service.operationguide.trainmat;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.trainmat.TrainMatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.trainmat.TrainMatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 培训资料 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TrainMatServiceImpl implements TrainMatService {

    @Resource
    private TrainMatMapper trainMatMapper;

    @Override
    public Long createTrainMat(TrainMatSaveReqVO createReqVO) {
        // 插入
        TrainMatDO trainMat = BeanUtils.toBean(createReqVO, TrainMatDO.class);
        trainMatMapper.insert(trainMat);
        // 返回
        return trainMat.getId();
    }

    @Override
    public void updateTrainMat(TrainMatSaveReqVO updateReqVO) {
        // 校验存在
        validateTrainMatExists(updateReqVO.getId());
        // 更新
        TrainMatDO updateObj = BeanUtils.toBean(updateReqVO, TrainMatDO.class);
        trainMatMapper.updateById(updateObj);
    }

    @Override
    public void deleteTrainMat(Long id) {
        // 校验存在
        validateTrainMatExists(id);
        // 删除
        trainMatMapper.deleteById(id);
    }

    private void validateTrainMatExists(Long id) {
        if (trainMatMapper.selectById(id) == null) {
            throw exception(TRAIN_MAT_NOT_EXISTS);
        }
    }

    @Override
    public TrainMatDO getTrainMat(Long id) {
        return trainMatMapper.selectById(id);
    }

    @Override
    public PageResult<TrainMatDO> getTrainMatPage(TrainMatPageReqVO pageReqVO) {
        return trainMatMapper.selectPage(pageReqVO);
    }

}