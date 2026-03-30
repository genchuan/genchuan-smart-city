package cn.iocoder.yudao.module.envirhealth.service.dictionary.tool;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.tool.vo.ToolPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.tool.vo.ToolSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ToolDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.ToolMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TOOL_NOT_EXISTS;

/**
 * 工具字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolMapper toolMapper;

    @Override
    public Long createTool(ToolSaveReqVO createReqVO) {
        // 插入
        ToolDO tool = BeanUtils.toBean(createReqVO, ToolDO.class);
        toolMapper.insert(tool);
        // 返回
        return tool.getId();
    }

    @Override
    public void updateTool(ToolSaveReqVO updateReqVO) {
        // 校验存在
        validateToolExists(updateReqVO.getId());
        // 更新
        ToolDO updateObj = BeanUtils.toBean(updateReqVO, ToolDO.class);
        toolMapper.updateById(updateObj);
    }

    @Override
    public void deleteTool(Long id) {
        // 校验存在
        validateToolExists(id);
        // 删除
        toolMapper.deleteById(id);
    }

    private void validateToolExists(Long id) {
        if (toolMapper.selectById(id) == null) {
            throw exception(TOOL_NOT_EXISTS);
        }
    }

    @Override
    public ToolDO getTool(Long id) {
        return toolMapper.selectById(id);
    }

    @Override
    public PageResult<ToolDO> getToolPage(ToolPageReqVO pageReqVO) {
        return toolMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getToolOptions() {

        List<ToolDO> list;
        list = toolMapper.selectList(
                new LambdaQueryWrapperX<ToolDO>()
                        .eq(ToolDO::getDeleted, 0)
                        .orderByDesc(ToolDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, toolDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(toolDO.getName());
            vo.setValue(toolDO.getSysToolId());
            return vo;
        });
    }
}