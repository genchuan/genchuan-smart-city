package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.resoverview;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverDispatchReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.resoverview.EmergResoverViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.UNIVERSAL_SCENE_NOT_EXISTS;

/**
 * 应急资源总览 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergResoverViewServiceImpl implements EmergResoverViewService {

    @Resource
    private EmergResoverViewMapper emergResoverViewMapper;

    @Override
    public List<EmergResoverViewRespVO> listEmergResoverView(EmergResoverViewQueryReqVO emergResoverViewQueryReqVO) {
            return emergResoverViewMapper.listEmergResoverView(emergResoverViewQueryReqVO);
    }

    @Override
    public void dispatchEmergResover(EmergResoverDispatchReqVO reqVO) {

        // 1. 参数校验
        String resId = reqVO.getResId();
        if (!StringUtils.hasText(resId)) {
            throw exception(new ErrorCode(400, "请传入资源Id"));
        }

        Integer dispatchCount = reqVO.getDispatchCount();
        if (dispatchCount == null || dispatchCount <= 0) {
            throw exception(new ErrorCode(400, "调度数量必须大于0"));
        }

        // 2. 查询资源（VO）
        EmergResoverViewRespVO resVO = emergResoverViewMapper.getByResId(resId);
        if (resVO == null) {
            throw exception(new ErrorCode(500, "该资源不存在"));
        }

        // 3. 校验库存
        if (resVO.getAvailableQty() < dispatchCount) {
            throw exception(new ErrorCode(500, "调度资源数量大于当前可用库存"));
        }

        // 4. 计算新库存数量
        int newAvailableQty = resVO.getAvailableQty() - dispatchCount;
        int newDispatchQty = resVO.getDispatchQty() + dispatchCount;

        // 5. 计算库存状态
        String newStockStatus;
        if (newAvailableQty <= 0) {
            newStockStatus = "短缺";
        } else if (newAvailableQty <= resVO.getMinStock()) {
            newStockStatus = "紧张";
        } else {
            newStockStatus = "充足";
        }

        // 6. 更新数据库（关键：直接走 update SQL）
        int updateCount = emergResoverViewMapper.updateStockAfterDispatch(
                resId,
                newAvailableQty,
                newDispatchQty,
                newStockStatus
        );

        if (updateCount == 0) {
            throw exception(new ErrorCode(500, "库存更新失败，请重试"));
        }
    }
}
