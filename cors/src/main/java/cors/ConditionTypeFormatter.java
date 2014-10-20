package cors;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import cors.domain.condition.type.DefaultConditionType;
import cors.service.ConditionTypes;

@Component
public class ConditionTypeFormatter implements Formatter<DefaultConditionType> {

    @Autowired
    private ConditionTypes conditionTypes;

    

    public ConditionTypeFormatter() {
        super();
    }

    public DefaultConditionType parse(final String text, final Locale locale) throws ParseException {   	

        return conditionTypes.get(text);
    }

    public String print(final DefaultConditionType object, final Locale locale) {
        return object.getName();
    }

}