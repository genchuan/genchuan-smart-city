package cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.trainmat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.trainmat.TrainMatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 培训资料 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TrainMatMapper extends BaseMapperX<TrainMatDO> {

    default PageResult<TrainMatDO> selectPage(TrainMatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TrainMatDO>()
                .eqIfPresent(TrainMatDO::getTrainMatId, reqVO.getTrainMatId())
                .likeIfPresent(TrainMatDO::getMatName, reqVO.getMatName())
                .eqIfPresent(TrainMatDO::getTrainTypeCode, reqVO.getTrainTypeCode())
                .likeIfPresent(TrainMatDO::getTrainTypeName, reqVO.getTrainTypeName())
                .eqIfPresent(TrainMatDO::getTrainObject, reqVO.getTrainObject())
                .eqIfPresent(TrainMatDO::getTrainEndure, reqVO.getTrainEndure())
                .eqIfPresent(TrainMatDO::getMatFormat, reqVO.getMatFormat())
                .eqIfPresent(TrainMatDO::getMatSize, reqVO.getMatSize())
                .eqIfPresent(TrainMatDO::getMatAbstract, reqVO.getMatAbstract())
                .eqIfPresent(TrainMatDO::getMatFileId, reqVO.getMatFileId())
                .eqIfPresent(TrainMatDO::getMatFilePath, reqVO.getMatFilePath())
                .eqIfPresent(TrainMatDO::getMatStatus, reqVO.getMatStatus())
                .eqIfPresent(TrainMatDO::getDownloadCount, reqVO.getDownloadCount())
                .eqIfPresent(TrainMatDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(TrainMatDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(TrainMatDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(TrainMatDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(TrainMatDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(TrainMatDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TrainMatDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TrainMatDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(TrainMatDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(TrainMatDO::getId));
    }

}