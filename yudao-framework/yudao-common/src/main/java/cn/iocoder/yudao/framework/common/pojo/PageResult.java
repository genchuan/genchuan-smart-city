package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "分页结果")
@Data
public final class PageResult<T> implements Serializable {

    @Schema(description = "总量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long total;

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> list;

    public PageResult() {
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public PageResult(@NotNull(message = "页码不能为空") @Min(value = 1, message = "页码最小值为 1") @NotNull(message = "页码不能为空") @Min(value = 1, message = "页码最小值为 1") Integer pageNo, @NotNull(message = "每页条数不能为空") @Min(value = 1, message = "每页条数最小值为 1") @Max(value = 200, message = "每页条数最大值为 200") @NotNull(message = "每页条数不能为空") @Min(value = 1, message = "每页条数最小值为 1") @Max(value = 200, message = "每页条数最大值为 200") Integer pageSize) {
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

}
