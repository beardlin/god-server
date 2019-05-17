package net.lantrack.framework.springplugin.view;

import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Map;

public class XmlView extends MarshallingView {

    private String modelKey = "returnEntityInfo";//pageEntity

    protected Object locateToBeMarshalled(Map<String, Object> model) throws IllegalStateException {

        Object value = model.get("returnEntity");
        Object pageEntity = model.get("pageEntity");

        if (value == null) {
            value = pageEntity;
        }
        if (value == null) {
            throw new IllegalStateException("Model contains no object with key [" + this.modelKey + "]");
        }
        if (!isEligibleForMarshalling(this.modelKey, value)) {
            throw new IllegalStateException("Model object [" + value + "] retrieved via key [" +
                    this.modelKey + "] is not supported by the Marshaller");
        }
        return value;

	/*	for (Map.Entry<String, Object> entry : model.entrySet()) {
			Object value = entry.getValue();
			if (value != null && (model.size() == 1 || !(value instanceof BindingResult)) &&
					isEligibleForMarshalling(entry.getKey(), value)) {
				return value;
			}
		}*/

    }
}
