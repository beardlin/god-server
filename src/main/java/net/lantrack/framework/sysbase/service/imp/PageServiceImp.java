package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.PageService;

import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("serial")
@Service
public class PageServiceImp extends PageEntity implements PageService {
    
	public static final int DEFAULTPAGESIZE = 20;// 默认一页20条

	@Override
	public PageEntity doList(List<?> list,long count) throws ServiceException {
	    
		if (count != 0L) {
			totalPage = count % (long) perPageCount != 0L ? count
					/ (long) perPageCount + 1L : count / (long) perPageCount;
			if ((long) currentPage > totalPage)
				currentPage = (int) totalPage;
		}
		super.setContent(list);
		return this;
	}

	@Override
	public void getPage(int pageSize, int currentPage) {
		this.currentPage = 1;
		totalPage = 1L;
		totalCount = 0L;
		this.perPageCount = pageSize > 0 ? pageSize : PageServiceImp.DEFAULTPAGESIZE;
		this.currentPage = currentPage > 0 ? currentPage : currentPage;
		
	}

    


	

}
