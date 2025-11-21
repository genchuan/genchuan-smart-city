package cn.iocoder.yudao.module.datacenter.service.operationguide.trainmat;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.trainmat.TrainMatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 培训资料 Service 接口
 *
 * @author 亘川智城
 */
public interface TrainMatService {

    /**
     * 创建培训资料
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTrainMat(@Valid TrainMatSaveReqVO createReqVO);

    /**
     * 更新培训资料
     *
     * @param updateReqVO 更新信息
     */
    void updateTrainMat(@Valid TrainMatSaveReqVO updateReqVO);

    /**
     * 删除培训资料
     *
     * @param id 编号
     */
    void deleteTrainMat(Long id);

    /**
     * 获得培训资料
     *
     * @param id 编号
     * @return 培训资料
     */
    TrainMatDO getTrainMat(Long id);

    /**
     * 获得培训资料分页
     *
     * @param pageReqVO 分页查询
     * @return 培训资料分页
     */
    PageResult<TrainMatDO> getTrainMatPage(TrainMatPageReqVO pageReqVO);

}