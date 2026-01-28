package cn.iocoder.yudao.module.park.dal.mysql.recognitionevents;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.dal.dataobject.recognitionevents.RecognitionEventsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.park.controller.admin.recognitionevents.vo.*;

/**
 * 车牌识别事件 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface RecognitionEventsMapper extends BaseMapperX<RecognitionEventsDO> {

    default PageResult<RecognitionEventsDO> selectPage(RecognitionEventsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RecognitionEventsDO>()
                .eqIfPresent(RecognitionEventsDO::getType, reqVO.getType())
                .eqIfPresent(RecognitionEventsDO::getMode, reqVO.getMode())
                .eqIfPresent(RecognitionEventsDO::getProtoVer, reqVO.getProtoVer())
                .eqIfPresent(RecognitionEventsDO::getPlateNum, reqVO.getPlateNum())
                .eqIfPresent(RecognitionEventsDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(RecognitionEventsDO::getPlateVal, reqVO.getPlateVal())
                .eqIfPresent(RecognitionEventsDO::getConfidence, reqVO.getConfidence())
                .eqIfPresent(RecognitionEventsDO::getCarLogo, reqVO.getCarLogo())
                .eqIfPresent(RecognitionEventsDO::getCarSublogo, reqVO.getCarSublogo())
                .eqIfPresent(RecognitionEventsDO::getCarColor, reqVO.getCarColor())
                .eqIfPresent(RecognitionEventsDO::getVehicleType, reqVO.getVehicleType())
                .betweenIfPresent(RecognitionEventsDO::getStartTime, reqVO.getStartTime())
                .eqIfPresent(RecognitionEventsDO::getParkId, reqVO.getParkId())
                .eqIfPresent(RecognitionEventsDO::getCamId, reqVO.getCamId())
                .eqIfPresent(RecognitionEventsDO::getCamIp, reqVO.getCamIp())
                .eqIfPresent(RecognitionEventsDO::getVdcType, reqVO.getVdcType())
                .eqIfPresent(RecognitionEventsDO::getIsWhitelist, reqVO.getIsWhitelist())
                .eqIfPresent(RecognitionEventsDO::getTrigerType, reqVO.getTrigerType())
                .eqIfPresent(RecognitionEventsDO::getEncryptVerify, reqVO.getEncryptVerify())
                .eqIfPresent(RecognitionEventsDO::getPicture, reqVO.getPicture())
                .eqIfPresent(RecognitionEventsDO::getCloseupPic, reqVO.getCloseupPic())
                .eqIfPresent(RecognitionEventsDO::getCreatedAt, reqVO.getCreatedAt())
                .betweenIfPresent(RecognitionEventsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RecognitionEventsDO::getId));
    }

}