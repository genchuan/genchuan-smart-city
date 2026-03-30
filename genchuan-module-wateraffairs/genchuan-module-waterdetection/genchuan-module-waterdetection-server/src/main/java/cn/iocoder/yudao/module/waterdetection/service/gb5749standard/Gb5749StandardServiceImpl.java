package cn.iocoder.yudao.module.waterdetection.service.gb5749standard;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.gb5749standard.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.gb5749standard.Gb5749StandardDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.gb5749standard.Gb5749StandardMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 《生活饮用水卫生标准》GB 5749-2022标准 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class Gb5749StandardServiceImpl implements Gb5749StandardService {

    @Resource
    private Gb5749StandardMapper gb5749StandardMapper;

    @Override
    public Long createGb5749Standard(Gb5749StandardSaveReqVO createReqVO) {
        // 插入
        Gb5749StandardDO gb5749Standard = BeanUtils.toBean(createReqVO, Gb5749StandardDO.class);
        gb5749StandardMapper.insert(gb5749Standard);
        // 返回
        return gb5749Standard.getId();
    }

    @Override
    public void updateGb5749Standard(Gb5749StandardSaveReqVO updateReqVO) {
        // 校验存在
        validateGb5749StandardExists(updateReqVO.getId());
        // 更新
        Gb5749StandardDO updateObj = BeanUtils.toBean(updateReqVO, Gb5749StandardDO.class);
        gb5749StandardMapper.updateById(updateObj);
    }

    @Override
    public void deleteGb5749Standard(Long id) {
        // 校验存在
        validateGb5749StandardExists(id);
        // 删除
        gb5749StandardMapper.deleteById(id);
    }

    private void validateGb5749StandardExists(Long id) {
        if (gb5749StandardMapper.selectById(id) == null) {
            throw exception(GB5749_STANDARD_NOT_EXISTS);
        }
    }

    @Override
    public Gb5749StandardDO getGb5749Standard(Long id) {
        return gb5749StandardMapper.selectById(id);
    }

    @Override
    public PageResult<Gb5749StandardDO> getGb5749StandardPage(Gb5749StandardPageReqVO pageReqVO) {
        return gb5749StandardMapper.selectPage(pageReqVO);
    }

}