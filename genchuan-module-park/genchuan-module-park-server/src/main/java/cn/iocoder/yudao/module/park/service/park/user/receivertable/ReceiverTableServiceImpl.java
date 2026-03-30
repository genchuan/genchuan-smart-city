package cn.iocoder.yudao.module.park.service.park.user.receivertable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTablePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTableSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.receivertable.ReceiverTableDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.receivertable.ReceiverTableMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.RECEIVER_TABLE_NOT_EXISTS;

/**
 * 接收方 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ReceiverTableServiceImpl implements ReceiverTableService {

    @Resource
    private ReceiverTableMapper receiverTableMapper;

    @Override
    public Long createReceiverTable(ReceiverTableSaveReqVO createReqVO) {
        // 插入
        ReceiverTableDO receiverTable = BeanUtils.toBean(createReqVO, ReceiverTableDO.class);
        receiverTableMapper.insert(receiverTable);
        // 返回
        return receiverTable.getId();
    }

    @Override
    public void updateReceiverTable(ReceiverTableSaveReqVO updateReqVO) {
        // 校验存在
        validateReceiverTableExists(updateReqVO.getId());
        // 更新
        ReceiverTableDO updateObj = BeanUtils.toBean(updateReqVO, ReceiverTableDO.class);
        receiverTableMapper.updateById(updateObj);
    }

    @Override
    public void deleteReceiverTable(Long id) {
        // 校验存在
        validateReceiverTableExists(id);
        // 删除
        receiverTableMapper.deleteById(id);
    }

    private void validateReceiverTableExists(Long id) {
        if (receiverTableMapper.selectById(id) == null) {
            throw exception(RECEIVER_TABLE_NOT_EXISTS);
        }
    }

    @Override
    public ReceiverTableDO getReceiverTable(Long id) {
        return receiverTableMapper.selectById(id);
    }

    @Override
    public PageResult<ReceiverTableDO> getReceiverTablePage(ReceiverTablePageReqVO pageReqVO) {
        return receiverTableMapper.selectPage(pageReqVO);
    }

}
