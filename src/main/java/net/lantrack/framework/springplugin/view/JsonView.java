package net.lantrack.framework.springplugin.view;

import com.fasterxml.jackson.databind.ser.FilterProvider;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class JsonView extends MappingJackson2JsonView {


    //private Set<String> modelKeys=new LinkedHashSet<String>();

//    private boolean extractValueFromSingleKeyModel = false;

    private Set<String> jsonpParameterNames = new LinkedHashSet<String>(Arrays.asList("jsonp", "callback"));


    /**
     * Filter out undesired attributes from the given model.
     * The return value can be either another {@link Map} or a single value object.
     * <p>The default implementation removes {@link BindingResult} instances and entries
     * not included in the {@link #setModelKeys renderedAttributes} property.
     *
     * @param model the model, as passed on to {@link #renderMergedOutputModel}
     * @return the value to be rendered
     */
    @Override
    protected Object filterModel(Map<String, Object> model) {
        Object info = null;
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) &&
                    !entry.getKey().equals(JsonView.class.getName()) &&
                    !entry.getKey().equals(FilterProvider.class.getName()) &&
                    entry.getKey().equals("returnEntity") ||
                    !(entry.getValue() instanceof BindingResult) &&
                            !entry.getKey().equals(JsonView.class.getName()) &&
                            !entry.getKey().equals(FilterProvider.class.getName())&&
                            entry.getKey().equals("returnPage") ) {                info = entry.getValue();
            }
        }
        return info;
    }

	@Override
    protected Object filterAndWrapModel(Map<String, Object> model, HttpServletRequest request) {
		Object value = filterModel(model);
        Class<?> serializationView = (Class<?>) model.get(JsonView.class.getName());
        FilterProvider filters = (FilterProvider) model.get(FilterProvider.class.getName());
        if (serializationView != null || filters != null) {
            MappingJacksonValue container = new MappingJacksonValue(value);
            container.setSerializationView(serializationView);
            container.setFilters(filters);
            value = container;
        }
        
		String jsonpParameterValue = getJsonpParameterValue(request);
		if (jsonpParameterValue != null) {
			if (value instanceof MappingJacksonValue) {
				((MappingJacksonValue) value).setJsonpFunction(jsonpParameterValue);
			}
			else {
				MappingJacksonValue container = new MappingJacksonValue(value);
				container.setJsonpFunction(jsonpParameterValue);
				value = container;
			}
		}
		if(value instanceof ReturnEntity){
		    request.setAttribute("returnEntity", value);
		}else if(value instanceof ReturnPage){
		    request.setAttribute("returnPage", value);
		}
		return value;
	}
	
	private String getJsonpParameterValue(HttpServletRequest request) {
		if (this.jsonpParameterNames != null) {
			for (String name : this.jsonpParameterNames) {
				String value = request.getParameter(name);
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				if (!isValidJsonpQueryParam(value)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Ignoring invalid jsonp parameter value: " + value);
					}
					continue;
				}
				return value;
			}
		}
		return null;
	}
}
