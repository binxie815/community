package life.flycode.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther
 * @Date:2020/1/13
 * @Description:
 * @version:1.0
 */
@Data
public class PageInfoDATO {
    private List<QuestionDTO> questions;
    /*上一页*/
    private boolean showPrevious;
    /*首页*/
    private boolean showFirst;
    /*下一页*/
    private boolean showNext;
    /*尾页*/
    private boolean showEndPage;
    /*当前页数*/
    private Integer currentPage;
    /*分页数据集*/
    private List<Integer> pages = new ArrayList<>();
    /*总的页码数*/
    private Integer totalPage;

    /**
     * @param totalCount 总数值
     * @param page       当前是第几页
     * @param size       每页需要加载的数值
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.currentPage = page;
        //总数 取模 每页需要加载的数值 = 数据分为几页
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //防止页面输入无效数据
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            //当前页左边的页码
            if (page - i > 0) {
                //往头部插入
                pages.add(0, page - i);
            }
            //当前页右边的页码
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        this.showPrevious = page == 1 ? false : true;

        //是否展示下一页
        this.showNext = page == totalPage ? false : true;

        //是否展示首页, 如果包含第一页
        this.showFirst = pages.contains(1) ? false : true;

        //是否展示尾页 ，如果包含最后一页
        this.showEndPage = pages.contains(totalPage) ? false : true;
    }
}
