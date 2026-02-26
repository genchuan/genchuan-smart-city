package cn.iocoder.yudao.module.envir.service.urbanvillage;

import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.urbanvillage.UrbanVillageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 城中村 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UrbanVillageServiceImpl implements UrbanVillageService {

    @Resource
    private UrbanVillageMapper urbanVillageMapper;

    @Override
    public Long createUrbanVillage(UrbanVillageSaveReqVO createReqVO) {
        // 插入
        UrbanVillageDO urbanVillage = BeanUtils.toBean(createReqVO, UrbanVillageDO.class);
        urbanVillageMapper.insert(urbanVillage);
        // 返回
        return urbanVillage.getId();
    }

    @Override
    public void updateUrbanVillage(UrbanVillageSaveReqVO updateReqVO) {
        // 校验存在
        validateUrbanVillageExists(updateReqVO.getId());
        // 更新
        UrbanVillageDO updateObj = BeanUtils.toBean(updateReqVO, UrbanVillageDO.class);
        urbanVillageMapper.updateById(updateObj);
    }

    @Override
    public void deleteUrbanVillage(Long id) {
        // 校验存在
        validateUrbanVillageExists(id);
        // 删除
        urbanVillageMapper.deleteById(id);
    }

    private void validateUrbanVillageExists(Long id) {
        if (urbanVillageMapper.selectById(id) == null) {
            throw exception(URBAN_VILLAGE_NOT_EXISTS);
        }
    }

    @Override
    public UrbanVillageDO getUrbanVillage(Long id) {
        return urbanVillageMapper.selectById(id);
    }

    @Override
    public PageResult<UrbanVillageDO> getUrbanVillagePage(UrbanVillagePageReqVO pageReqVO) {
        return urbanVillageMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UrbanVillageDetailDO> getUrbanVillageListDetail() {
        return urbanVillageMapper.selectListDetail();
    }
}