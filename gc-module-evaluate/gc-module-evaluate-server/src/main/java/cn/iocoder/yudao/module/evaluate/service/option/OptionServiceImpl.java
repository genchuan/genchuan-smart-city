package cn.iocoder.yudao.module.evaluate.service.option;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.option.OptionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.option.OptionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 选项 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OptionServiceImpl implements OptionService {

    @Resource
    private OptionMapper optionMapper;

    @Override
    public Long createOption(OptionSaveReqVO createReqVO) {
        // 插入
        OptionDO option = BeanUtils.toBean(createReqVO, OptionDO.class);
        optionMapper.insert(option);

        // 返回
        return option.getId();
    }

    @Override
    public void updateOption(OptionSaveReqVO updateReqVO) {
        // 校验存在
        validateOptionExists(updateReqVO.getId());
        // 更新
        OptionDO updateObj = BeanUtils.toBean(updateReqVO, OptionDO.class);
        optionMapper.updateById(updateObj);
    }

    @Override
    public void deleteOption(Long id) {
        // 校验存在
        validateOptionExists(id);
        // 删除
        optionMapper.deleteById(id);
    }

    @Override
        public void deleteOptionListByIds(List<Long> ids) {
        // 删除
        optionMapper.deleteByIds(ids);
        }


    private void validateOptionExists(Long id) {
        if (optionMapper.selectById(id) == null) {
            throw exception(OPTION_NOT_EXISTS);
        }
    }

    @Override
    public OptionDO getOption(Long id) {
        return optionMapper.selectById(id);
    }

    @Override
    public PageResult<OptionDO> getOptionPage(OptionPageReqVO pageReqVO) {
        return optionMapper.selectPage(pageReqVO);
    }

}