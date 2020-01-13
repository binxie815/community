package life.flycode.community.dto;

import lombok.Data;

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
    private List<Integer> pages;
}
