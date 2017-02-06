package com.maksimov.transformers;

import com.maksimov.models.Page;
import com.maksimov.utils.Utils;
import com.maksimov.utils.factorys.ModelFactory;

import javax.servlet.http.HttpServletRequest;

import static com.maksimov.constants.PageConstants.*;

/**
 * This class consists method which operates with the {@link HttpServletRequest} object
 * and creates the {@link Page} object from request parameters.
 * <p>
 * Created on 16.09.16.
 *
 * @author Vladislav Maksimov
 * @see com.maksimov.controllers.Controller
 * @see Page
 */
public class PageRequestTransformer implements RequestTransformer<Page> {

    @Override
    public Page transform(HttpServletRequest req) {

        Integer pageNumber = Utils.parseInteger(req.getParameter(PAGE_NUMBER));
        pageNumber = pageNumber == null || pageNumber < 1 ? PAGE_DEFAULT_NUMBER : pageNumber;

        Integer pageSize = Utils.parseInteger(req.getParameter(PAGE_SIZE));
        if (pageSize == null || pageSize < 1) {
            pageSize = PAGE_DEFAULT_SIZE;
        }

        String sort = req.getParameter(PAGE_SORT);
        if (sort == null) {
            sort = PAGE_DEFAULT_SORT;
        }

        return ModelFactory.createPage(pageNumber, pageSize, sort);
    }
}
