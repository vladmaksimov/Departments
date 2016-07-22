package com.maksimov.transformers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 7/23/2016.
 */
public interface RequestTransformer<T> {

    /**
     * Get the {@link T} object from the {@link HttpServletRequest}.
     *
     * @param req the {@link HttpServletRequest} object that contains the request the client made of the servlet.
     * @return T object.
     */
    T transform(HttpServletRequest req);
}
