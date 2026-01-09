package cn.iocoder.yudao.module.smartcity.service.denselypopulatedareas;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.denselypopulatedareas.DenselyPopulatedAreasDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.denselypopulatedareas.DenselyPopulatedAreasMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 人员密集场所安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DenselyPopulatedAreasServiceImpl implements DenselyPopulatedAreasService {

    @Resource
    private DenselyPopulatedAreasMapper denselyPopulatedAreasMapper;

    @Override
    public Long createDenselyPopulatedAreas(DenselyPopulatedAreasSaveReqVO createReqVO) {
        // 插入
        DenselyPopulatedAreasDO denselyPopulatedAreas = BeanUtils.toBean(createReqVO, DenselyPopulatedAreasDO.class);
        denselyPopulatedAreasMapper.insert(denselyPopulatedAreas);
        // 返回
        return denselyPopulatedAreas.getId();
    }

    @Override
    public void updateDenselyPopulatedAreas(DenselyPopulatedAreasSaveReqVO updateReqVO) {
        // 校验存在
        validateDenselyPopulatedAreasExists(updateReqVO.getId());
        // 更新
        DenselyPopulatedAreasDO updateObj = BeanUtils.toBean(updateReqVO, DenselyPopulatedAreasDO.class);
        denselyPopulatedAreasMapper.updateById(updateObj);
    }

    @Override
    public void deleteDenselyPopulatedAreas(Long id) {
        // 校验存在
        validateDenselyPopulatedAreasExists(id);
        // 删除
        denselyPopulatedAreasMapper.deleteById(id);
    }

    private void validateDenselyPopulatedAreasExists(Long id) {
        if (denselyPopulatedAreasMapper.selectById(id) == null) {
            throw exception(DENSELY_POPULATED_AREAS_NOT_EXISTS);
        }
    }

    @Override
    public DenselyPopulatedAreasDO getDenselyPopulatedAreas(Long id) {
        return denselyPopulatedAreasMapper.selectById(id);
    }

    @Override
    public PageResult<DenselyPopulatedAreasDO> getDenselyPopulatedAreasPage(DenselyPopulatedAreasPageReqVO pageReqVO) {
        return denselyPopulatedAreasMapper.selectPage(pageReqVO);
    }

}