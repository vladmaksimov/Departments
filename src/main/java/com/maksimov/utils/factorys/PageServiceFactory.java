package com.maksimov.utils.factorys;

import com.maksimov.services.PageService;
import com.maksimov.services.impl.PageServiceImpl;

/**
 * Created on 16.09.16.
 */
public class PageServiceFactory {

    private static PageService pageService = new PageServiceImpl();

    public static PageService getPageService() {
        return pageService;
    }

}
