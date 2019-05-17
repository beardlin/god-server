package net.lantrack.framework.springplugin.view;

import net.lantrack.framework.core.entity.ReturnEntity;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView extends InternalResourceView {
    /**
     * Render the internal resource given the specified model.
     * This includes setting the model as request attributes.
     */
    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Expose the model object as request attributes.
        exposeModelAsRequestAttributes(model, request);

        // Expose helpers as request attributes, if any.
        exposeHelpers(request);

        // Determine the path for the request dispatcher.
        String dispatcherPath = prepareForRendering(request, response);

        // Obtain a RequestDispatcher for the target resource (typically a JSP).
        RequestDispatcher rd = getRequestDispatcher(request, dispatcherPath);
        if (rd == null) {
            throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
                    "]: Check that the corresponding file exists within your web application archive!");
        }

        // If already included or response already committed, perform include, else forward.
        if (useInclude(request, response)) {
            response.setContentType(getContentType());
            if (logger.isDebugEnabled()) {
                logger.debug("Including resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
            }
            rd.include(request, response);
        } else {
            // Note: The forwarded resource is supposed to determine the content type itself.
            if (logger.isDebugEnabled()) {
                logger.debug("Forwarding to resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
            }
            rd.forward(request, response);
        }
    }

    /**
     * Expose the model objects in the given map as request attributes.
     * Names will be taken from the model Map.
     * This method is suitable for all resources reachable by {@link RequestDispatcher}.
     *
     * @param model   Map of model objects to expose
     * @param request current HTTP request
     */
    protected void exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request) throws Exception {
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            String modelName = entry.getKey();
            Object modelValue = entry.getValue();
            if (modelValue != null) {
                request.setAttribute(modelName, modelValue);
                if ("returnEntity".equals(modelName))//returnEntity  pageEntity
                {
                    ReturnEntity returnEntity = (ReturnEntity) modelValue;
                    request.setAttribute("status", returnEntity.getStatus());//二次封装
                    request.setAttribute("message", returnEntity.getMessage());//二次封装
//                    Object obj = returnEntity.getResult();
//                    Map<String, Object> map = (Map<String, Object>) obj;
                    //System.out.println("1");
//                    for (Map.Entry<String, Object> re : map.entrySet()) {
//                        String rename = re.getKey();
//                        Object reValue = re.getValue();
//                        if (reValue != null) {
//                            request.removeAttribute(rename);
//                            request.setAttribute(rename, reValue);//二次封装
//                            if (logger.isDebugEnabled()) {
//                                logger.debug("Added model object '" + rename + "' of type [" + reValue.getClass().getName() +
//                                        "] to request in view with name '" + getBeanName() + "'");
//                            }
//                        }
//                    }

                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Added model object '" + modelName + "' of type [" + modelValue.getClass().getName() +
                            "] to request in view with name '" + getBeanName() + "'");
                }
            } else {
                request.removeAttribute(modelName);
                if (logger.isDebugEnabled()) {
                    logger.debug("Removed model object '" + modelName +
                            "' from request in view with name '" + getBeanName() + "'");
                }
            }
        }
    }
}
