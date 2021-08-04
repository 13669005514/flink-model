package com.supermap.model.pojo.request;

import com.supermap.model.validated.GroupPage;
import com.supermap.model.validated.ListPage;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : zhangfx 2020/1/16 16:48
 * @version : 1.0
 */
@Data
public class PageInfo {

    /**
     * 页码
     */
    @NotNull(groups = ListPage.class,message = "pageIndex页码最小为1不能为空")
    @Min(value = 1,groups = ListPage.class,message = "pageIndex页码最小为1")
    private Integer pageIndex;

    /**
     * 页数
     */
    @NotNull(groups = ListPage.class,message = "pageSize页数最小为1不能为空")
    @Min(value = 1,groups = ListPage.class,message = "pageSize页数最小为1")
    private Integer pageSize;

    /**
     * 查询关键字
     */
    @NotBlank(groups = {ListPage.class,GroupPage.class},message = "keyWord查询关键字不能为空")
    private String keyWord;

    /**
     * 要素类型
     */
    private String objType;



}
