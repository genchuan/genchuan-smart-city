package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.resoverview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 应急资源总览 Mapper
 *
 * @author lxs
 */
@Mapper
public interface EmergResoverViewMapper {

        /**
         * 查询应急资源总览
         *
         * @param emergResoverViewQueryReqVO 查询参数
         * @return EmergResoverViewRespVO 结果
         */
        List<EmergResoverViewRespVO> listEmergResoverView(EmergResoverViewQueryReqVO emergResoverViewQueryReqVO);

        EmergResoverViewRespVO getByResId(String resId);

        int updateStockAfterDispatch(String resId, int newAvailableQty, int newDispatchQty, String newStockStatus);
}
