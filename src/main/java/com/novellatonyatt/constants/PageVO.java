package com.novellatonyatt.constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 21:59
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分页信息")
public class PageVO<T> {

    @ApiModelProperty("总记录数")
    private int totalRecord;

    @ApiModelProperty("每页显示多少条")
    private int pageSize;

    @ApiModelProperty("总页数")
    private int totalPage;

    @ApiModelProperty("当前页数")
    private int currentPage;

    @ApiModelProperty("数据")
    private List<T> dataSource;

}
