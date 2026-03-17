package cn.iocoder.yudao.module.envirhealth.service.urbanvillage.urbanvillage;

import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.urbanvillage.UrbanVillagePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.urbanvillage.UrbanVillageSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.detail.UrbanVillageDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.urbanvillage.UrbanVillageCodeGenerator;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage.UrbanVillageMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

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

    @Resource
    private UrbanVillageCodeGenerator codeGenerator;

    @Override
    public Long createUrbanVillage(UrbanVillageSaveReqVO createReqVO) {
        // 插入
        UrbanVillageDO urbanVillage = BeanUtils.toBean(createReqVO, UrbanVillageDO.class);

        urbanVillage.setId(null);
        urbanVillage.setVillageId(codeGenerator.generateVillageId());

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
    public PageResult<UrbanVillageDetailDO> getUrbanVillageDetailPage(UrbanVillagePageReqVO pageReqVO) {
        Long total = urbanVillageMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<UrbanVillageDetailDO> list = urbanVillageMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}