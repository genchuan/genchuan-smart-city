package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletfacilityrepair;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletFacilityRepairDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletFacilityRepairMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕设施维修 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ToiletFacilityRepairServiceImpl implements ToiletFacilityRepairService {

    @Resource
    private ToiletFacilityRepairMapper toiletFacilityRepairMapper;

    @Override
    public Long createToiletFacilityRepair(ToiletFacilityRepairSaveReqVO createReqVO) {
        // 插入
        ToiletFacilityRepairDO toiletFacilityRepair = BeanUtils.toBean(createReqVO, ToiletFacilityRepairDO.class);
        toiletFacilityRepairMapper.insert(toiletFacilityRepair);
        // 返回
        return toiletFacilityRepair.getId();
    }

    @Override
    public void updateToiletFacilityRepair(ToiletFacilityRepairSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletFacilityRepairExists(updateReqVO.getId());
        // 更新
        ToiletFacilityRepairDO updateObj = BeanUtils.toBean(updateReqVO, ToiletFacilityRepairDO.class);
        toiletFacilityRepairMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletFacilityRepair(Long id) {
        // 校验存在
        validateToiletFacilityRepairExists(id);
        // 删除
        toiletFacilityRepairMapper.deleteById(id);
    }

    private void validateToiletFacilityRepairExists(Long id) {
        if (toiletFacilityRepairMapper.selectById(id) == null) {
            throw exception(TOILET_FACILITY_REPAIR_NOT_EXISTS);
        }
    }

    @Override
    public ToiletFacilityRepairDO getToiletFacilityRepair(Long id) {
        return toiletFacilityRepairMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletFacilityRepairDO> getToiletFacilityRepairPage(ToiletFacilityRepairPageReqVO pageReqVO) {
        return toiletFacilityRepairMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletFacilityRepairDetailDO> getToiletFacilityRepairDetailPage(ToiletFacilityRepairPageReqVO pageReqVO) {
        Long total = toiletFacilityRepairMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletFacilityRepairDetailDO> list = toiletFacilityRepairMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}